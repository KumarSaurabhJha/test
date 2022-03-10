package com.kumar.test.data.repository

interface Repository<T> {

   suspend fun getAll(): List<T>

   suspend fun get(id: String): T?

}