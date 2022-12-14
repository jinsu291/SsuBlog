package com.example.SsuBlog.app.member.service;

import com.example.SsuBlog.app.base.exception.DataNotFoundException;
import com.example.SsuBlog.app.member.entity.Member;
import com.example.SsuBlog.app.member.exception.AlreadyJoinException;
import com.example.SsuBlog.app.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member join(String username, String password, String email) {
        if (memberRepository.findByUsername(username).isPresent()) {
            throw new AlreadyJoinException();
        }

        Member member = Member.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();

        memberRepository.save(member);

        return member;
    }

    @Transactional(readOnly = true)
    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public Member getMember(String username) {
        return this.memberRepository.findByUsername(username).orElseThrow(() -> new DataNotFoundException("username not found"));
    }
}
