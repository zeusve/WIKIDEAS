package com.example.wikideas.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "topics")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "created_at", nullable = false, columnDefinition = "timestamptz", updatable = false)
    private ZonedDateTime created_at;

    @Column(name = "updated_at", columnDefinition = "timestamptz")
    private ZonedDateTime updated_at;

    public Topic(String title, String description, ZonedDateTime current_time) {
        this.title = title;
        this.description = description;
        this.created_at = current_time;
        this.updated_at = current_time;
    }
}
