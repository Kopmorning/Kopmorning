package com.kop.morning.domain.football.dto.teamDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamInfoDto {
    private Area area;
    private int id;
    private String name;
    private String shortName;
    private String tla;
    private String crest;
    private String address;
    private String website;
    private int founded;
    private String clubColors;
    private String venue;
    private List<Competition> runningCompetitions;
    private Coach coach;
    private List<Player> squad;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Area {
        private int id;
        private String name;
        private String code;
        private String flag;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Competition {
        private int id;
        private String name;
        private String code;
        private String type;
        private String emblem;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Coach {
        private int id;
        private String firstName;
        private String lastName;
        private String name;
        private String dateOfBirth;
        private String nationality;
        private Contract contract;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Contract {
            private String start;
            private String until;
        }
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Player {
        private int id;
        private String name;
        private String position;
        private String dateOfBirth;
        private String nationality;
    }
}

