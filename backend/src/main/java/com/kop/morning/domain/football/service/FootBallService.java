package com.kop.morning.domain.football.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kop.morning.domain.football.dto.matchDto.MatchInfoDto;
import com.kop.morning.domain.football.dto.standingDto.StandingResponseDto;
import com.kop.morning.domain.football.dto.teamDto.TeamInfoDto;
import com.kop.morning.domain.football.entity.MatchInfo;
import com.kop.morning.domain.football.repository.MatchRepository;
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
    private final MatchRepository matchRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${football.apiToken}")
    private String apiToken;

    private static final String XAUTHTOKEN = "X-Auth-Token";
    private static final String STANDINGS = "standings";
    private static final String TABLE = "table";
    private static final String standingGetUrl = "http://api.football-data.org/v4/competitions/2021/standings";
    private static final String teamInfoGetUrl = "http://api.football-data.org/v4/teams/%d";
    private static final String matchListGetUrl = "http://api.football-data.org/v4/competitions/2021/matches";

    public StandingResponseDto getStanding() {
        try {
            JsonNode jsonNode = getJsonResponse(standingGetUrl);
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
            JsonNode jsonNode = getJsonResponse(url);

            return objectMapper.treeToValue(jsonNode, TeamInfoDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void saveMatchInfo() {
        try{
            JsonNode jsonNode = getJsonResponse(matchListGetUrl);
            List<MatchInfoDto> matchInfoDto = objectMapper
                    .readerForListOf(MatchInfoDto.class)
                    .readValue(jsonNode.get("matches"));

            for(MatchInfoDto match : matchInfoDto){
                matchRepository.save(new MatchInfo(match));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private JsonNode getJsonResponse(String url) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set(XAUTHTOKEN, apiToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return objectMapper.readTree(response.getBody());
    }
}
