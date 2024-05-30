package com.earlyword.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class Member {
    private Long userId;
    private String email;
    private String password;
    private String nickname;
    private String phone;
    private LocalDateTime createDate;

    public Member(String email, String password, String nickname, String phone) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phone = phone;
    }
}