package com.kop.morning.domain.member.dto.responseDto;

import com.kop.morning.domain.member.entity.Member;
import lombok.Data;

@Data
public class MemberResponseDto {
    private Long id;
    private String nickname;
    private String email;

    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.nickname = member.getNickname();
        this.email = member.getEmail();
    }
}
