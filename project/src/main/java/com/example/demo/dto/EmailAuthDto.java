package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
public class EmailAuthDto {

    @NotEmpty(message = "이메일을 입력해주세요.")
    public String email;

}
