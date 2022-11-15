package com.illia.finalproject.retrofit

import com.google.gson.annotations.SerializedName


class Something(
    @SerializedName("name")
    private var name: String,
    @SerializedName("number")
    private var number: Int
) {

    fun getNameOfSomething() : String{
        return name
    }

    fun getNumberFromSomething() : Int{
        return number
    }


    override fun toString(): String {
        return "Entity called $name has number: $number"
    }
}