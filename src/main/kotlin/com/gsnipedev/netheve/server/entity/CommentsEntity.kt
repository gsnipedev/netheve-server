package com.gsnipedev.netheve.server.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "Comments")
data class CommentsEntity(

    @Id
    @GeneratedValue
    val id: Int,

    @JsonIgnore
    @ManyToOne
    val post: PostsEntity,

    @Column
    val text: String,

    )
