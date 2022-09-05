package com.gsnipedev.netheve.server.repository

import com.gsnipedev.netheve.server.entity.FollowEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface FollowRepository : JpaRepository<FollowEntity, Int> {

    @Query("SELECT * FROM follow WHERE follower_id=?1", nativeQuery = true)
    fun getAllFollower(data: Int) : List<Optional<FollowEntity>>

    @Query("SELECT * FROM follow WHERE following_id=?1", nativeQuery = true)
    fun getAllFollowing(data: Int) : List<Optional<FollowEntity>>

}