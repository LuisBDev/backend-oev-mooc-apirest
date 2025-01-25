package com.unmsm.oevbackend.mapper;

import com.unmsm.oevbackend.dto.response.EnrollmentResponseDTO;
import com.unmsm.oevbackend.model.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {

    @Mapping(source = "course.id", target = "courseId")
    @Mapping(source = "user.id", target = "userId")
    EnrollmentResponseDTO entityToResponseDTO(Enrollment enrollment);

    List<EnrollmentResponseDTO> entityListToResponseDTOList(List<Enrollment> enrollments);

}
