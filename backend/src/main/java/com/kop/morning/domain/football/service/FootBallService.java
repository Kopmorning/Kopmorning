package com.kop.morning.domain.football.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kop.morning.domain.football.dto.TableDto;
import com.kop.morning.domain.football.dto.responseDto.StandingResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FootBallService {
    @Value("${football.apiToken}")
    private String apiToken;
    private final String standingGetUrl = "http://api.football-data.org/v4/competitions/%d/standings";

    public StandingResponseDto getStanding(int leagueId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        String url = String.format(standingGetUrl, leagueId);


        headers.set("X-Auth-Token", apiToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            JsonNode standings = jsonNode.get("standings");
            List<TableDto> tableDto = objectMapper
                    .readerForListOf(TableDto.class)
                    .readValue(standings.get(0).get("table"));
            StandingResponseDto standingResponseDto = objectMapper.treeToValue(jsonNode, StandingResponseDto.class);
            standingResponseDto.setTables(tableDto);

            return standingResponseDto;
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse standing", e);
        }
    }
}
