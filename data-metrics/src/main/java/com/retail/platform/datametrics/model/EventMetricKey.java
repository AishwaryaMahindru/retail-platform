package com.retail.platform.datametrics.model;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.*;

@PrimaryKeyClass
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventMetricKey {
    @PrimaryKeyColumn(name = "tenant_id", type = PARTITIONED)
    private String tenantId;

    @PrimaryKeyColumn(name = "event_type", ordinal = 0, type = CLUSTERED)
    private String eventType;

    @PrimaryKeyColumn(name = "product_id", ordinal = 1, type = CLUSTERED)
    private String productId;
}
