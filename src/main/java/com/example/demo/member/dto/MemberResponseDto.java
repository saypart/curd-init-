package com.example.demo.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberResponseDto {
    private long memberId;
    private String userName;
    private String password;
    private String email;
}
