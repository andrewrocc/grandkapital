package com.assessment.work.grandkapital.util;

import com.assessment.work.grandkapital.model.service.User;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class Page {

    private Integer number;
    private Integer total;
    private Long elementsTotal;
    private List<User> elements;
}