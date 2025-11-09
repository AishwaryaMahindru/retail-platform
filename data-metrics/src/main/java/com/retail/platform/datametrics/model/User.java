package com.retail.platform.datametrics.model;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import lombok.*;

import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;

@Table("users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @PrimaryKey
    @Column("user_name")
    private String username;
    @Column(value = "password_hash")
    private String passwordHash; // bcrypt hash
    @Column(value = "full_name")
    private String fullName;
}
