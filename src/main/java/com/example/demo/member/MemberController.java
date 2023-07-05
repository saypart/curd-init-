package com.example.demo.member;

import com.example.demo.member.dto.MemberPatchDto;
import com.example.demo.member.dto.MemberPostDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.*;

import java.util.List;

@AllArgsConstructor // 필드에 쓴 모든생성자 생성
@RestController
@RequestMapping("/v1/member")
public class MemberController {
    private MemberService memberService;
    private MemberMapper memberMapper;

// @AllArgsConstructor 사용 안할시 생성자 필요
//    public MemberController(MemberService memberService, MemberMapper memberMapper){
//        this.memberService = memberService;
//        this.memberMapper =memberMapper;
//    }

    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto memberPostDto){
        Member member = memberService.createMember(memberMapper.memberPostDtoToMember(memberPostDto));

        return new ResponseEntity<>(
                new SingleResponseDto<>(memberMapper.memberToMemberResponseDto(member)),
                HttpStatus.CREATED);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") long memberId) {
        Member member = memberService.findMember(memberId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(memberMapper.memberToMemberResponseDto(member)),
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers(@Positive @RequestParam int page,
                                     @Positive @RequestParam int size) {
        Page<Member> pageMembers = memberService.findMembers(page - 1, size);
        List<Member> members = pageMembers.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(memberMapper.memberToMemberResponseDtos(members),
                        pageMembers),
                HttpStatus.OK);
    }


    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") @Positive long memberId,
                                      @Valid @RequestBody MemberPatchDto memberPatchDto) {
//        memberPatchDto.setMemberId(memberId); //@setter 사용시
        memberPatchDto = MemberPatchDto.builder()
                .memberId(memberId)
                .password(memberPatchDto.getPassword())
                .userName(memberPatchDto.getUserName())
                .email(memberPatchDto.getEmail())
                .build();

        Member member = memberService.updateMember(memberMapper.memberPatchDtoToMember(memberPatchDto));

        return new ResponseEntity<>(
                new SingleResponseDto<>(memberMapper.memberToMemberResponseDto(member)),
                HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") long memberId) {
        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
