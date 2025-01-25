package com.unmsm.oevbackend.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonRequestDTO {

    private String title;
    private String videoUrl;
    private Integer sequenceOrder;

}
