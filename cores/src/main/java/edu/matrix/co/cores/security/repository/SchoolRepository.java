package edu.matrix.co.cores.security.repository;

import edu.matrix.co.entity.security.SchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<SchoolEntity, Long> {
}
