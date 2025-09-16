package edu.matrix.co.entity.security;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "organizations", schema = "edumatrix")
public class OrganizationEntity {
    @Id
    @Column(name = "org_id", nullable = false)
    private Long orgId;

    @Size(max = 200)
    @NotNull
    @Column(name = "org_name", nullable = false, length = 200)
    private String orgName;

    @NotNull
    @ColumnDefault("current_timestamp()")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

}