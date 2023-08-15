package com.developersunited.pruebatecnicaandroid.data.model

data class MovieResponse (
    val page: Long,
    val results: List<Result>,
    val total_pages: Long,
    val total_results: Long
)
