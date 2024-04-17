package com.example.taskequalinfotech.models

data class UsersList(
    val data: List<Data>,
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int
)