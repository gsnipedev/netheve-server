package com.gsnipedev.netheve.server.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "likes")
data class LikeEntity(

    @Id
    @GeneratedValue
    val id: Int,

    @ManyToOne
    val issuer: AccountEntity,

    @ManyToOne
    val post: PostsEntity,

    val updatedAt: Date?,

    val createdAt: Date
)
