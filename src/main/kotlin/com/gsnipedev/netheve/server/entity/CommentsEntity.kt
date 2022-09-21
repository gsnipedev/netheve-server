package com.gsnipedev.netheve.server.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Comments")
data class CommentsEntity(

    @Id
    @GeneratedValue
    val id: Int,

    @ManyToOne
    val issuer: AccountEntity,

    @JsonIgnore
    @ManyToOne
    val post: PostsEntity,

    @Column
    val text: String,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "comment", cascade = [CascadeType.REMOVE])
    val replies: List<CommentRepliesEntity>?,

    @Column(name = "updatedAt")
    val updatedAt: Date?,

    @Column(name = "createdAt")
    val createdAt: Date

    )
