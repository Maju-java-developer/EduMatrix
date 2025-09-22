package edu.matrix.co.cores.security.repository;

import edu.matrix.co.entity.security.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {
    boolean existsByCnic(String cnic);
}
