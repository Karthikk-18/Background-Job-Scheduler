package com.example.Never.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.annotation.Scheduled;

@Data
@Setter
@Getter
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
}
