package com.gsnipedev.netheve.server.model.comment.reply

data class EditReplyCommentRequest(

    val replyId: Int,

    val newTextReply: String

)
