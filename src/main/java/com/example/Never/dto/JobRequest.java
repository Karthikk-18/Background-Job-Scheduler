package com.example.Never.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class JobRequest {
    private String type;
    private String payload;
}
