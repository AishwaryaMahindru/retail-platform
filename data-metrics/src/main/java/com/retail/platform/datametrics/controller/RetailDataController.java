package com.retail.platform.datametrics.controller;

import com.retail.platform.datametrics.dto.MetricResponse;
import com.retail.platform.datametrics.model.EventMetric;
import com.retail.platform.datametrics.service.AuthService;
import com.retail.platform.datametrics.service.RetailDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/v1/metrics")
@RequiredArgsConstructor
public class RetailDataController {

    private final RetailDataService retailDataService;
    private final AuthService authService;

    @GetMapping
    public ResponseEntity<List<MetricResponse>> getMetrics(
            @RequestHeader("X-Tenant-Id") String tenantId,
            Authentication authentication,
            @RequestParam(required = false) String eventType) {

        String username = (String) authentication.getPrincipal();
        if (!authService.userHasAccessToTenant(username, tenantId)) {
            return ResponseEntity.status(403).build();
        }

        List<MetricResponse> res = retailDataService.getMetricsForTenant(tenantId, eventType);
        return ResponseEntity.ok(res);
    }

    @PostMapping
    public ResponseEntity<EventMetric> saveMetric(
            @RequestHeader("X-Tenant-Id") String tenantId,
            @RequestBody EventMetric metric,
            Authentication authentication) {

        String username = (String) authentication.getPrincipal();
        if (!authService.userHasAccessToTenant(username, tenantId)) {
            return ResponseEntity.status(403).build();
        }

        metric.getKey().setTenantId(tenantId);
        EventMetric saved = retailDataService.saveMetric(metric);
        return ResponseEntity.ok(saved);
    }
}
