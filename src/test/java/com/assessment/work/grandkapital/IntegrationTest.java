package com.assessment.work.grandkapital;

import com.assessment.work.grandkapital.model.dto.Message;
import com.assessment.work.grandkapital.model.dto.TransferRequest;
import com.assessment.work.grandkapital.model.entity.UserEntity;
import com.assessment.work.grandkapital.util.Page;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class IntegrationTest extends AbstractTest {

    @Test
    @Sql(value = "/sql/insert_data_test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/sql/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @DisplayName("Тест (многопоточного) многопользовательского трансфера денег между пользователями")
    void transferMoneyTestOk() throws Exception {
        int threads = 5;
        CountDownLatch latch = new CountDownLatch(threads);
        List<Future<MockHttpServletResponse>> futures = new ArrayList<>();
        ExecutorService threadPool = Executors.newFixedThreadPool(threads);

        long userId = 5L;
        String token = generateToken(1L);

        for (int i = 0; i < threads; i++) {
            futures.add(threadPool.submit(() -> {
                try {
                    TransferRequest transferRequest = new TransferRequest().toUserId(userId).amount(BigDecimal.valueOf(1000));

                    MockHttpServletResponse response = mvc.perform(post("/transfer")
                                    .header("Authorization", "Bearer " + token)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(transferRequest)))
                            .andExpect(status().isOk())
                            .andReturn().getResponse();

                    return response;
                } finally {
                    latch.countDown();
                }
            }));
        }
        latch.await();
        threadPool.shutdown();

        for (var future : futures) {
            var response = future.get();
            Message message = objectMapper.readValue(response.getContentAsString(), Message.class);
            assertEquals("Transfer successful", message.getMessage());
        }

        UserEntity userFrom = userRepository.findById(1L).get();
        UserEntity userTo = userRepository.findById(userId).get();

        BigDecimal expectedFromBalance = BigDecimal.valueOf(150000.50).subtract(BigDecimal.valueOf(1000L * threads));
        BigDecimal expectedToBalance = BigDecimal.valueOf(85000.00).add(BigDecimal.valueOf(1000L * threads));
        assertAll(
                () -> assertEquals(0, userFrom.getAccount().getBalance().compareTo(expectedFromBalance)),
                () -> assertEquals(0, userTo.getAccount().getBalance().compareTo(expectedToBalance))
        );
    }

    @Test
    @Sql(value = "/sql/insert_data_test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/sql/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @DisplayName("Тест получить страницу пользователей")
    void getUserPageTestOk() throws Exception {

        MockHttpServletResponse responseGenerate = mvc.perform(get("/users")
                        .queryParam("pageNumber", "0")
                        .queryParam("pageSize", "7")
                        .queryParam("dateOfBirth", "1992-01-01")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        Page actualPage = objectMapper.readValue(responseGenerate.getContentAsString(), Page.class);

        assertAll(
                () -> assertEquals(actualPage.getElements().size(), 2),
                () -> assertEquals(actualPage.getElementsTotal(), 2),
                () -> assertEquals(actualPage.getElements().get(0).getDateOfBirth(), LocalDate.parse("1995-03-08")),
                () -> assertEquals(actualPage.getElements().get(1).getDateOfBirth(), LocalDate.parse("1992-09-14"))
        );
    }
}