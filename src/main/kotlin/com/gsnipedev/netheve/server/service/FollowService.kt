package com.gsnipedev.netheve.server.service

import com.gsnipedev.netheve.server.entity.FollowEntity
import org.springframework.stereotype.Service
import com.gsnipedev.netheve.server.interfaces.FollowService
import com.gsnipedev.netheve.server.model.follow.FollowRequest
import com.gsnipedev.netheve.server.model.WebResponse
import com.gsnipedev.netheve.server.repository.AccountRepository
import com.gsnipedev.netheve.server.repository.FollowRepository
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.persistence.EntityNotFoundException

@Service
class FollowService(
    val followRepository: FollowRepository,
    val accountRepository: AccountRepository
    ) : FollowService {
    override fun follow(data: FollowRequest): WebResponse<String> {

        if(!accountRepository.existsById(data.whoFollowId) || !accountRepository.existsById(data.followToId))
        {
            throw EntityNotFoundException()
        }

        val response = WebResponse(
            code = 200,
            status = "OK",
            data = "Successfully follow"
        )

        val newFollowEntity = FollowEntity(
            id = 0,
            following = accountRepository.getReferenceById(data.whoFollowId),
            follower = accountRepository.getReferenceById(data.followToId)
        )

        followRepository.save(newFollowEntity)
        return response
    }

    @Transactional
    override fun unfollow(data: Int): WebResponse<String> {
        val response = WebResponse(
            code = 200,
            status = "OK",
            data = "Successfully unfollow"
        )
        if(!followRepository.existsById(data))
        {
            throw EntityNotFoundException()
        }
        followRepository.deleteById(data)
        return response

    }

    override fun getAllFollower(data: Int): WebResponse<List<Optional<FollowEntity>>> {
        val response = WebResponse(
            code = 200,
            status = "OK",
            data = followRepository.getAllFollower(data)
        )
        return response
    }

    override fun getAllFollowing(data: Int): WebResponse<List<Optional<FollowEntity>>> {
        val response = WebResponse(
            code = 200,
            status = "OK",
            data = followRepository.getAllFollowing(data)
        )
        return response
    }


}