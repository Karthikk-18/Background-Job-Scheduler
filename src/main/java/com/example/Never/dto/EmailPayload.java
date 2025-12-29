package com.example.Never.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class EmailPayload {
    private String to;
    private String subject;
    private String body;
}
