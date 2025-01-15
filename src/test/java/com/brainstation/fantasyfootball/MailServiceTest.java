package com.brainstation.fantasyfootball;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
public class MailServiceTest {

    @Autowired
    private JavaMailSender mailSender;

//    public void sendMailTest(){
//
//        SimpleMailMessage message = new SimpleMailMessage();
//        Integer OTP = (int) (Math.random() * (9000) + 1000);
//        String fromMail = "shahinbeg80@gmail.com";
//        String subject = "Mail Verification from Fantasy Football!";
//        String text = "Your OTP code is: " + OTP + "\r\n Please verify by this OTP code. Don't share the code anyone!";
//        String toMail="ugjsoenbecs@spacehotline.com";
//        message.setFrom(fromMail);
//        message.setTo(toMail);
//        message.setSubject(subject);
//        message.setText(text);
//        mailSender.send(message);
//
//        Integer expectedValue=OTP;
//        Integer actualValue;
//    }
}
