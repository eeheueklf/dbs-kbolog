package com.example.kbolog.dto;

import com.example.kbolog.entity.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MemberDTO {

    private Integer id;
    private String username;
    private LocalDate rootDate;
    private Integer rootTeamId;
    private String rootTeamName;
    private String rootTeamSponsor;

    public MemberDTO(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
        this.rootDate = member.getRootdate();
        this.rootTeamId = member.getRootTeam() != null ? member.getRootTeam().getTeamId() : null;
        this.rootTeamName = member.getRootTeam() != null ? member.getRootTeam().getTeamName() : null;
        this.rootTeamSponsor = member.getRootTeam() != null ? member.getRootTeam().getSponsor() : null;
    }
}
