package com.api.studyprojectjudgeserver.Domain.Project.Dto;

import com.api.studyprojectjudgeserver.Domain.Project.Entity.ProjectEntity;
import com.api.studyprojectjudgeserver.Domain.Project.Entity.ServiceStatus;
import com.api.studyprojectjudgeserver.Domain.Member.Entity.MemberEntity;
import lombok.*;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

/**
 * 프로젝트 정보를 저장할 때 쓰이는 Dto 입니다.
 * 해당 Dto 는 Entity 에 저장합니다.
 */
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectDto {
    private Long id;
    private String email;

    @Pattern(regexp = "^[a-z0-9]*$", message = "Only lowercase letters or numbers are allowed")
    private String serviceName;
    private String githubUrl;
    private String branch;
    private int servicePort;
    private String buildPackage;
    private ServiceStatus status;
    private String deployUrl;
    private String description;         // 프로젝트 설명(관리자 확인용)
    private String command;             // 프로젝트를 빌드하는데 쓰이는 명령어
    private MemberEntity memberEntity;


    public static ProjectDto fromEntity(ProjectEntity project){
        return new ProjectDto(
                project.getId(),
                project.getEmail(),
                project.getServiceName(),
                project.getGithubUrl(),
                project.getBranch(),
                project.getServicePort(),
                project.getBuildPackage(),
                project.getStatus(),
                project.getDeployUrl(),
                project.getDescription(),
                project.getCommand(),
                project.getMemberEntity()
        );
    }

    public ProjectEntity toEntity(){
        return ProjectEntity.builder()
                .id(id)
                .email(email)
                .serviceName(serviceName)
                .githubUrl(githubUrl)
                .branch(branch)
                .servicePort(servicePort)
                .buildPackage(buildPackage)
                .status(status)
                .deployUrl(deployUrl)
                .description(description)
                .command(command)
                .memberEntity(memberEntity)
                .build();
    }

    public ProjectEntity toEntity(MemberEntity memberEntity){
        return ProjectEntity.builder()
                .email(getEmail())
                .serviceName(getServiceName())
                .githubUrl(getGithubUrl())
                .branch(getBranch())
                .buildPackage(getBuildPackage())
                .servicePort(getServicePort())
                .status(ServiceStatus.CREATING)
                .deployUrl("Project Creating")
                .description(getDescription())
                .command(getCommand())
                .memberEntity(memberEntity)
                .build();
    }}
