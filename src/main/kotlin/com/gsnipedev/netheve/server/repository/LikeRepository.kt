package com.gsnipedev.netheve.server.repository

import com.gsnipedev.netheve.server.entity.LikeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface LikeRepository : JpaRepository<LikeEntity, Int> {

    @Query("SELECT * FROM likes WHERE post_id=?1", nativeQuery = true)
    fun getAllByPostId(id: Int) : List<LikeEntity>

    @Query("SELECT COUNT(*) FROM likes WHERE post_id=?1", nativeQuery = true)
    fun getTotalByPostId(id: Int): Int

    @Query("SELECT * FROM likes WHERE post_id=?1 AND issuer_id=?2 LIMIT 1", nativeQuery = true)
    fun checkIfExist(postId: Int, issuerId: Int ): LikeEntity
}