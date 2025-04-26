package com.assessment.work.grandkapital;

import com.assessment.work.grandkapital.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@AutoConfigureMockMvc
@ActiveProfiles({"test"})
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = GrandKapitalApplication.class)
public abstract class AbstractTest {

    @Autowired
    protected MockMvc mvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected UserRepository userRepository;

    public final String USER_ID_CLAIM = "USER_ID";

    @Value("${spring.security.jwt.key-value}")
    private String KEY_SECRET;

    protected String generateToken(Long userId) {
        return Jwts.builder()
                .claim(USER_ID_CLAIM, userId.toString())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 3600_000))    // 1 час
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {
        byte[] decode = Base64.getDecoder().decode(KEY_SECRET);
        return Keys.hmacShaKeyFor(decode);
    }
}