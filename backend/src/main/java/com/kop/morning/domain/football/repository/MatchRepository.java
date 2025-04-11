package com.kop.morning.domain.football.repository;

import com.kop.morning.domain.football.entity.MatchInfo;
import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<MatchInfo, Long> {
}
