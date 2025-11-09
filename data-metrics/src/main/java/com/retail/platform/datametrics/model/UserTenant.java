package com.retail.platform.datametrics.model;

import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import lombok.*;
import org.springframework.data.cassandra.core.mapping.Table;

import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.*;

@Table("user_tenant")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTenant {

    @PrimaryKeyColumn(name = "user_name", type = PARTITIONED)
    private String username;

    @PrimaryKeyColumn(name = "tenant_id", ordinal = 0, type = CLUSTERED)
    private String tenantId;

    private String role; // e.g., "ADMIN", "USER" - to do make an enum
}
