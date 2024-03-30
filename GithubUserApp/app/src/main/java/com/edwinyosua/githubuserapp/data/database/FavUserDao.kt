package com.edwinyosua.githubuserapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface FavUserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favUser: FavoriteUser)

    @Update
    fun update(favUser: FavoriteUser)

    @Delete
    fun delete(favUser: FavoriteUser)

    @Query("SELECT * from FavoriteUser ORDER BY id ASC")
    fun getFavUserList(): LiveData<List<FavoriteUser>>

    @Query("SELECT * FROM FavoriteUser WHERE username = :userName")
    fun getFavoriteUserByUsername(userName: String): LiveData<FavoriteUser>
}