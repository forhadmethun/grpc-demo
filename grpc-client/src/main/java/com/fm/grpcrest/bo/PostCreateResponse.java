package com.fm.grpcrest.bo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostCreateResponse {
    public long userId;
    public String title;
    public String body;
    public long id;
}
