package com.unmsm.oevbackend.dto.response;

import com.unmsm.oevbackend.model.enums.Status;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonResponseDTO {
    private Long id;

    private String title;
    private String videoUrl;
    private Integer duration;
    private Integer sequenceOrder;

    private Status status;

    private Long courseId;

}
