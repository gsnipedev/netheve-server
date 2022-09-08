package com.gsnipedev.netheve.server.repository

import com.gsnipedev.netheve.server.entity.CommentRepliesEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface CommentReplyRepository : JpaRepository<CommentRepliesEntity, Int> {

    @Modifying
    @Query("UPDATE comment_reply SET text_reply=?2 WHERE id=?1", nativeQuery = true)
    fun edit(id:Int, newText: String)

}