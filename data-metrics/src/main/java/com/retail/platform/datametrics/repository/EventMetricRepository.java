package com.retail.platform.datametrics.repository;

import com.retail.platform.datametrics.model.EventMetric;
import com.retail.platform.datametrics.model.EventMetricKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventMetricRepository extends CassandraRepository<EventMetric, EventMetricKey> {
    List<EventMetric> findByKeyTenantId(String tenantId);
    List<EventMetric> findByKeyTenantIdAndKeyEventType(String tenantId, String eventType);
}
