package com.gsnipedev.netheve.server.repository

import com.gsnipedev.netheve.server.entity.CommentsEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface CommentRepository : JpaRepository<CommentsEntity, Int> {

    @Query("SELECT * FROM comments WHERE text LIKE %?1%", nativeQuery = true)
    fun findCommentByText(text: String) : List<Optional<CommentsEntity>>

    @Modifying
    @Query("UPDATE comments SET text=?2 WHERE id=?1",nativeQuery = true)
    fun findCommentByIdAndChangeText(id: Int, text: String)

    @Query("SELECT * FROM comments WHERE post_id=?1", nativeQuery = true)
    fun findCommentByPostId(id: Int): List<CommentsEntity>

}