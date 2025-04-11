package com.kop.morning.domain.football.entity;

import com.kop.morning.domain.football.dto.matchDto.MatchInfoDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchInfo {

    @Id
    private Long matchId; // API의 match.id
    private String competitionName; // e.g. Premier League
    private String seasonStartDate;
    private String seasonEndDate;
    private Integer matchday;
    private String utcDate;
    private String status;
    private String homeTeamName;
    private String awayTeamName;
    private Integer homeScore;
    private Integer awayScore;
    private String stage;
    private String refereeName;
    private String areaName;

    public MatchInfo(MatchInfoDto matchInfoDto) {
        this.matchId = matchInfoDto.getId();
        this.competitionName = matchInfoDto.getCompetition().getName();
        this.seasonStartDate = matchInfoDto.getSeason().getStartDate();
        this.seasonEndDate = matchInfoDto.getSeason().getEndDate();
        this.matchday = matchInfoDto.getMatchday();
        this.utcDate = matchInfoDto.getUtcDate();
        this.status = matchInfoDto.getStatus();
        this.homeTeamName = matchInfoDto.getHomeTeam().getName();
        this.awayTeamName = matchInfoDto.getAwayTeam().getName();
        this.homeScore = matchInfoDto.getScore().getFullTime().getHome();
        this.awayScore = matchInfoDto.getScore().getFullTime().getAway();
        this.stage = matchInfoDto.getStage();
        this.refereeName = matchInfoDto.getReferees().get(0).getName();
        this.areaName = matchInfoDto.getArea().getName();
    }
}

