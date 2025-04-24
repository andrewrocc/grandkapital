package com.assessment.work.grandkapital.model.service;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;

@Data
@Accessors(chain = true)
public class User {

    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private String password;

    private List<String> emails;
    private List<String> phones;
    private List<String> accounts;
}