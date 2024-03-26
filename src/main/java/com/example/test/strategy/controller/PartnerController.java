package com.example.test.strategy.controller;

import com.example.test.strategy.model.controller.MemberRegisterationRequest;
import com.example.test.strategy.model.controller.MemberLinkRequest;
import com.example.test.strategy.model.error.ErrorResponse;
import com.example.test.strategy.service.EnrollmentService;
import com.example.test.strategy.service.MemberLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class PartnerController {

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private MemberLinkService memberLinkService;

    @PostMapping("/member/register")
    public ResponseEntity<?> enrollMember(
                                @RequestBody MemberRegisterationRequest memberRegisterationRequest,
                                @RequestHeader(value = "requestId", required = true) String requestId) {
        try {
            enrollmentService.registerMember(memberRegisterationRequest);
            return ResponseEntity.ok("Enrollment Complete !!");
        } catch (Exception e) {
            //In actual implementation, different types of error should map to different codes
            ErrorResponse errorResponse = new ErrorResponse("LL-SVC-100",  e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/member/unenroll")
    public ResponseEntity<?> unenrollMember(@RequestHeader(value = "requestId", required = true) String requestId,
                                                 @RequestBody MemberRegisterationRequest memberRegisterationRequest) {
        try {
            enrollmentService.unenrollMember(memberRegisterationRequest);
            return ResponseEntity.ok("Un-enrollment Complete");
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("LL-SVC-101",  e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/member/link")
    public ResponseEntity<?> link(@RequestBody MemberLinkRequest linkRequest) {
        try {
            memberLinkService.link(linkRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Linked successfully");
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("LL-SVC-102",  e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/member/unlink")
    public ResponseEntity<String> unlink() {
        //TBD: Undo steps from link
        return new ResponseEntity<>("Unlink successful", HttpStatus.OK);
    }

    @PostMapping("/member/validate")
    public ResponseEntity<String> validate() {
        //TBD: Assess all checkpoints from where validate may get invoked, based on that
        //we can try to generalize the flow
        return new ResponseEntity<>("Validate successful", HttpStatus.OK);
    }
}

