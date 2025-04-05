package com.kop.morning.domain.football.controller;

import com.kop.morning.domain.football.dto.responseDto.StandingResponseDto;
import com.kop.morning.domain.football.service.FootBallService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/football")
@RequiredArgsConstructor
public class FootBallApiController {
    private final FootBallService footBallService;

    // 프론트에서 한번 받고 스케줄러를 통해 시간마다 최신화 하는 방향
    @GetMapping("/standing/{leagueId}")
    public ResponseEntity<StandingResponseDto> getStanding(@PathVariable(name = "leagueId") int leagueId) {
        return ResponseEntity.ok(footBallService.getStanding(leagueId));
    }
}
