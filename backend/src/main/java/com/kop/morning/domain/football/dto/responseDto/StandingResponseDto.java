package com.kop.morning.domain.football.dto.responseDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kop.morning.domain.football.dto.CompetitionDto;
import com.kop.morning.domain.football.dto.SeasonDto;
import com.kop.morning.domain.football.dto.TableDto;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StandingResponseDto {
    private SeasonDto season;
    private CompetitionDto competition;
    private List<TableDto> tables;
}
