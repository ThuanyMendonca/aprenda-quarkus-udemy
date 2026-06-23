package io.github.thuanymendonca.quarkussocial.rest.dto;

import lombok.Data;

import java.util.List;

@Data
public class FollowersPerUserResponse {
    private int followerscount;
    private List<FollowerResponse> content;
}
