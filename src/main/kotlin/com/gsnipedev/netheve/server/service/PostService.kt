package com.gsnipedev.netheve.server.service

import com.gsnipedev.netheve.server.entity.PostsEntity
import com.gsnipedev.netheve.server.interfaces.PostsService
import com.gsnipedev.netheve.server.model.WebResponse
import com.gsnipedev.netheve.server.model.posts.CreatePostRequest
import com.gsnipedev.netheve.server.model.posts.SearchPostRequest
import com.gsnipedev.netheve.server.model.posts.UpdatePostRequest
import com.gsnipedev.netheve.server.repository.AccountRepository
import com.gsnipedev.netheve.server.repository.PostsRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class PostService(
    val postsRepository: PostsRepository,
    val accountRepository: AccountRepository,
) : PostsService {

    override fun getPost(data: Int): WebResponse<Optional<PostsEntity>> {
        val result = postsRepository.findById(data)
        val response = WebResponse(
            code = 200,
            status = "OK",
            data = result
        )
        return response
    }

    override fun getFyp(): WebResponse<List<PostsEntity>> {
        val result = postsRepository.findAll()
        val response = WebResponse(
            code = 200,
            status = "OK",
            data = result
        )
        return response
    }

    override fun searchPost(data: SearchPostRequest): WebResponse<List<PostsEntity>> {

        val result = postsRepository.findByTextContent(data.textContent)
        val response = WebResponse(
            code = 200,
            status = "OK",
            data = result
        )
        return response
    }

    @Transactional
    override fun createPost(data: CreatePostRequest): WebResponse<String> {

        val response = WebResponse(
            code = 201,
            status = "Created",
            data = "Post successfully Created"
        )

        val newPostEntity = PostsEntity(
            Id = 0,
            account = accountRepository.getReferenceById(data.id),
            textContent = data.textContent,
            createdAt = Date(),
            updatedAt = null,
            comments = null,
            likes = emptyList()
        )
        postsRepository.save(newPostEntity)

        return response
    }

    @Transactional
    override fun updatePost(data: UpdatePostRequest): WebResponse<String> {
        val response = WebResponse(
            code = 202,
            status = "Accepted",
            data = "Post successfully updated"
        )
        postsRepository.findByIdAndChangeTextContent(data.id, data.textContent)
        return response
    }

    override fun deletePost(data: Int): WebResponse<String> {
        val response = WebResponse(
            code = 202,
            status = "Accepted",
            data = "Post successfully deleted"
        )
        postsRepository.deleteById(data)
        return response
    }
}