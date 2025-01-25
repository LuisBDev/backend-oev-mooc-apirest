package com.unmsm.oevbackend.service.impl;

import com.unmsm.oevbackend.dto.request.CourseRequestDTO;
import com.unmsm.oevbackend.dto.response.CourseResponseDTO;
import com.unmsm.oevbackend.exception.AppException;
import com.unmsm.oevbackend.exception.UserNotFoundException;
import com.unmsm.oevbackend.mapper.CourseMapper;
import com.unmsm.oevbackend.model.Course;
import com.unmsm.oevbackend.model.User;
import com.unmsm.oevbackend.repository.ICourseRepository;
import com.unmsm.oevbackend.repository.IUserRepository;
import com.unmsm.oevbackend.service.interfaces.ICourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements ICourseService {

    private final ICourseRepository courseRepository;

    private final CourseMapper courseMapper;

    private final IUserRepository userRepository;

    @Override
    public List<CourseResponseDTO> findAllCourses() {
        List<Course> allCourses = courseRepository.findAll();
        return courseMapper.entityListToDTOList(allCourses);
    }

    @Override
    public CourseResponseDTO findCourseById(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isEmpty()) {
            throw new AppException("Course with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
        return courseMapper.entityToDTO(course.get());
    }

    @Override
    public void deleteCourseById(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isEmpty()) {
            throw new AppException("Course with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
        courseRepository.deleteById(id);
    }

    @Override
    public CourseResponseDTO createCourse(Long userId, CourseRequestDTO courseRequestDTO) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User with id " + userId + " not found", HttpStatus.NOT_FOUND);
        }
        Course course = courseMapper.requestDTOToEntity(courseRequestDTO);
        course.setUser(user.get());
        course.setCreationDate(LocalDateTime.now());
        course.setTotalStudents(0);

        courseRepository.save(course);
        return courseMapper.entityToDTO(course);
    }
}
