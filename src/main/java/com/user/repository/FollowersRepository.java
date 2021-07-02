package com.user.repository;

import com.user.model.tables.Followers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowersRepository extends JpaRepository<Followers, Long> {

    List<Followers> findAllByToId(long userId);

    List<Followers> findAllByFromId(long userId);

//    long deleteByFromIdAndToId(long following_id, long follower_id);

//    void deleteByFrom_IdAndTo_Id(long fromId, long toId);

    Optional<Followers> findByFrom_IdAndTo_Id(long fromId, long toId);

}
