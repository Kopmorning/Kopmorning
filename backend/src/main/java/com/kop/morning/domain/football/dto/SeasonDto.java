package com.kop.morning.domain.football.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SeasonDto {
    private Long id;
    private String startDate;
    private String endDate;
    private String currentMatchDay;
    private String winner;
}
