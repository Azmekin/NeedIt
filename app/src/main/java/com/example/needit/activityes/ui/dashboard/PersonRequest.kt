package com.example.needit.activityes.ui.dashboard

var personList = mutableListOf<PersonRequest>()

val PERSON_TD_EXTRA = "personExtra"

data class PersonRequest(val imageID:Int, val name:String, val surname:String, val description:String, val typeReq:String, val address:String)
