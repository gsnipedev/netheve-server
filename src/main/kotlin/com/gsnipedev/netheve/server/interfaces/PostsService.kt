package com.gsnipedev.netheve.server.interfaces

import com.gsnipedev.netheve.server.entity.PostsEntity
import com.gsnipedev.netheve.server.model.WebResponse
import com.gsnipedev.netheve.server.model.posts.*
import java.util.Optional

interface PostsService {

    fun getPost(data: Int) : WebResponse<Optional<PostsEntity>>

    fun getFyp(): WebResponse<List<PostsEntity>>

    fun searchPost(data: SearchPostRequest) : WebResponse<List<PostsEntity>>

    fun createPost(data: CreatePostRequest) : WebResponse<String>

    fun updatePost(data: UpdatePostRequest) : WebResponse<String>

    fun deletePost(data: Int) : WebResponse<String>
}