package com.assessment.work.grandkapital.schedule;

import com.assessment.work.grandkapital.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduleExecutor {

    private final AccountRepository accountRepository;

    @Transactional
    @Scheduled(cron = "${schedule.updateBalance.time}")
    public void updateBalance() {
        log.debug("Schedule update balance has been started");
        accountRepository.findAll().forEach(account -> {            // not best practise extract all data :))
            BigDecimal balance = account.getBalance();
            BigDecimal increased = account.getBalance().multiply(new BigDecimal(1.10));

            BigDecimal maxBalance = account.getInitDeposit().multiply(BigDecimal.valueOf(2.07));
            if (increased.compareTo(maxBalance) > 0) {
                increased = maxBalance;
            }
            increased = increased.setScale(2, RoundingMode.HALF_UP);
            account.setBalance(increased);

            log.info("Account {}: {} -> {} (max: {})", account.getId(), balance, increased, maxBalance);
        });
        log.debug("Schedule update balance has been ended");
    }
}