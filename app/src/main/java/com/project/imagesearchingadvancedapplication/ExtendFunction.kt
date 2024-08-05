package com.project.imagesearchingadvancedapplication

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<MutableList<T>>.add(item: T) {
    val updatedItems = this.value as MutableList<T>
    updatedItems.add(item)
    this.value = updatedItems
}
fun <T> MutableLiveData<MutableList<T>>.addAll(items: Collection<T>) {
    val updatedItems = this.value as MutableList<T>
    updatedItems.addAll(items)
    this.value = updatedItems
}
fun <T> MutableLiveData<MutableList<T>>.remove(item: T) {
    val updatedItems = this.value as MutableList<T>
    updatedItems.remove(item)
    this.value = updatedItems
}
fun <T> MutableLiveData<MutableList<T>>.clear() {
    this.value = mutableListOf()
}


