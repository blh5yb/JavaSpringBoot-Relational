package com.testproductapi.springbootrelational.repository;
import com.testproductapi.springbootrelational.entity.Email;

public interface EmailRepository {
    // Method
    // To send a simple email
    String sendSimpleMail(Email details);

    // Method
    // To send an email with attachment
    String sendMailWithAttachment(Email details);
}
