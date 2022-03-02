package com.example.needit.firebase.firestore
import com.example.needit.firebase.models.User
import com.example.needit.firebase.utils.Constants
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.toObject

class UserFirestore{
    private val firestore = FirebaseFirestore.getInstance()

    fun set(user: User){
        firestore.collection(Constants.USERS).document(user.id).set(user, SetOptions.merge())
            .addOnFailureListener{e -> throw e }
    }

    fun delete(userId: String){
        firestore.collection(Constants.USERS).document(userId).delete()
            .addOnFailureListener{ e -> throw e }
    }

    //TODO getOne не работает, не знаю почему
    fun getOne(userId: String): User{
        val user = firestore.collection(Constants.USERS).document(userId).get()
            .addOnSuccessListener { document ->
                if (document == null){
                    throw NoSuchElementException()
                }
            }
            .addOnFailureListener{ e -> throw e }

        return user.result.toObject<User>()!!
    }






}