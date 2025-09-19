package edu.matrix.co.entity.security;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "AssignSchoolRoles")
public class AssignSchoolRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer assignSchoolRoleId;

    @ManyToOne
    @JoinColumn(name = "UserId", referencedColumnName = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "SchoolRoleId", nullable = false)
    private SchoolRoleEntity schoolRoleEntity;

    @ManyToOne
    @JoinColumn(name = "SchoolId", referencedColumnName = "school_id", nullable = false)
    private SchoolEntity school;

    private Integer createdBy;
    private LocalDateTime createdDateTime;
    private Integer updatedBy;
    private LocalDateTime updatedDateTime;

    // getters and setters
}

