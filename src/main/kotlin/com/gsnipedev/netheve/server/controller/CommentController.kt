package com.gsnipedev.netheve.server.controller

import com.gsnipedev.netheve.server.entity.CommentsEntity
import com.gsnipedev.netheve.server.model.WebResponse
import com.gsnipedev.netheve.server.model.comment.EditCommentRequest
import com.gsnipedev.netheve.server.model.comment.SendCommentRequest
import com.gsnipedev.netheve.server.service.CommentService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("api/comment")
class CommentController(val commentService: CommentService) {

    @GetMapping
    fun search(@RequestParam("s") param: String) : WebResponse<List<Optional<CommentsEntity>>>
    {
        return commentService.searchComment(param)
    }

    @GetMapping("{id}")
    fun get(@PathVariable(name = "id") pathVar : Int) : WebResponse<CommentsEntity>
    {
        return commentService.getComment(pathVar)
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun post(@RequestBody body: SendCommentRequest) : WebResponse<String>
    {
        return commentService.sendComment(body)
    }

    @GetMapping("/p")
    fun getByPostId(@RequestParam("id") param : Int) : WebResponse<List<CommentsEntity>>
    {
        return commentService.getCommentByPostId(param)
    }

    @PatchMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun update(@RequestBody body: EditCommentRequest) : WebResponse<String>
    {
        return commentService.editComment(body)
    }

    @DeleteMapping()
    fun delete(@RequestParam("id") param: Int) : WebResponse<String>
    {
        return commentService.deleteComment(param)
    }

}