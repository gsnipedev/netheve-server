package com.gsnipedev.netheve.server.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "posts")
data class PostsEntity(

    @Id
    @GeneratedValue
    val Id: Int,

    @JsonIgnoreProperties("password")
    @ManyToOne
    val account: AccountEntity,

    @Column(name = "textContent")
    val textContent: String,

    @Column(name = "createdAt")
    val createdAt: Date,

    @Column(name = "updatedAt")
    val updatedAt: Date?,

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "post", cascade = [CascadeType.REMOVE])
    val comments: List<CommentsEntity>?,

    @OneToMany(mappedBy = "post", cascade = [CascadeType.REMOVE])
    val likes: List<LikeEntity>

)
