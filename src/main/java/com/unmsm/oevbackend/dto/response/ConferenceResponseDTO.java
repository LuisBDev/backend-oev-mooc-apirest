package com.unmsm.oevbackend.dto.response;

import com.unmsm.oevbackend.model.Registration;
import com.unmsm.oevbackend.model.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConferenceResponseDTO {

    private Long id;

    private String name;
    private String description;
    private String imageUrl;
    private String category;
    private Integer totalStudents;
    private String status;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdate;


    private Long userId;

    private String creatorName;
}
