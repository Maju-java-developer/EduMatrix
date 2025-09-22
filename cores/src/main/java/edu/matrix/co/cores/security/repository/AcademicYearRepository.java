package edu.matrix.co.cores.security.repository;

import edu.matrix.co.entity.security.AcademicYearEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AcademicYearRepository extends JpaRepository<AcademicYearEntity, Integer> {
    List<AcademicYearEntity> findBySchool_SchoolId(Long schoolId);
}
