package com.example.app.classes

import java.sql.Date

//@Entity(tableName = "person")
class Person  {
    //@PrimaryKey
    var id: Int
    var avatarUrl: String? = null
    var firstName: String? = null
    var lastName: String? = null
    var userTag: String? = null
    var department: String? = null
    var position: String? = null
    var birthday: Date? = null
    var phone: String? = null

//    override fun toString(): String {
//        return name!!
//    }

    constructor(id: Int,
                avatarUrl_: String?,
                firstName_: String?,
                lastName_: String?,
                userTage_: String?,
                department_: String?,
                position_: String?,
                birthday_: Date?,
                phone_: String?)
    {
        this.id  = id
        this.avatarUrl = avatarUrl_
        this.firstName = firstName_
        this.lastName = lastName_
        this.userTag = userTage_
        this.department = department_
        this.position = position_
        this.birthday = birthday_
        this.phone = phone_

    }

//    fun setImage(img: Int?){
//        this.img =  img
//    }
}