package com.gsnipedev.netheve.server.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "comment_reply")
data class CommentRepliesEntity(

    @Id
    @GeneratedValue
    val id: Int,

    @ManyToOne
    val issuer: AccountEntity,

    @ManyToOne
    @JsonIgnore
    val comment: CommentsEntity,

    @Column(name = "textReply")
    val text: String,

    @Column(name = "updatedAt")
    val updatedAt: Date?,

    @Column(name = "createdAt")
    val createdAt: Date

)
