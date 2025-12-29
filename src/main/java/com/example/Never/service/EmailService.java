package com.example.Never.service;

import com.example.Never.dto.EmailPayload;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void sendEmail(EmailPayload payload){

        System.out.println("Sending Email to "+payload.getTo());
        System.out.println("Subject "+payload.getSubject());
        System.out.println("Body "+payload.getBody());

        try{
            Thread.sleep(3000);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}
