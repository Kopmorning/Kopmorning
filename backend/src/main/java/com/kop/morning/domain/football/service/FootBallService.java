package com.kop.morning.domain.football.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kop.morning.domain.football.dto.standingDto.StandingResponseDto;
import com.kop.morning.domain.football.dto.teamDto.TeamInfoDto;
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
    private final String XAUTHTOKEN = "X-Auth-Token";
    private final String STANDINGS = "standings";
    private final String TABLE = "table";
    private final String standingGetUrl = "http://api.football-data.org/v4/competitions/%d/standings";
    private final String teamInfoGetUrl = "http://api.football-data.org/v4/teams/%d";

    private final RestTemplate restTemplate = new RestTemplate();  // Optional: Bean으로 등록해도 됨
    private final ObjectMapper objectMapper = new ObjectMapper();

    public StandingResponseDto getStanding(int leagueId) {
        String url = String.format(standingGetUrl, leagueId);

        try {
            JsonNode jsonNode = getJsonNode(url);
            JsonNode standings = jsonNode.path(STANDINGS);

            List<StandingResponseDto.TableDto> tableDto = objectMapper
                    .readerForListOf(StandingResponseDto.TableDto.class)
                    .readValue(standings.get(0).get(TABLE));

            StandingResponseDto standingResponseDto = objectMapper.treeToValue(jsonNode, StandingResponseDto.class);
            standingResponseDto.setTables(tableDto);

            return standingResponseDto;
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse standing", e);
        }
    }

    public TeamInfoDto getTeamInfo(int teamId) {
        String url = String.format(teamInfoGetUrl, teamId);

        try {
            JsonNode jsonNode = getJsonNode(url);

            return objectMapper.treeToValue(jsonNode, TeamInfoDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private JsonNode getJsonNode(String url) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set(XAUTHTOKEN, apiToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return objectMapper.readTree(response.getBody());
    }
}
