package com.example.demo.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberPostDto {
    private String userName;
    private String password;
    private String email;
}
