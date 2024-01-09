package com.ait.grooming.controller.email;


import com.ait.grooming.model.email.EmailRequest;
import com.ait.grooming.service.mail.InternetMailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmailController {
    private final InternetMailSender mailSender;


    @PostMapping("/send-mail")
    public String sendEmail(@RequestBody EmailRequest request){
        mailSender.send(request.getTo(), request.getSubject(), request.getText());
        return  "Email sent successfully!";
    }
}