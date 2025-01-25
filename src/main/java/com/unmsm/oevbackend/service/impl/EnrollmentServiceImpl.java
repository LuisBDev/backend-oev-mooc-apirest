package com.unmsm.oevbackend.service.impl;

import com.unmsm.oevbackend.dto.request.EnrollmentRequestDTO;
import com.unmsm.oevbackend.dto.response.EnrollmentResponseDTO;
import com.unmsm.oevbackend.exception.AppException;
import com.unmsm.oevbackend.mapper.EnrollmentMapper;
import com.unmsm.oevbackend.model.Course;
import com.unmsm.oevbackend.model.Enrollment;
import com.unmsm.oevbackend.model.User;
import com.unmsm.oevbackend.repository.ICourseRepository;
import com.unmsm.oevbackend.repository.IEnrollmentRepository;
import com.unmsm.oevbackend.repository.IUserRepository;
import com.unmsm.oevbackend.service.interfaces.IEnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements IEnrollmentService {

    private final IEnrollmentRepository enrollmentRepository;

    private final ICourseRepository courseRepository;

    private final IUserRepository userRepository;

    private final EnrollmentMapper enrollmentMapper;

    @Override
    public EnrollmentResponseDTO createEnrollment(EnrollmentRequestDTO enrollmentRequestDTO) {
        Optional<Course> course = courseRepository.findById(enrollmentRequestDTO.getCourseId());
        if (course.isEmpty()) {
            throw new AppException("Course with id " + enrollmentRequestDTO.getCourseId() + " not found", HttpStatus.NOT_FOUND);
        }
        Optional<User> user = userRepository.findById(enrollmentRequestDTO.getUserId());
        if (user.isEmpty()) {
            throw new AppException("User with id " + enrollmentRequestDTO.getUserId() + " not found", HttpStatus.NOT_FOUND);
        }
        Enrollment enrollmentEntity = Enrollment.builder()
                .course(course.get())
                .user(user.get())
                .status("ACTIVE")
                .progress(0.0)
                .enrollmentDate(LocalDateTime.now())
                .build();

        enrollmentRepository.save(enrollmentEntity);
        return enrollmentMapper.entityToResponseDTO(enrollmentEntity);
    }

    @Override
    public EnrollmentResponseDTO findEnrollmentById(Long enrollmentId) {
        Optional<Enrollment> enrollment = enrollmentRepository.findById(enrollmentId);
        if (enrollment.isEmpty()) {
            throw new AppException("Enrollment with id " + enrollmentId + " not found", HttpStatus.NOT_FOUND);
        }
        return enrollmentMapper.entityToResponseDTO(enrollment.get());
    }

    @Override
    public List<EnrollmentResponseDTO> findEnrollmentsByUserId(Long userId) {
        List<Enrollment> enrollments = enrollmentRepository.findEnrollmentsByUserId(userId);
        return enrollmentMapper.entityListToResponseDTOList(enrollments);

    }

    @Override
    public List<EnrollmentResponseDTO> findEnrollmentsByCourseId(Long courseId) {
        List<Enrollment> enrollments = enrollmentRepository.findEnrollmentsByCourseId(courseId);
        return enrollmentMapper.entityListToResponseDTOList(enrollments);
    }

    @Override
    public void deleteEnrollmentById(Long enrollmentId) {
        Optional<Enrollment> enrollment = enrollmentRepository.findById(enrollmentId);
        if (enrollment.isEmpty()) {
            throw new AppException("Enrollment with id " + enrollmentId + " not found", HttpStatus.NOT_FOUND);
        }
        enrollmentRepository.deleteById(enrollmentId);
    }
}
