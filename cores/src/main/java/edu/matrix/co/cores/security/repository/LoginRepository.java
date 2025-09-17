package edu.matrix.co.cores.security.repository;


import edu.matrix.co.entity.security.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Long> {
    Optional<LoginEntity> findByUsername(String username);
    Optional<LoginEntity> findByUserId(Long userId);
}