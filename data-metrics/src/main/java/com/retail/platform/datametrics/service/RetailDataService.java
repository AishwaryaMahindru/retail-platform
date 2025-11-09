package com.retail.platform.datametrics.service;

import com.retail.platform.datametrics.dto.MetricResponse;
import com.retail.platform.datametrics.model.EventMetric;

import java.util.List;

public interface RetailDataService {
    List<MetricResponse> getMetricsForTenant(String tenantId, String eventTypeFilter);
    EventMetric saveMetric(EventMetric metric);
}
