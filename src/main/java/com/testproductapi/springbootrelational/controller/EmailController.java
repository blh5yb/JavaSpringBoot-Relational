package com.testproductapi.springbootrelational.controller;
// Importing required classes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.testproductapi.springbootrelational.entity.Email;
import com.testproductapi.springbootrelational.repository.RateLimitProtection;
import com.testproductapi.springbootrelational.service.EmailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

// Annotation
@RestController
@ConditionalOnProperty( prefix = "emailconnection", value = "enabled", havingValue = "true", matchIfMissing = false)
// Class
public class EmailController {

    @Autowired 
    private EmailService emailService;

    // Sending a simple Email
    @PostMapping("/sendMail")
    @RateLimitProtection
    @Operation(summary = "Send an email")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully sent email",
        content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
        @ApiResponse(responseCode = "429", description = "Too many requests",
        content = @Content)
    })
    @Tag(name="2. Email")
    public String
    sendMail(@RequestBody Email details)
    {
        String status
            = emailService.sendSimpleMail(details);

        return status;
    }

    // Sending email with attachment
    //@PostMapping("/api/sendMailWithAttachment")
    //@RateLimitProtection
    //public String sendMailWithAttachment(
    //    @RequestBody Email details)
    //{
    //    String status
    //        = emailService.sendMailWithAttachment(details);
//
    //    return status;
    //}
}