package com.retail.platform.datametrics.repository;

import com.retail.platform.datametrics.model.UserTenant;
import com.retail.platform.datametrics.model.UserTenant;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UserTenantRepository extends CassandraRepository<UserTenant, String> {
    List<UserTenant> findByUsername(String username);

    default boolean userBelongsToTenant(org.springframework.data.cassandra.core.CassandraAdminOperations template, String username, String tenantId) {
        return findByUsername(username).stream().anyMatch(ut -> ut.getTenantId().equals(tenantId));
    }
}
