package com.example.roller;

import com.example.roller.domain.Product

data class Order_info(
    val id: Int,
    val order_item: HashMap<Product, Int>,
    val order_time: String,
    val order_path: String
)