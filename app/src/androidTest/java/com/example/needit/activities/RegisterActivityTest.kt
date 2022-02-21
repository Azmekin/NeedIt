package com.example.needit.activities

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*


@RunWith(AndroidJUnit4::class)
class RegisterActivityTest {
    val registerActivity = RegisterActivity();

    @Test
    fun registerUserTest(){

        registerActivity.registerUser("Leha", "Tumbaev", "Aleksandrovich",
        "Barnaul", "male", "my@mail.com", "2134", "4321")


    }
}