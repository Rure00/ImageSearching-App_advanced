package com.project.imagesearchingadvancedapplication

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<MutableList<T>>.add(item: T) {
    val updatedItems = this.value as ArrayList
    updatedItems.add(item)
    this.value = updatedItems
}
fun <T> MutableLiveData<MutableList<T>>.addAll(items: Collection<T>) {
    val updatedItems = this.value as ArrayList
    updatedItems.addAll(items)
    this.value = updatedItems
}


