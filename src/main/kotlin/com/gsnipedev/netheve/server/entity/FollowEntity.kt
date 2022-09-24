package com.gsnipedev.netheve.server.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
@Table(name = "follow")
data class FollowEntity(

    @Id
    @GeneratedValue
    val id: Int,

    @ManyToOne
    @JsonIgnoreProperties("userData")
    val left: AccountEntity,

    @ManyToOne
    @JsonIgnoreProperties("userData")
    val right: AccountEntity

)
