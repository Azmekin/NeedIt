package com.example.needit.firebase.firestore

import com.example.needit.firebase.models.Post
import com.example.needit.firebase.utils.Constants
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.toObject

class PostFirestore {
    private val firestore = FirebaseFirestore.getInstance()

    fun set(post: Post){
        firestore.collection(Constants.POSTS).document(post.id).set(post, SetOptions.merge())
            .addOnFailureListener{e -> throw e }
    }

    fun delete(postId: String){
        firestore.collection(Constants.POSTS).document(postId).delete()
            .addOnFailureListener{ e -> throw e }
    }

    //TODO getOne не работает
    fun getOne(postId: String): Post {
        val post = firestore.collection(Constants.POSTS).document(postId).get()
            .addOnSuccessListener { document ->
                if (document == null){
                    throw NoSuchElementException()
                }
            }
            .addOnFailureListener{ e -> throw e }

        return post.result.toObject<Post>()!!
    }
}