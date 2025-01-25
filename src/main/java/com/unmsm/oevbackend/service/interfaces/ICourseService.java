package com.unmsm.oevbackend.service.interfaces;

import com.unmsm.oevbackend.dto.request.CourseRequestDTO;
import com.unmsm.oevbackend.dto.response.CourseResponseDTO;

import java.util.List;

public interface ICourseService {

    List<CourseResponseDTO> findAllCourses();

    CourseResponseDTO findCourseById(Long id);

//    CourseResponseDTO updateCourseById(Long id, UpdateCourseRequestDTO updateCourseRequestDTO);

    void deleteCourseById(Long id);

    CourseResponseDTO createCourse(Long userId, CourseRequestDTO courseRequestDTO);

}
