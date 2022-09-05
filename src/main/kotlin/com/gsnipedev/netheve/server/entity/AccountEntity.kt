package com.gsnipedev.netheve.server.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import java.util.Date
import javax.persistence.*

@Entity
@Embeddable
@Table(name = "Account")
data class AccountEntity(

    @Id
    @GeneratedValue
    val id: Int,

    @Column(name = "username")
    val username: String,

    @JsonIgnore
    @Column(name = "password")
    val password: String,

    @Column(name = "createdAt")
    val createdAt: Date,

    @Column(name = "updatedAt")
    val updatedAt: Date?,

    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    val userData: UserDataEntity


)
