package com.gsnipedev.netheve.server.entity

import com.fasterxml.jackson.annotation.JsonIgnore
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
    @JsonIgnore
    val post: PostsEntity,

    val updatedAt: Date?,

    val createdAt: Date
)
