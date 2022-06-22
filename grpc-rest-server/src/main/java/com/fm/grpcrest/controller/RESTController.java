package com.fm.grpcrest.controller;

import com.fm.grpcrest.bo.PostCreateRequest;
import com.fm.grpcrest.bo.PostCreateResponse;
import com.fm.grpcrest.model.PingRequest;
import com.fm.grpcrest.model.PostRequest;
import com.fm.grpcrest.model.PostResponse;
import com.fm.grpcrest.service.PingService;
import com.fm.grpcrest.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class RESTController {
    private final PingService pingService;
    private final PostService postService;

    @PostMapping(path = "/ping", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> ping(@RequestBody PingRequest request) {
        return ResponseEntity.ok(pingService.ping(request));
    }

    @PostMapping(path = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostCreateResponse> posts(@RequestBody PostCreateRequest request) {
        return ResponseEntity.ok(postService.create(request));
    }

    @GetMapping(path = "/posts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostCreateResponse> findPost(@PathVariable String id) {
        return ResponseEntity.ok(postService.find(id));
    }

}
