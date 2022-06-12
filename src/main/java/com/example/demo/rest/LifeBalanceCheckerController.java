package com.example.demo.rest;

import com.example.demo.services.lifebalancechecker.LifeBalanceCheckerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController("/lifebalancecheck")
@RequiredArgsConstructor
@Validated
public class LifeBalanceCheckerController {

    private final LifeBalanceCheckerService lifeBalanceCheckerService;

    @GetMapping("/lifebalancecheck/islifebalanced")
    ResponseEntity<LifeBalanceDto> getLifeBalance(@RequestParam @Min(0) @Max(1) double desiredSelfCareRatio) {
        return ResponseEntity.ok(lifeBalanceCheckerService.balanceLife(desiredSelfCareRatio));
    }
}
