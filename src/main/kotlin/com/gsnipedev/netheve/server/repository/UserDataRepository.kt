package com.gsnipedev.netheve.server.repository

import com.gsnipedev.netheve.server.entity.UserDataEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserDataRepository : JpaRepository<UserDataEntity, Int> {
}