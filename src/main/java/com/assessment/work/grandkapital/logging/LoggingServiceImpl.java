package com.assessment.work.grandkapital.logging;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class LoggingServiceImpl implements LoggingService {

    @Override
    public void logRequest(HttpServletRequest httpServletRequest, Object body) {
        Map<String, String> parameters = buildParametersMap(httpServletRequest);

        String requestLog = "REQUEST " + httpServletRequest.getMethod() + " " + httpServletRequest.getRequestURI() + "\n" +
                "headers=[" + buildHeadersMap(httpServletRequest) + "]";

        if (!parameters.isEmpty()) {
            requestLog += "\n" + "parameters=[" + parameters + "]";
        }

        if (body != null) {
            requestLog += "\n" + "body=[" + body + "]";
        }

        log.info(requestLog);
    }

    @Override
    public void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body) {
        String stringBuilder = "RESPONSE " + httpServletRequest.getMethod() + httpServletRequest.getRequestURI() + "\n" +
                "responseHeaders=[" + buildHeadersMap(httpServletResponse) + "]" + "\n" +
                "status=" + httpServletResponse.getStatus() + "\n" +
                "responseBody=[" + body + "]";

        log.info(stringBuilder);
    }

    private Map<String, String> buildParametersMap(HttpServletRequest httpServletRequest) {
        Map<String, String> resultMap = new HashMap<>();
        Enumeration<String> parameterNames = httpServletRequest.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            String value = httpServletRequest.getParameter(key);
            resultMap.put(key, value);
        }

        return resultMap;
    }

    private Map<String, String> buildHeadersMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return map;
    }

    private Map<String, String> buildHeadersMap(HttpServletResponse response) {
        Map<String, String> map = new HashMap<>();

        Collection<String> headerNames = response.getHeaderNames();
        for (String header : headerNames) {
            map.put(header, response.getHeader(header));
        }

        return map;
    }
}