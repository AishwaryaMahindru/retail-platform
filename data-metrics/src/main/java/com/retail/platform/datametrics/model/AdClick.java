package com.retail.platform.datametrics.model;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.Column;


@Table("ad_clicks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdClick {

    @PrimaryKey
    private AdClickKey key;

    @Column("click_count")
    private long clickCount;
}


