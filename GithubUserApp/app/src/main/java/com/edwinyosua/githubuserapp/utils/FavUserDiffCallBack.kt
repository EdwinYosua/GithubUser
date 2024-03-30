package com.edwinyosua.githubuserapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.edwinyosua.githubuserapp.data.database.FavoriteUser

class FavUserDiffCallBack(
    private val oldNoteList: List<FavoriteUser>,
    private val newNoteList: List<FavoriteUser>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldNoteList.size
    override fun getNewListSize(): Int = newNoteList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldNoteList[oldItemPosition].id == newNoteList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldNote = oldNoteList[oldItemPosition]
        val newNote = newNoteList[newItemPosition]
        return oldNote.userName == newNote.userName && oldNote.avatarUrl == newNote.avatarUrl
    }
}