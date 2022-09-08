package com.gsnipedev.netheve.server.model.comment

data class SendCommentRequest(

    val postId : Int,
    val issuerId: Int,
    val textComment: String

)
