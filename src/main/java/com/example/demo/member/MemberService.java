package com.example.demo.member;

import com.example.demo.exception.BusinessLogicException;
import com.example.demo.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

   private final MemberRepository memberRepository;

    //c
    public Member createMember(Member member){
        return memberRepository.save(member);
    }
    //r
    public Member findMember(long memberId) {return findVerifiedMemberByQuery(memberId);}

    public Page<Member> findMembers(int page, int size){
        return memberRepository.findAll(PageRequest.of(page, size,
                Sort.by("memberId").descending()));
    }

    //u
    public Member updateMember(Member member){
        Member findMember = findVerifiedMember(member.getMemberId());

        Optional.ofNullable(member.getUserName())
                .ifPresent(userName -> findMember.setUserName(userName));
        Optional.ofNullable(member.getEmail())
                .ifPresent(email -> findMember.setEmail(email));
        Optional.ofNullable(member.getPassword())
                .ifPresent(password -> findMember.setPassword(password));

        return memberRepository.save(findMember);
    }
    //d
    public void deleteMember(long memberId){
        Member member = findVerifiedMember(memberId);
        memberRepository.delete(member);
    }


    public Member findVerifiedMember(long memberId){
        Optional<Member> optionalMember = memberRepository.findByMember(memberId);
        Member findMember =
                optionalMember.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return findMember;
    }



    private Member findVerifiedMemberByQuery(long memberId){
        Optional<Member> optionalMember = memberRepository.findByMember(memberId);

        Member findMember =
                optionalMember.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return findMember;
    }
}
