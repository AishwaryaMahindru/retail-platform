package com.retail.platform.datametrics.repository;

import com.retail.platform.datametrics.model.AdClick;
import com.retail.platform.datametrics.model.AdClickKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdClickRepository extends CassandraRepository<AdClick, AdClickKey> {

    @Query("SELECT * FROM ad_clicks WHERE tenant_id = ?0 AND campaign_id = ?1")
    Optional<AdClick> findByTenantIdAndCampaignId(String tenantId, String campaignId);
}
