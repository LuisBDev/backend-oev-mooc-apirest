package com.unmsm.oevbackend.service.impl;

import com.unmsm.oevbackend.dto.request.LessonRequestDTO;
import com.unmsm.oevbackend.dto.response.LessonResponseDTO;
import com.unmsm.oevbackend.exception.AppException;
import com.unmsm.oevbackend.mapper.LessonMapper;
import com.unmsm.oevbackend.model.Course;
import com.unmsm.oevbackend.model.Lesson;
import com.unmsm.oevbackend.repository.ICourseRepository;
import com.unmsm.oevbackend.repository.ILessonRepository;
import com.unmsm.oevbackend.service.interfaces.ILessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements ILessonService {

    private final ILessonRepository lessonRepository;

    private final ICourseRepository courseRepository;

    private final LessonMapper lessonMapper;

    @Override
    public LessonResponseDTO createLesson(Long courseId, LessonRequestDTO lessonRequestDTO) {
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isEmpty()) {
            throw new AppException("Course with id " + courseId + " not found", HttpStatus.NOT_FOUND);
        }

        Course courseEntity = course.get();

        Lesson lesson = lessonMapper.requestDTOToEntity(lessonRequestDTO);

        lesson.setCourse(courseEntity);
        lesson.setCreatedAt(LocalDateTime.now());

        lessonRepository.save(lesson);

        return lessonMapper.entityToDTO(lesson);

    }

    @Override
    public List<LessonResponseDTO> findLessonsByCourseId(Long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isEmpty()) {
            throw new AppException("Course with id " + courseId + " not found", HttpStatus.NOT_FOUND);
        }

        List<Lesson> lessons = lessonRepository.findLessonsByCourseId(courseId);

        return lessonMapper.entityListToDTOList(lessons);

    }

    @Override
    public void deleteLessonById(Long id) {
        Optional<Lesson> lesson = lessonRepository.findById(id);
        if (lesson.isEmpty()) {
            throw new AppException("Lesson with id " + id + " not found", HttpStatus.NOT_FOUND);
        }

        lessonRepository.deleteById(id);
    }

    //TODO: update lesson
}
