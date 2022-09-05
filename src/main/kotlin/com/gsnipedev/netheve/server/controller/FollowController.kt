package com.gsnipedev.netheve.server.controller

import com.gsnipedev.netheve.server.entity.FollowEntity
import com.gsnipedev.netheve.server.model.follow.FollowRequest
import com.gsnipedev.netheve.server.model.WebResponse
import com.gsnipedev.netheve.server.service.FollowService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.persistence.EntityNotFoundException


@RestController
@RequestMapping("api/follow")
class FollowController(val followService: FollowService) {

    @GetMapping
    fun getAllFollow(
        @RequestParam(name = "id") param_id: Int,
        @RequestParam(name = "type") param_type: String
    ): WebResponse<List<Optional<FollowEntity>>>
    {
        return when(param_type){
            "following" -> followService.getAllFollowing(param_id)
            "follower" -> followService.getAllFollower(param_id)
            else -> throw EntityNotFoundException()
        }

    }

    @PostMapping
    fun follow(@RequestBody body: FollowRequest): WebResponse<String>
    {
        return followService.follow(body)
    }

    @DeleteMapping
    fun unfollow(@RequestParam(name = "id") param: Int): WebResponse<String>
    {
        return followService.unfollow(param)
    }

}