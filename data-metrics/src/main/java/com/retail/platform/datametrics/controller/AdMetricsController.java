package com.retail.platform.datametrics.controller;

import com.retail.platform.datametrics.service.AdMetricsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ad")
@RequiredArgsConstructor
public class AdMetricsController {

    private final AdMetricsService adMetricsService;

    @GetMapping("/{campaignId}/clicks")
    public ResponseEntity<?> getAdClicks(
            @RequestHeader("X-Tenant-Id") String tenantId,
            @PathVariable String campaignId) {

        long clicks = adMetricsService.getAdClicksForCampaign(tenantId, campaignId);
        return ResponseEntity.ok()
                .body(String.format("Campaign %s has %d clicks for tenant %s",
                        campaignId, clicks, tenantId));
    }

    @PostMapping("/{campaignId}/click")
    public ResponseEntity<?> addAdClick(
            @RequestHeader("X-Tenant-Id") String tenantId,
            @PathVariable String campaignId) {

        long updatedCount = adMetricsService.incrementAdClick(tenantId, campaignId);
        return ResponseEntity.ok()
                .body(String.format("Campaign %s click count incremented to %d for tenant %s",
                        campaignId, updatedCount, tenantId));
    }
}
