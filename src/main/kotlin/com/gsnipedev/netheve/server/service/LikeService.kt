package com.gsnipedev.netheve.server.service

import com.gsnipedev.netheve.server.entity.LikeEntity
import com.gsnipedev.netheve.server.interfaces.LikeServiceInterface
import com.gsnipedev.netheve.server.model.WebResponse
import com.gsnipedev.netheve.server.model.like.LikeModel
import com.gsnipedev.netheve.server.repository.AccountRepository
import com.gsnipedev.netheve.server.repository.LikeRepository
import com.gsnipedev.netheve.server.repository.PostsRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class LikeService(
    val likeRepository: LikeRepository,
    val accountRepository: AccountRepository,
    val postsRepository: PostsRepository
) : LikeServiceInterface {

    override fun like(data: LikeModel): WebResponse<String> {

        val response = WebResponse(
            code = 200,
            status = "OK",
            data = "OK"
        )

        val newLikeRecord = LikeEntity(
            id = 0,
            issuer = accountRepository.getReferenceById(data.issuerId),
            post = postsRepository.getReferenceById(data.postId),
            updatedAt = null,
            createdAt = Date()
        )
        if(likeRepository.checkIfExist(newLikeRecord.post.Id, newLikeRecord.issuer.id) != 0)
        {
            response.code = 403
            response.status = "Error"
            response.data = "Error"
            return response
        }
        likeRepository.save(newLikeRecord)
        return response
    }

    override fun unlike(data: Int): WebResponse<String> {

        val response = WebResponse(
            code = 200,
            status = "OK",
            data = "OK"
        )

        likeRepository.deleteById(data)

        return response
    }

    override fun dislike(data: LikeModel): WebResponse<String> {
        val response = WebResponse(
            code = 200,
            status = "OK",
            data = "Not Yet Implemented"
        )

        return response
    }

    override fun getAllLikesById(postId: Int): WebResponse<List<LikeEntity>> {

        val response = WebResponse(
            code = 200,
            status = "OK",
            data = likeRepository.getAllByPostId(postId)
        )

        return response
    }

    override fun getTotalLikesById(postId: Int): WebResponse<Int> {
        val response = WebResponse(
            code = 200,
            status = "OK",
            data = likeRepository.getTotalByPostId(postId)
        )

        return response
    }

}