package com.ait.grooming.controller.email;


import com.ait.grooming.model.email.EmailRequest;
import com.ait.grooming.service.mail.InternetMailSender;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mail_sender")
@AllArgsConstructor
@Data
public class EmailController {
    private final InternetMailSender mailSender;


    @PostMapping
    public String sendEmail(@RequestBody EmailRequest request){
        mailSender.send(request.getTo(), request.getSubject(), request.getText());
        return  "Email sent successfully!";
    }
}