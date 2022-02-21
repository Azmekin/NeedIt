package com.example.needit.activities

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.needit.R
import com.example.needit.firestore.FirestoreClass
import com.example.needit.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity(){
    private lateinit var auth: FirebaseAuth     //переменная для авторизации

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_register) TODO: cоздать activity_register

        auth = Firebase.auth


    }


    //Проверяет введенные при регистрации данные
    private fun validateRegistration(name:String, surname:String, email:String,
                                            phone:String, password:String): Boolean{
        //TODO выводить сообщение об ошибке при незаполненых полях
        //TODO добавить проверки телефона, емайла и пароля на правильсность
        return when{
            TextUtils.isEmpty(name.trim()) || TextUtils.isEmpty(surname.trim()) -> {
                false
            }

            TextUtils.isEmpty(email.trim()) -> {
                false
            }

            TextUtils.isEmpty(phone.trim()) -> {
                false
            }

            TextUtils.isEmpty(password.trim()) || password.length<4 -> {
                false
            }

            else -> {true}
        }
    }

    //Функция для регистрации пользователей
    fun registerUser(name:String, surname:String, patronymic:String,
                             address:String, gender:String, email:String,
                             phone:String, password:String){


        if (!validateRegistration(name, surname, email, phone, password)) return //валидация

        //Записываем пользователя в бд
        auth.signInWithEmailAndPassword(email.trim(),password.trim())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val firebaseUser: FirebaseUser = task.result!!.user!! //только что зареганый юзер

                    //записываем пользователя в бд
                    val user = User( firebaseUser.uid, name.trim(), surname.trim(),
                        patronymic.trim(), address.trim(), gender.trim(),
                        phone.trim(), email.trim(), 0, achievements = mapOf(
                            "1" to false,
                            "2" to false,
                            "3" to false,
                            "4" to false,
                            "5" to false,
                            "6" to false,
                            "7" to false,
                            "8" to false,
                            "9" to false,
                            "10" to false
                        ))

                    FirestoreClass().registerUser(user)

                    // TODO сообщить пользователю об успешной авторизации
                } else {
                    // TODO если авторизация не удалась, вывести сообщение об этом
                }
            }
    }
}