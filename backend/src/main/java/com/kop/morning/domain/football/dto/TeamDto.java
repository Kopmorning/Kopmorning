package com.kop.morning.domain.football.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamDto {
    private Long id;
    private String name;
    private String shortName;
    private String tla;
    private String crest;
}
