package com.example.wikideas.controller;

import com.example.wikideas.entities.Topic;
import com.example.wikideas.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping("/topics")
    public ResponseEntity<List<Topic>> getTopics(@RequestParam(required = false) String title){
        return topicService.getTopics(title);
    }

    @GetMapping("/topics/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable("id") long id) {
        return topicService.getTopicById(id);
    }

    @PostMapping("/topics")
    public ResponseEntity<Topic> createTopic(@RequestBody Topic topic) {
        return topicService.createTopic(topic);
    }

    @PutMapping("/topics/{id}")
    public ResponseEntity<Topic> editTopic(@PathVariable("id") long id, @RequestBody Topic topic) {
        return topicService.editTopic(id, topic);
    }
}