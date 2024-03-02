package com.aws.ses.controller;


import com.aws.ses.model.EmailRecord;
import com.aws.ses.service.AwsSimpleEmailService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notify")
@RequiredArgsConstructor
public class AWSNotificationController {


    private final AwsSimpleEmailService simpleEmailService;

    @PostMapping("/ses")
    public ResponseEntity<String> sendEmailNotification(@RequestBody EmailRecord emailRecord) {

        simpleEmailService.sendEmail(emailRecord);
        return new ResponseEntity<>("Email Queued Successfully", HttpStatus.OK);

    }
}
