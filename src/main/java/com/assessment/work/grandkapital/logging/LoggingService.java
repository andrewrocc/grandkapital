package com.assessment.work.grandkapital.logging;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface LoggingService {

    void logRequest(HttpServletRequest httpServletRequest, Object body);

    void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body);
}
