package com.gsnipedev.netheve.server.repository

import com.gsnipedev.netheve.server.entity.AccountEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface AccountRepository : JpaRepository<AccountEntity, Int>{

    @Query(value = "SELECT * FROM account WHERE username= ?1 AND password= ?2", nativeQuery = true)
    fun findByUsernameAndPassword(username: String, password: String) : Int

    @Query(value = "SELECT * FROM account WHERE username=?1 ", nativeQuery = true)
    fun findByUsername(username: String) : Int

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE account SET password=?1 WHERE username=?2 AND password=?3", nativeQuery = true)
    fun changePassword(newPassword: String, username: String, oldPassword: String)

}