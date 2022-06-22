package com.fm.grpcrest.service;

import com.fm.grpcrest.bo.PostCreateRequest;
import com.fm.grpcrest.bo.PostCreateResponse;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private final RestTemplate template;
    private static final String url = "https://jsonplaceholder.typicode.com/posts";

    public PostCreateResponse create(PostCreateRequest request) {
        log.info("Received person create request {}", new Gson().toJson(request));
        var response =
            template.exchange(url, HttpMethod.POST, new HttpEntity<>(request, getHeaders()), PostCreateResponse.class);
        return response.getBody();
    }

    public PostCreateResponse find(String id) {
        log.info("Received person find request");
        var response     =
            template.exchange("%s/%s".formatted(url, id), HttpMethod.GET, new HttpEntity<>(getHeaders()), PostCreateResponse.class);
        return response.getBody();
    }

    private HttpHeaders getHeaders() {
        var headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}


