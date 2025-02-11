package com.unmsm.oevbackend.mapper;

import com.unmsm.oevbackend.dto.request.LessonRequestDTO;
import com.unmsm.oevbackend.dto.response.LessonResponseDTO;
import com.unmsm.oevbackend.model.Course;
import com.unmsm.oevbackend.model.Lesson;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-11T16:29:39-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
@Component
public class LessonMapperImpl implements LessonMapper {

    @Override
    public LessonResponseDTO entityToDTO(Lesson lesson) {
        if ( lesson == null ) {
            return null;
        }

        LessonResponseDTO.LessonResponseDTOBuilder lessonResponseDTO = LessonResponseDTO.builder();

        lessonResponseDTO.courseId( lessonCourseId( lesson ) );
        lessonResponseDTO.id( lesson.getId() );
        lessonResponseDTO.title( lesson.getTitle() );
        lessonResponseDTO.videoUrl( lesson.getVideoUrl() );
        lessonResponseDTO.duration( lesson.getDuration() );
        lessonResponseDTO.sequenceOrder( lesson.getSequenceOrder() );
        lessonResponseDTO.status( lesson.getStatus() );

        return lessonResponseDTO.build();
    }

    @Override
    public List<LessonResponseDTO> entityListToDTOList(List<Lesson> lessonList) {
        if ( lessonList == null ) {
            return null;
        }

        List<LessonResponseDTO> list = new ArrayList<LessonResponseDTO>( lessonList.size() );
        for ( Lesson lesson : lessonList ) {
            list.add( entityToDTO( lesson ) );
        }

        return list;
    }

    @Override
    public Lesson requestDTOToEntity(LessonRequestDTO lessonRequestDTO) {
        if ( lessonRequestDTO == null ) {
            return null;
        }

        Lesson.LessonBuilder lesson = Lesson.builder();

        lesson.title( lessonRequestDTO.getTitle() );
        lesson.videoUrl( lessonRequestDTO.getVideoUrl() );
        lesson.sequenceOrder( lessonRequestDTO.getSequenceOrder() );

        return lesson.build();
    }

    private Long lessonCourseId(Lesson lesson) {
        if ( lesson == null ) {
            return null;
        }
        Course course = lesson.getCourse();
        if ( course == null ) {
            return null;
        }
        Long id = course.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
