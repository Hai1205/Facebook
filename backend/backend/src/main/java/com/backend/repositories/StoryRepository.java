package com.backend.repositories;

import com.backend.entities.Story;
import com.backend.entities.User;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryRepository extends MongoRepository<Story, String> {
    List<Story> findAllByOrderByCreatedAtDesc();

    List<Story> findByUserInOrderByCreatedAtDesc(List<User> followingPosts);

    @Query("SELECT s FROM Story s WHERE s.user.id IN :userIds ORDER BY s.createdAt DESC")
    List<Story> findStoriesByUserIds(@Param("userIds") List<String> userIds);

}
