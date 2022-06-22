package com.fm.grpcrest.controller;

import com.fm.grpcrest.bo.PostCreateRequest;
import com.fm.grpcrest.bo.PostCreateResponse;
import com.fm.grpcrest.model.PostFindRequest;
import com.fm.grpcrest.model.PostRequest;
import com.fm.grpcrest.model.PostResponse;
import com.fm.grpcrest.model.PostServiceGrpc;
import com.fm.grpcrest.service.PostService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;

@Slf4j
@GRpcService
@RequiredArgsConstructor
public class PostGrpcController extends PostServiceGrpc.PostServiceImplBase {
    private final PostService postService;
    @Override
    public void create(PostRequest request, StreamObserver<PostResponse> responseObserver) {
        PostCreateResponse postCreateResponse = postService.create(
            PostCreateRequest.builder()
                .userId(request.getUserId())
                .title(request.getTitle())
                .body(request.getBody())
                .build()
        );
        responseObserver.onNext(
            PostResponse.newBuilder()
                .setId(postCreateResponse.id)
                .setUserId(postCreateResponse.userId)
                .setTitle(postCreateResponse.title)
                .setBody(postCreateResponse.body)
                .build()
        );
        responseObserver.onCompleted();
    }

    @Override
    public void find(PostFindRequest request, StreamObserver<PostResponse> responseObserver) {
        var postCreateResponse = postService.find(request.getId());
        responseObserver.onNext(
            PostResponse.newBuilder()
                .setId(postCreateResponse.id)
                .setUserId(postCreateResponse.userId)
                .setTitle(postCreateResponse.title)
                .setBody(postCreateResponse.body)
                .build()
        );
        responseObserver.onCompleted();
    }
}
