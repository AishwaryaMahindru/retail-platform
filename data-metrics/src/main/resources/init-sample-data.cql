
CREATE KEYSPACE IF NOT EXISTS retail_data
    WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

USE retail_data;

CREATE TABLE IF NOT EXISTS tenants (
                                       tenant_id text PRIMARY KEY,
                                       name text
);

CREATE TABLE IF NOT EXISTS users (
                                     user_name text PRIMARY KEY,
                                     password_hash text,
                                     full_name text
);

CREATE TABLE IF NOT EXISTS user_tenant (
                                            user_name text,
                                            tenant_id text,
                                            role text,
                                            PRIMARY KEY (user_name, tenant_id)
);

CREATE TABLE IF NOT EXISTS event_metrics (
                                             tenant_id text,
                                             event_type text,
                                             product_id text,
                                             metric_value double,
                                             PRIMARY KEY ((tenant_id, event_type), product_id)
);

CREATE TABLE IF NOT EXISTS ad_clicks (
                           tenant_id text,
                           campaign_id text,
                           click_count bigint,
                           PRIMARY KEY (tenant_id, campaign_id)
);


--  Sample tenant
INSERT INTO tenants (tenant_id, name) VALUES ('retailerA', 'Retailer A');

-- Sample user (john)
-- Password: "admin123"
INSERT INTO users (user_name, password_hash, full_name)
VALUES (
           'john',
           '$2a$12$TtGmlZbb5oWSoBBbgv.oDOZAuM41eGbLtBoGesUUeTRzc.1SZS9PC',
           'John Doe'
       );

-- Map user to tenant with ADMIN role
INSERT INTO user_tenant (user_name, tenant_id, role)
VALUES ('john', 'retailerA', 'ADMIN');

INSERT INTO event_metrics (tenant_id, event_type, product_id, metric_value)
VALUES ('retailerA', 'view', 'P1001', 15.0);

INSERT INTO event_metrics (tenant_id, event_type, product_id, metric_value)
VALUES ('retailerA', 'add_to_cart', 'P1002', 8.0);

INSERT INTO ad_clicks (tenant_id, campaign_id, click_count)
VALUES ('retailerA', 'CAMPAIGN_1001', 250);
