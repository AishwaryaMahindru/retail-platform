package com.retail.platform.datametrics.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories(basePackages = "com.retail.platform.datametrics.repository")
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Override
    protected String getKeyspaceName() { return "retail_data"; }

    @Override
    protected String getContactPoints() { return "localhost"; }

    @Override
    protected int getPort() { return 9042; }

    @Override
    public SchemaAction getSchemaAction() { return SchemaAction.CREATE_IF_NOT_EXISTS; }

    @Override
    public String[] getEntityBasePackages() {
        return new String[]{"com.retail.platform.datametrics.model"};
    }
}

