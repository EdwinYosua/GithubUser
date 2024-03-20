package com.edwinyosua.githubuserapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.edwinyosua.githubuserapp.data.response.Item

class DiffCallBackUtil(
    private val oldList: List<Item>,
    private val newList: List<Item>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}