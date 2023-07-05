package com.example.demo.member;

import com.example.demo.member.dto.MemberPatchDto;
import com.example.demo.member.dto.MemberPostDto;
import com.example.demo.member.dto.MemberResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")

public interface MemberMapper {
    @Mapping(target = "memberId", ignore = true)  //warning: Unmapped target property 오류 경고 무시 처리 매핑되지 않은 속성
    Member memberPostDtoToMember(MemberPostDto memberPostDto);
    Member memberPatchDtoToMember(MemberPatchDto memberPatchDto);
    MemberResponseDto memberToMemberResponseDto(Member member);
    List<MemberResponseDto> memberToMemberResponseDtos(List<Member> members);
}
