package com.fm.grpcrest.service;

import com.fm.grpcrest.bo.PostCreateRequest;
import com.fm.grpcrest.bo.PostCreateResponse;
import com.fm.grpcrest.model.PostFindRequest;
import com.fm.grpcrest.model.PostRequest;
import com.fm.grpcrest.model.PostServiceGrpc;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

    @GrpcClient("grpc-server")
    private PostServiceGrpc.PostServiceBlockingStub stub;

    public PostCreateResponse create(PostCreateRequest request) {
        log.info("Received person create request {}", new Gson().toJson(request));
        var response = stub.create(
            PostRequest.newBuilder()
                .setUserId(request.userId)
                .setTitle(request.title)
                .setBody(request.body)
                .build()
        );

        return PostCreateResponse.builder()
            .id(response.getId())
            .userId(response.getUserId())
            .title(response.getTitle())
            .body(response.getBody())
            .build();
    }

    public PostCreateResponse find(String id) {
        var response = stub.find(
            PostFindRequest.newBuilder()
                .setId(id).build()
        );

        return PostCreateResponse.builder()
            .id(response.getId())
            .userId(response.getUserId())
            .title(response.getTitle())
            .body(response.getBody())
            .build();
    }
}


