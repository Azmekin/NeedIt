package com.example.needit.firebase.models

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
    val achievements: Achievements = Achievements())
{
    class Achievements(
        val first:Boolean = false,
        val second:Boolean = false,
        val third:Boolean = false,
        val fourth:Boolean = false,
        val fifth:Boolean = false,
        val sixth:Boolean = false,
        val seventh:Boolean = false,
        val eighth:Boolean = false,
        val ninth:Boolean = false,
        val tenth:Boolean = false)
}
