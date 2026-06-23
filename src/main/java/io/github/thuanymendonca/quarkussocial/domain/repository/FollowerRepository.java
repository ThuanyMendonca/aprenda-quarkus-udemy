package io.github.thuanymendonca.quarkussocial.domain.repository;

import io.github.thuanymendonca.quarkussocial.domain.model.Follower;
import io.github.thuanymendonca.quarkussocial.domain.model.User;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class FollowerRepository implements PanacheRepository<Follower> {
    public boolean follows(User follower, User user){
        Map<String, Object> params = new HashMap<>();
        params.put("follower", follower);
        params.put("user", user);

        // =: indica parametros
        PanacheQuery<Follower> query = find("follower =:follower and user =:user", params);

        // O resultado é opcional, ou seja, pode ter ou não o registro
        Optional<Follower> result = query.firstResultOptional();

        return result.isPresent();
    }

    public List<Follower> findByUser(Long userId){
        PanacheQuery<Follower> query = find("user.id", userId);
        return query.list();
    }

    public void deleteByFollowerAndUser(Long followerId, Long userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("followerId", followerId);

        delete("follower.id =:followerId and user.id =:userId", params);
    }
}
