package com.unmsm.oevbackend.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonRequestDTO {

    @NotNull
    @NotEmpty
    private String title;

    private String videoUrl;

    private Integer sequenceOrder;

}
