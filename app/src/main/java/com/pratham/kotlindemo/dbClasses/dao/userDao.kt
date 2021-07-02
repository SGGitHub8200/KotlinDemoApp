package com.pratham.kotlindemo.dbClasses.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pratham.kotlindemo.Model.Model_User

@Dao
interface userDao {
    @Query("SELECT * FROM User")
    fun getAllUser(): List<Model_User>

    @Query("SELECT * FROM User WHERE userMobile=:mobile and userPassword=:password")
    fun userLogin(mobile : String, password : String) : Model_User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: Model_User)
}