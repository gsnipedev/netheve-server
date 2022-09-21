package com.gsnipedev.netheve.server.controller

import com.gsnipedev.netheve.server.entity.PostsEntity
import com.gsnipedev.netheve.server.model.WebResponse
import com.gsnipedev.netheve.server.model.posts.CreatePostRequest
import com.gsnipedev.netheve.server.model.posts.SearchPostRequest
import com.gsnipedev.netheve.server.model.posts.UpdatePostRequest
import com.gsnipedev.netheve.server.service.PostService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/post")
class PostsController(val postService: PostService) {


    @GetMapping("/fyp", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFyp() : WebResponse<List<PostsEntity>>
    {
        return postService.getFyp()
    }

    @GetMapping("/{id}",produces = [MediaType.APPLICATION_JSON_VALUE])
    fun get(@PathVariable(name = "id") pathVar: Int) : WebResponse<PostsEntity>
    {
        return postService.getPost(pathVar)
    }

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getById(@RequestParam(name = "s") param: String) : WebResponse<List<PostsEntity>>
    {
        val data = SearchPostRequest(param)

        return postService.searchPost(data)
    }

    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun post(@RequestBody body: CreatePostRequest) : WebResponse<String>
    {
        return postService.createPost(body)
    }

    @PatchMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun put(@RequestBody body: UpdatePostRequest) : WebResponse<String>
    {
        return postService.updatePost(body)
    }

    @DeleteMapping
    fun delete(@RequestParam(name = "id") param: Int) : WebResponse<String>
    {
        return postService.deletePost(param)
    }

}