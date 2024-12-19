package com.devanktu.controller;

import com.devanktu.dto.GraphDto;
import com.devanktu.service.StatsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stats")
@CrossOrigin("*")
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/chart")
    public ResponseEntity<GraphDto> getChartDetails() {
        return ResponseEntity.ok(this.statsService.getCharData());
    }

    @GetMapping
    public ResponseEntity<?> getStats() {
        return ResponseEntity.ok(this.statsService.getStats());
    }

}
