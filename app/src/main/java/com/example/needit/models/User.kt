package com.example.needit.models

class User(
    val id: String,
    val surname: String,
    val name: String,
    val patronymic: String? = null,
    val address: String,
    val gender: String? = null,
    val phone: String,
    val email: String,
    val nobility_level: Int = 0,
    val achievements: Map<String, Boolean>) //TODO сделать дефолтное значение для карты
