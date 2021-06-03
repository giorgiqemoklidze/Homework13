package com.example.homework13


data class Result<T>(val status : Status,val data : T? = null,val message :String? = null) {

    companion object{

        fun <T> isSucsessfull(data:T) : Result<T>{
            return Result(Status.SUCCESS,data)
        }

        fun <T> error(message:String):Result<T>{
            return Result(Status.ERROR,null,message)
        }

    }



    enum class Status {
        SUCCESS,
        ERROR

    }
}

