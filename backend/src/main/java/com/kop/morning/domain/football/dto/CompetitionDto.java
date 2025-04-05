package com.kop.morning.domain.football.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompetitionDto {
    private Long id;
    private String name;
    private String code;
    private String type;
    private String emblem;
}
