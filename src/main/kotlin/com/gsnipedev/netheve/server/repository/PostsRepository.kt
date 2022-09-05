package com.gsnipedev.netheve.server.repository

import com.gsnipedev.netheve.server.entity.PostsEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import java.util.*

interface PostsRepository : JpaRepository<PostsEntity, Int> {

    @Query("SELECT * FROM posts WHERE username=?1", nativeQuery = true)
    fun findByUsername(username: String)

    @Modifying
    @Query("UPDATE posts SET text_content =?2, updated_at=?3 WHERE id=?1 ", nativeQuery = true)
    fun findByIdAndChangeTextContent(Id: Int, newTextContent: String, date: Date? = Date())

    @Query("SELECT * FROM posts WHERE text_content LIKE %?1%", nativeQuery = true)
    fun findByTextContent(text: String) : List<PostsEntity>

}