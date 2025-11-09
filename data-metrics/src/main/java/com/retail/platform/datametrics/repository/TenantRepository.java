package com.retail.platform.datametrics.repository;

import com.retail.platform.datametrics.model.Tenant;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends CassandraRepository<Tenant, String> {
}
