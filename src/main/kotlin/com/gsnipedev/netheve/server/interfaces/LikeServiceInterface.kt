package com.gsnipedev.netheve.server.interfaces

import com.gsnipedev.netheve.server.entity.LikeEntity
import com.gsnipedev.netheve.server.model.WebResponse
import com.gsnipedev.netheve.server.model.like.CheckLikeResponse
import com.gsnipedev.netheve.server.model.like.LikeModel

interface LikeServiceInterface {

    fun like(data: LikeModel): WebResponse<String>

    fun unlike(data: Int): WebResponse<String>

    fun dislike(data: LikeModel): WebResponse<String>

    fun getAllLikesById(postId: Int): WebResponse<List<LikeEntity>>

    fun getTotalLikesById(postId: Int): WebResponse<Int>

    fun checkIfLiked(postId: Int, userId:Int): WebResponse<CheckLikeResponse>

}
