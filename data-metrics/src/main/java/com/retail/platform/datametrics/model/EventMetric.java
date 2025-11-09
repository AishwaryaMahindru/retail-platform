package com.retail.platform.datametrics.model;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("event_metrics")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventMetric {
    @PrimaryKey
    private EventMetricKey key;
    private double metricValue;
}
