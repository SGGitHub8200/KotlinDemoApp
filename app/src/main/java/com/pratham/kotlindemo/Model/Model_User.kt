package com.pratham.kotlindemo.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "User")
data class Model_User(
    val userId: String?,
    val userName: String?,
    val userMobile: String?,
    val userPassword: String?,
    @PrimaryKey(autoGenerate = true) val uId: Int?=null
)