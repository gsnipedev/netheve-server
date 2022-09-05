package com.gsnipedev.netheve.server.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import java.util.Date
import javax.persistence.*


@Entity
@Table(name = "UserData")
data class UserDataEntity(

    @Id
    @GeneratedValue
    val id: Int,

    @Column(name = "firstname")
    val firstname: String,

    @Column(name = "lastname")
    val lastname: String,

    @Column(name = "email")
    val email: String,

    @Column(name = "createdAt")
    val createdAt: Date,

    @Column(name = "updatedAt")
    val updatedAt: Date?

)
