package com.api.studyprojectjudgeserver.Domain.Member.Dto;

import com.api.studyprojectjudgeserver.Domain.Member.Entity.MemberEntity;
import com.api.studyprojectjudgeserver.Domain.Member.Entity.Roles;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.UUID;

/**
 * 회원가입할 때 쓰이는 DTO입니다.
 * @author : 황시준
 * @since : 1.0
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDto {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String nickname;

    @NotBlank
    private String name;

    @NotBlank
    @Size(min = 8)
    private String pwd;

    @NotBlank
    private String phone;

    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .email(getEmail())
                .nickname(getNickname())
                .name(getName())
                .pwd(getPwd())
                .userId(UUID.randomUUID().toString())
                .phone(getPhone())
                .roles(Collections.singletonList(Roles.USER.getId()))
                .build();
    }
}