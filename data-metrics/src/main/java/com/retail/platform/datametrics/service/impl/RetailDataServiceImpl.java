package com.retail.platform.datametrics.service.impl;

import com.retail.platform.datametrics.dto.MetricResponse;
import com.retail.platform.datametrics.model.EventMetric;
import com.retail.platform.datametrics.model.EventMetricKey;
import com.retail.platform.datametrics.repository.EventMetricRepository;
import com.retail.platform.datametrics.service.RetailDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RetailDataServiceImpl implements RetailDataService {

    private final EventMetricRepository repository;

    @Override
    public List<MetricResponse> getMetricsForTenant(String tenantId, String eventTypeFilter) {
        List<EventMetric> rows;
        if (eventTypeFilter == null) {
            rows = repository.findByKeyTenantId(tenantId);
        } else {
            rows = repository.findByKeyTenantIdAndKeyEventType(tenantId, eventTypeFilter);
        }

        return rows.stream()
                .map(r -> new MetricResponse(r.getKey().getEventType(), r.getKey().getProductId(), r.getMetricValue()))
                .collect(Collectors.toList());
    }

    @Override
    public EventMetric saveMetric(EventMetric metric) {
        return repository.save(metric);
    }
}
