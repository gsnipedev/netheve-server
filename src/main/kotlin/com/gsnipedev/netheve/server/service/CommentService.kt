package com.gsnipedev.netheve.server.service

import com.gsnipedev.netheve.server.entity.CommentsEntity
import org.springframework.stereotype.Service
import com.gsnipedev.netheve.server.interfaces.CommentService
import com.gsnipedev.netheve.server.model.WebResponse
import com.gsnipedev.netheve.server.model.comment.EditCommentRequest
import com.gsnipedev.netheve.server.model.comment.SendCommentRequest
import com.gsnipedev.netheve.server.repository.CommentRepository
import com.gsnipedev.netheve.server.repository.PostsRepository
import org.springframework.transaction.annotation.Transactional
import java.util.Optional
import javax.persistence.EntityNotFoundException

@Service
class CommentService(
    val commentRepository: CommentRepository,
    val postsRepository: PostsRepository
    ) : CommentService  {


    override fun getComment(data: Int): WebResponse<CommentsEntity> {
        val response = WebResponse(
            code = 200,
            status = "OK",
            data = commentRepository.getReferenceById(data)
        )
        if(!commentRepository.existsById(data))
        {
            throw EntityNotFoundException()
        }
        return response
    }

    override fun getCommentByPostId(data: Int): WebResponse<List<CommentsEntity>> {
        val response = WebResponse(
            code = 200,
            status = "OK",
            data = commentRepository.findCommentByPostId(data)
        )

        return response
    }

    override fun sendComment(data: SendCommentRequest): WebResponse<String> {

        val response = WebResponse(
            code = 200,
            status = "OK",
            data = "OK"
        )

        val newCommentEntity = CommentsEntity(
            id = 0,
            post = postsRepository.getReferenceById(data.id),
            text = data.textComment
        )

        commentRepository.save(newCommentEntity)
        return response
    }

    override fun searchComment(data: String): WebResponse<List<Optional<CommentsEntity>>> {

        val response = WebResponse(
            code = 200,
            status = "OK",
            data = commentRepository.findCommentByText(data)
        )

        return response
    }

    @Transactional
    override fun editComment(data: EditCommentRequest): WebResponse<String> {
        val response = WebResponse(
            code = 200,
            status = "OK",
            data = "Comments Edited"
        )
        if(!commentRepository.existsById(data.id))
        {
            throw EntityNotFoundException()
        }

        commentRepository.findCommentByIdAndChangeText(data.id, data.text)

        return response
    }

    override fun deleteComment(data: Int): WebResponse<String> {
        if(!commentRepository.existsById(data))
        {
            throw EntityNotFoundException()
        }
        val response = WebResponse(
            code = 200,
            status = "OK",
            data = "Comments Deleted"
        )

        commentRepository.deleteById(data)
        return response
    }
}