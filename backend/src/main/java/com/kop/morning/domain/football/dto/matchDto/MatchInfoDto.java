package com.kop.morning.domain.football.dto.matchDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kop.morning.domain.football.dto.CompetitionDto;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchInfoDto {
    private Area area;
    private CompetitionDto competition;
    private Season season;
    private Long id;
    private String utcDate;
    private String status;
    private Integer matchday;
    private String stage;
    private String group;
    private String lastUpdated;
    private Team homeTeam;
    private Team awayTeam;
    private Score score;
    private List<Referee> referees;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Area {
        private Long id;
        private String name;
        private String code;
        private String flag;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Season {
        private Long id;
        private String startDate;
        private String endDate;
        private Integer currentMatchday;
        private Object winner; // 확장 시 Winner 클래스로 교체 가능
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Team {
        private Long id;
        private String name;
        private String shortName;
        private String tla;
        private String crest;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Score {
        private String winner;
        private String duration;
        private ScoreDetail fullTime;
        private ScoreDetail halfTime;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ScoreDetail {
        private Integer home;
        private Integer away;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Referee {
        private Long id;
        private String name;
        private String type;
        private String nationality;
    }
}

