package com.example.needit.firebase.models

import java.util.*

class Post(
    val surname: String,
    val name: String,
    val patronymic: String? = null,
    val address: String,
    val phone: String,
    val post_type: PostType,
    val object_type: ObjectType,
    val language: String,
    val photo_reference: String? = null,
    val creation_date: Date
)
{
    enum class PostType{
        GIVE_AWAY, REQUIRE
    }
    enum class ObjectType{
        CLOTHES, APPLIANCES, LEISURE_GOODS
    }

    val id: String  = UUID.randomUUID().toString()
}