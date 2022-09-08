package com.gsnipedev.netheve.server.controller

import com.gsnipedev.netheve.server.entity.CommentRepliesEntity
import com.gsnipedev.netheve.server.model.WebResponse
import com.gsnipedev.netheve.server.model.comment.reply.EditReplyCommentRequest
import com.gsnipedev.netheve.server.model.comment.reply.ReplyCommentRequest
import com.gsnipedev.netheve.server.service.CommentService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/reply")
class CommentReplyController(val commentService: CommentService) {

    @GetMapping
    fun get(@RequestParam("id") param: Int) : WebResponse<CommentRepliesEntity>
    {
        return commentService.getReply(param)
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun reply(@RequestBody body: ReplyCommentRequest) : WebResponse<String>
    {
        return commentService.reply(body)
    }

    @PatchMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun edit(@RequestBody body: EditReplyCommentRequest): WebResponse<String>
    {
        return commentService.editReply(body)
    }

    @DeleteMapping
    fun delete(@RequestParam("id") param: Int): WebResponse<String>
    {
        return commentService.deleteReply(param)
    }

}