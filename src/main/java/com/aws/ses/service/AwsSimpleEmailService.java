package com.aws.ses.service;


import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import com.aws.ses.model.EmailRecord;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Slf4j
@Component
public class AwsSimpleEmailService {

    private final AmazonSimpleEmailService simpleEmailService;


    @Async
    public void sendEmail(EmailRecord emailRecord) {

        SendEmailRequest sendEmailRequest = new SendEmailRequest();

        // make sure from and to emails or domain are verified in AWS SES
        sendEmailRequest
                .withSource(emailRecord.fromEmail())
                .withDestination(new Destination().withToAddresses(emailRecord.toEmail()))
                .withMessage(new Message()
                        .withSubject(new Content().withData(emailRecord.subject()))
                        .withBody(new Body().withText(new Content(emailRecord.bodyText()))));

        SendEmailResult result = simpleEmailService.sendEmail(sendEmailRequest);

        log.info("Email Sent !!!");
        log.info("Message Id is {} ", result.getMessageId());
    }
}
