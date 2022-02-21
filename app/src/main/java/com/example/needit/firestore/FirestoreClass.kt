package com.example.needit.firestore

import com.example.needit.models.User
import com.example.needit.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

//В этом классе описывается логика взаимодейсвтия с бд
class FirestoreClass {
    private val myFirestore = FirebaseFirestore.getInstance();

    fun registerUser(user: User){

        //открываем коллекцию users
        myFirestore.collection(Constants.USERS)
            //открываем документ, имя документа - id юзера
            .document(user.id)
            //заполяем поля документа значениями из user
            .set(user, SetOptions.merge())
            //TODO сообщить юзеру об успешной/неудачной записи в бд
            .addOnSuccessListener {
             //Я не знаю, что это строчка делает, она была в гайде
             //https://firebase.google.com/docs/firestore/manage-data/add-data?authuser=0
             //   Log.d(TAG, "DocumentSnapshot successfully written!")
            }
            .addOnFailureListener{
                // это строчка тоже в гайе была
               //     e -> Log.w(TAG, "Error writing document", e)
            }
    }

    fun getCurrentUserId(): String{

        //Получаем текущего пользователя через FirebaseAuth
        val currentUser = FirebaseAuth.getInstance().currentUser

        var currentUserId= ""
        if (currentUser != null){
            currentUserId = currentUser.uid
        }

        return currentUserId
    }

    fun getUserDetails() {

        //открываем коллекцию users
        myFirestore.collection(Constants.USERS)
            //находим документ по id текущего пользователя
            .document(getCurrentUserId())
            .get()
            .addOnSuccessListener { document ->

                if (document != null) {
             //       Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                    //конвертируем документ в объект
                    val user = document.toObject(User::class.java)!!
                } else {
             //       Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener{
         //           e -> Log.d(TAG, "get failed with ", e)
            }
    }
}