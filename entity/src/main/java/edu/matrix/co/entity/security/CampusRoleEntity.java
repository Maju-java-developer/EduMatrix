package edu.matrix.co.entity.security;

import enums.RoleType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "CampusRoles")
public class CampusRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer campusRoleId;

    @ManyToOne
    @JoinColumn(name = "ParentRoleId")
    private CampusRoleEntity parentRole;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RoleType roleType;

    @Column(nullable = false, length = 100)
    private String roleName;

    private String description;

    private Integer createdBy;
    private LocalDateTime createdDateTime;
    private Integer updatedBy;
    private LocalDateTime updatedDateTime;

    // getters and setters
}
