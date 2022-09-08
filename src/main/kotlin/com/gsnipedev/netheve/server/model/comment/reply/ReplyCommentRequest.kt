package com.gsnipedev.netheve.server.model.comment.reply

data class ReplyCommentRequest(

    val commentId: Int,

    val issuer: Int,

    val textReply: String,


)
