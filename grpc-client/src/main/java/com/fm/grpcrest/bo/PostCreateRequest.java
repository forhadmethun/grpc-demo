package com.fm.grpcrest.bo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCreateRequest {
    public long userId;
    public String title;
    public String body;
}
