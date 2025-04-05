package com.kop.morning.domain.football.dto.standingDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StandingResponseDto {
    private SeasonDto season;
    private CompetitionDto competition;
    private List<TableDto> tables;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SeasonDto {
        private Long id;
        private String startDate;
        private String endDate;
        private String currentMatchDay;
        private String winner;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TableDto {
        private int position;
        private TeamDto team;
        private int playedGames;
        private String form;
        private int won;
        private int draw;
        private int lost;
        private int points;
        private int goalsFor;
        private int goalsAgainst;
        private int goalDifference;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CompetitionDto {
        private Long id;
        private String name;
        private String code;
        private String type;
        private String emblem;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TeamDto {
        private Long id;
        private String name;
        private String shortName;
        private String tla;
        private String crest;
    }
}
