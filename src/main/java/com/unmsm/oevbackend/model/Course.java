package com.unmsm.oevbackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String imageUrl;
    private String videoUrl;
    private String category;
    private String level;
    private Double price;
    private Integer duration;
    private Integer totalLessons;
    private Integer totalStudents;
    private Double rating;
    private String status; // Ejemplo: 'ACTIVE', 'COMPLETED'
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdate;

    @OneToMany(mappedBy = "course", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Enrollment> enrollmentList;

}
