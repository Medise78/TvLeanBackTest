package ir.box_net.test.common

import java.lang.reflect.Type

interface JsonParse {
    fun <T> fromJson(json:String,type: Type):T?
    fun <T> toJson(obj:T,type: Type):String?
}