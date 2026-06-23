package io.github.thuanymendonca.quarkussocial.rest.dto;

import io.github.thuanymendonca.quarkussocial.domain.model.Follower;
import lombok.Data;

@Data
public class FollowerResponse {
    private Long id;
    private String name;

    public FollowerResponse() {
    }

    public FollowerResponse (Follower follower){
        /**
         * Para pegar o id do seguidor:
         * follower.getFollower().getId()
         */
        // o id é da tabela de follower e não do seguidor
         this(follower.getId(), follower.getFollower().getName());
    }

    public FollowerResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
