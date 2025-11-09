package com.retail.platform.datametrics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetricResponse {
    private String eventType;
    private String productId;
    private double metricValue;
}
