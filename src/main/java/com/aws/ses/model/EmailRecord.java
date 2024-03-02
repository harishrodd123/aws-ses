package com.aws.ses.model;

public record EmailRecord(String fromEmail, String toEmail, String subject, String bodyText, String bodyHtml) {
}
