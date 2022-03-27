package com.example.needit.firebase.models

import java.util.*

class Post(
    val surname: String,
    val name: String,
    val patronymic: String? = null,
    var address: String,
    val phone: String,
    var post_type: PostType,
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