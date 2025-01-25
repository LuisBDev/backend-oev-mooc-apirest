package com.unmsm.oevbackend.repository;

import com.unmsm.oevbackend.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEnrollmentRepository extends JpaRepository<Enrollment, Long> {

    @Query("select e from Enrollment e where e.user.id = ?1")
    List<Enrollment> findEnrollmentsByUserId(Long userId);

    @Query("select e from Enrollment e where e.course.id = ?1")
    List<Enrollment> findEnrollmentsByCourseId(Long courseId);

}
