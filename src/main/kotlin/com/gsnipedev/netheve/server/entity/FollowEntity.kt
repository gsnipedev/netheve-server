package com.gsnipedev.netheve.server.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "follow")
data class FollowEntity(

    @Id
    @GeneratedValue
    val id: Int,

    @ManyToOne
    @JsonIgnoreProperties("userData")
    val following: AccountEntity,

    @ManyToOne
    @JsonIgnoreProperties("userData")
    val follower: AccountEntity

)
