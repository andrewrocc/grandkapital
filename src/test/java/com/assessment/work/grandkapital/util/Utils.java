package com.assessment.work.grandkapital.util;

import org.springframework.http.HttpHeaders;

public class Utils {
    public static HttpHeaders buildHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        httpHeaders.add("Accept", "application/json");
        return httpHeaders;
    }
}