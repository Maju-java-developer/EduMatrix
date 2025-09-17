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
@Table(name = "schools", schema = "edumatrix")
public class SchoolEntity {
    @Id
    @Column(name = "school_id", nullable = false)
    private Long id;

    @Size(max = 200)
    @NotNull
    @Column(name = "school_name", nullable = false, length = 200)
    private String schoolName;

    @NotNull
    @ColumnDefault("current_timestamp()")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    // Relation to campuses
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "org_id", nullable = false)
    private OrganizationEntity organization;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CampusEntity> campuses = new ArrayList<>();
}