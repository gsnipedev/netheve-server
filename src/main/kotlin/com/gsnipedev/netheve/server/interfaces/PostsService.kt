package com.gsnipedev.netheve.server.interfaces

import com.gsnipedev.netheve.server.entity.PostsEntity
import com.gsnipedev.netheve.server.model.WebResponse
import com.gsnipedev.netheve.server.model.posts.CreatePostRequest
import com.gsnipedev.netheve.server.model.posts.SearchPostRequest
import com.gsnipedev.netheve.server.model.posts.UpdatePostRequest

interface PostsService {

    fun getPost(data: Int) : WebResponse<PostsEntity>

    fun getFyp(): WebResponse<List<PostsEntity>>

    fun searchPost(data: SearchPostRequest) : WebResponse<List<PostsEntity>>

    fun createPost(data: CreatePostRequest) : WebResponse<String>

    fun updatePost(data: UpdatePostRequest) : WebResponse<String>

    fun deletePost(data: Int) : WebResponse<String>
}