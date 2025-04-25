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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class IntegrationTest extends AbstractTest {

    @Test
    @Sql(value = "/sql/insert_data_test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/sql/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @DisplayName("Тест трансфера денег между пользователями")
    void transferMoneyTestOk() throws Exception {
        TransferRequest transferRequest = new TransferRequest().toUserId(5L).amount(BigDecimal.valueOf(50000));
        String token = generateToken(1L, "dmitry.smirnov@example.com");

        MockHttpServletResponse responseGenerate = mvc.perform(post("/transfer")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transferRequest)))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        Message actualMessage = objectMapper.readValue(responseGenerate.getContentAsString(), Message.class);
        assertEquals("Transfer successful", actualMessage.getMessage());

        UserEntity userFrom = userRepository.findById(1L).get();
        UserEntity userTo = userRepository.findById(transferRequest.getToUserId()).get();
        assertAll(
                () -> assertEquals(userFrom.getAccount().getBalance().compareTo(BigDecimal.valueOf(100000.50)), 0),
                () -> assertEquals(userTo.getAccount().getBalance().compareTo(BigDecimal.valueOf(135000.00)),0)
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