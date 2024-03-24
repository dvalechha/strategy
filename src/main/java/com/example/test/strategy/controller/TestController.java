package com.example.test.strategy.controller;

import com.example.test.strategy.model.controller.MemberEnrollRequest;
import com.example.test.strategy.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping("/member/enroll")
    public String performFunction(@RequestBody MemberEnrollRequest memberEnrollRequest) {
        return testService.enroll(memberEnrollRequest);
    }
}
