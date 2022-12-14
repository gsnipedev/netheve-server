package com.gsnipedev.netheve.server.repository

import com.gsnipedev.netheve.server.entity.FollowEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface FollowRepository : JpaRepository<FollowEntity, Int> {

    @Query("SELECT * FROM follow WHERE left_id=?1", nativeQuery = true)
    fun getAllFollowing(data: Int) : List<Optional<FollowEntity>>


    @Query("SELECT * FROM follow WHERE right_id=?1", nativeQuery = true)
    fun getAllFollower(data: Int) : List<Optional<FollowEntity>>

}