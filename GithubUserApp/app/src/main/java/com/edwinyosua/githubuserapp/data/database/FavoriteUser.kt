package com.edwinyosua.githubuserapp.data.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class FavoriteUser(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "Username")
    var userName: String = "",

    @ColumnInfo(name = "Avatar Url")
    var avatarUrl: String? = null,
) : Parcelable