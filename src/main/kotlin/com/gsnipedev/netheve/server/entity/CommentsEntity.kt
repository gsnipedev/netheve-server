package com.gsnipedev.netheve.server.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*

@Entity
@Table(name = "Comments")
data class CommentsEntity(

    @Id
    val id: Int,

    @JsonIgnore
    @ManyToOne
    val post: PostsEntity,

    @Column
    val text: String,

)
