package com.gsnipedev.netheve.server.controller

import com.gsnipedev.netheve.server.entity.LikeEntity
import com.gsnipedev.netheve.server.model.WebResponse
import com.gsnipedev.netheve.server.model.like.LikeModel
import com.gsnipedev.netheve.server.service.LikeService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/like")
class LikeController(val likeService: LikeService) {

    @GetMapping
    fun getLike(@RequestParam("id") param: Int) : WebResponse<List<LikeEntity>>
    {
        return likeService.getAllLikesById(param)
    }

    @GetMapping("/count")
    fun getLikeCount(@RequestParam("id") param: Int): WebResponse<Int>
    {
        return likeService.getTotalLikesById(param)
    }

    @PostMapping
    fun like(@RequestBody body: LikeModel): WebResponse<String>
    {
        return likeService.like(body)
    }

    @PostMapping("/dislike")
    fun dislike(@RequestBody body: LikeModel): WebResponse<String>
    {
        return likeService.dislike(body)
    }

    @DeleteMapping
    fun unlike(@RequestParam("id") param: Int) : WebResponse<String>
    {
        return likeService.unlike(param)
    }


}