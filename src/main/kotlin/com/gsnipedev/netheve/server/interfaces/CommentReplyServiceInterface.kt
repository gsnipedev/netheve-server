package com.gsnipedev.netheve.server.interfaces

import com.gsnipedev.netheve.server.entity.CommentRepliesEntity
import com.gsnipedev.netheve.server.model.WebResponse
import com.gsnipedev.netheve.server.model.comment.reply.EditReplyCommentRequest
import com.gsnipedev.netheve.server.model.comment.reply.ReplyCommentRequest

interface CommentReplyServiceInterface {

    fun getReply(data: Int): WebResponse<CommentRepliesEntity>

    fun reply(data: ReplyCommentRequest): WebResponse<String>

    fun deleteReply(data: Int): WebResponse<String>

    fun editReply(data: EditReplyCommentRequest): WebResponse<String>

}