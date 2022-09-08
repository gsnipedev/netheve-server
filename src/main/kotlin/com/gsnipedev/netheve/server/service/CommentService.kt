package com.gsnipedev.netheve.server.service

import com.gsnipedev.netheve.server.entity.CommentRepliesEntity
import com.gsnipedev.netheve.server.entity.CommentsEntity
import com.gsnipedev.netheve.server.interfaces.CommentServiceInterface
import com.gsnipedev.netheve.server.model.WebResponse
import com.gsnipedev.netheve.server.model.comment.EditCommentRequest
import com.gsnipedev.netheve.server.model.comment.SendCommentRequest
import com.gsnipedev.netheve.server.model.comment.reply.EditReplyCommentRequest
import com.gsnipedev.netheve.server.model.comment.reply.ReplyCommentRequest
import com.gsnipedev.netheve.server.repository.AccountRepository
import com.gsnipedev.netheve.server.repository.CommentReplyRepository
import com.gsnipedev.netheve.server.repository.CommentRepository
import com.gsnipedev.netheve.server.repository.PostsRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.persistence.EntityNotFoundException

@Service
class CommentService(
    val commentRepository: CommentRepository,
    val postsRepository: PostsRepository,
    val accountRepository: AccountRepository,
    val commentReplyRepository: CommentReplyRepository
    ) : CommentServiceInterface  {


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
            post = postsRepository.getReferenceById(data.postId),
            issuer = accountRepository.getReferenceById(data.issuerId),
            text = data.textComment,
            replies = emptyList(),
            updatedAt = null,
            createdAt = Date()
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

    override fun getReply(data: Int): WebResponse<CommentRepliesEntity> {
        val response = WebResponse(
            code = 200,
            status = "OK",
            data = commentReplyRepository.getReferenceById(data)
        )

        return response
    }

    override fun reply(data: ReplyCommentRequest): WebResponse<String> {

        val response = WebResponse(
            code = 200,
            status = "OK",
            data = "Replied"
        )

        val newCommentReply = CommentRepliesEntity(
            id = 0,
            issuer = accountRepository.getReferenceById(data.issuer),
            text = data.textReply,
            comment = commentRepository.getReferenceById(data.commentId),
            updatedAt = null,
            createdAt = Date(),
        )
        commentReplyRepository.save(newCommentReply)
        return response
    }

    @Transactional
    override fun deleteReply(data: Int): WebResponse<String> {
        val response = WebResponse(
            code = 200,
            status = "OK",
            data = "Deleted"
        )

        commentReplyRepository.deleteById(data)
        return response
    }

    @Transactional
    override fun editReply(data: EditReplyCommentRequest): WebResponse<String> {
        val response = WebResponse(
            code = 200,
            status = "OK",
            data = "Edited"
        )
        commentReplyRepository.edit(data.replyId, data.newTextReply)
        return response
    }
}