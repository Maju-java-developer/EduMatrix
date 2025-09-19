package edu.matrix.co.entity.security;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "AssignCampusRoles")
public class AssignCampusRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer assignCampusRoleId;

    @ManyToOne
    @JoinColumn(name = "UserId", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "CampusRoleId", nullable = false)
    private CampusRoleEntity campusRoleEntity;

    @ManyToOne
    @JoinColumn(name = "CampusId", referencedColumnName = "campus_id", nullable = false)
    private CampusEntity campus;

    private Integer createdBy;
    private LocalDateTime createdDateTime;
    private Integer updatedBy;
    private LocalDateTime updatedDateTime;

    // getters and setters
}
