package com.example.wikideas.repository;

import com.example.wikideas.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findByTitleContaining(String title);
}
