package com.gsnipedev.netheve.server.interfaces

import com.gsnipedev.netheve.server.entity.FollowEntity
import com.gsnipedev.netheve.server.model.follow.FollowRequest
import com.gsnipedev.netheve.server.model.WebResponse
import java.util.Optional

interface FollowService {

    fun follow(data: FollowRequest) : WebResponse<String>

    fun unfollow(data: Int): WebResponse<String>

    fun getAllFollower(data: Int): WebResponse<List<Optional<FollowEntity>>>

    fun getAllFollowing(data: Int): WebResponse<List<Optional<FollowEntity>>>
}