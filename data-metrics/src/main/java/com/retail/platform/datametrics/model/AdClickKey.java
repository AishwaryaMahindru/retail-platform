package com.retail.platform.datametrics.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.CLUSTERED;
import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;

@PrimaryKeyClass
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdClickKey {
    @PrimaryKeyColumn(name = "tenant_id", type = PARTITIONED)
    private String tenantId;

    @PrimaryKeyColumn(name = "campaign_id", type = CLUSTERED)
    private String campaignId;
}