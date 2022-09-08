package com.gsnipedev.netheve.server.interfaces

import com.gsnipedev.netheve.server.entity.CommentsEntity
import com.gsnipedev.netheve.server.model.WebResponse
import com.gsnipedev.netheve.server.model.comment.EditCommentRequest
import com.gsnipedev.netheve.server.model.comment.SendCommentRequest
import java.util.*

interface CommentServiceInterface : CommentReplyServiceInterface {

    fun getComment(data: Int): WebResponse<CommentsEntity>

    fun getCommentByPostId(data: Int): WebResponse<List<CommentsEntity>>

    fun sendComment(data: SendCommentRequest): WebResponse<String>

    fun searchComment(data: String): WebResponse<List<Optional<CommentsEntity>>>

    fun editComment(data: EditCommentRequest): WebResponse<String>

    fun deleteComment(data: Int): WebResponse<String>

}