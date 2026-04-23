package com.examen.appsolissol.api

data class Product(
    val title: String,
    val price: Double,
    val category: String,
    val thumbnail: String
)

data class ProductResponse(
    val products: List<Product>
)
