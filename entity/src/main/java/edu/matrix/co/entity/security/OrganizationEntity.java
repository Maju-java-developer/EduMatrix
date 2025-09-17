package edu.matrix.co.entity.security;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "organizations", schema = "edumatrix")
public class OrganizationEntity {
    @Id
    @Column(name = "org_id", nullable = false)
    private Long organizationId;

    @Size(max = 200)
    @NotNull
    @Column(name = "org_name", nullable = false, length = 200)
    private String organizationName;

    @NotNull
    @ColumnDefault("current_timestamp()")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    // Relation to Schools
    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SchoolEntity> schools = new ArrayList<>();
}