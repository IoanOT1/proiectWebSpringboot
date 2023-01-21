package com.example.ex1.controller;

import com.example.ex1.model.SubscriptionPlan;
import com.example.ex1.service.PlanStatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
@Api(value = "/statistics", description = "Statistics regarding subscription plans included into contracts")
public class PlanStatisticsController {
    private final PlanStatisticsService planStatisticsService;

    public PlanStatisticsController(PlanStatisticsService planStatisticsService) {
        this.planStatisticsService = planStatisticsService;
    }

    @GetMapping
    @ApiOperation("This will return the n most common subscription " +
            "plans included into actual contracts, the integer n is given")
    public ResponseEntity<List<SubscriptionPlan>> getMostCommonNSubscriptions(@RequestParam Integer n) {
        List<SubscriptionPlan> mostCommonPlans =  planStatisticsService.getNMostPopularSubscriptionPlan(n);
        return ResponseEntity.ok().body(mostCommonPlans);
    }
}
