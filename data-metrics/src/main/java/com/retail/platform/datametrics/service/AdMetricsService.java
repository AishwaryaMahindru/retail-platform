package com.retail.platform.datametrics.service;

public interface AdMetricsService {
    long getAdClicksForCampaign(String tenantId, String campaignId);
    long incrementAdClick(String tenantId, String campaignId);
}
