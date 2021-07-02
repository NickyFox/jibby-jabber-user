package com.user.repository;

import com.user.model.tables.Followers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowersRepository extends JpaRepository<Followers, Long> {

    List<Followers> findAllByToId(long userId);

    List<Followers> findAllByFromId(long userId);

//    long deleteByFromIdAndToId(long following_id, long follower_id);

//    void deleteByFrom_IdAndTo_Id(long fromId, long toId);

    void deleteByFrom_IdAndTo_Id(long from_id, long to_id);

    boolean existsByFrom_IdAndTo_Id(long fromId, long toId);

}
