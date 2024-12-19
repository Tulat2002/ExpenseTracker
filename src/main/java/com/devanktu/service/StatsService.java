package com.devanktu.service;

import com.devanktu.dto.GraphDto;
import com.devanktu.dto.StatsDto;

public interface StatsService {

    GraphDto getCharData();

    StatsDto getStats();

}
