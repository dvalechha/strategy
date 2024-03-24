package com.example.test.strategy.model;

import lombok.Getter;
import lombok.Setter;
import java.util.Map;

@Getter
@Setter
public class Rules {
    private Map<String, Map<String, Boolean>> enroll;
    private Map<String, Map<String, Boolean>> validate;
    private Map<String, Map<String, Boolean>> link;
    private Map<String, Map<String, Boolean>> unlink;
}

