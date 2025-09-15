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

}