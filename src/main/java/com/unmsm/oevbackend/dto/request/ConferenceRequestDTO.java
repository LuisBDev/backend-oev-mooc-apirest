package com.unmsm.oevbackend.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConferenceRequestDTO {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String imageUrl;
    private String category;

}
