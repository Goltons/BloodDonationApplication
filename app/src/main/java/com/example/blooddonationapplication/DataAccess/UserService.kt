package com.example.blooddonationapplication.DataAccess

import android.content.ContentValues.TAG
import android.util.Log
import com.example.blooddonationapplication.data.User
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot


class UserService :IUserService{
    private val db=FirebaseFirestore.getInstance()
    private val userDocRef:CollectionReference=db.collection("users")
    var sonuc="fail"
    @Throws(java.lang.IllegalArgumentException::class)
   override  fun save(user: User): Unit {
        var result=false
        try {
            userDocRef
                .whereEqualTo("identificationNumber", user.identificationNumber)
                .get().addOnSuccessListener { task->
                    result = task.isEmpty
                    if (task.isEmpty){
                      sonuc= onSuccess(result,user)
                    }
                    else
                       sonuc= onSuccess(result,user)
                    Log.d(TAG,"döküman var mı ?")
                }.addOnFailureListener{
                    throw it
                }
        }
        catch (e:java.lang.IllegalArgumentException){
            onSuccess(result,user)
        }
    }
     fun onSuccess(boolean: Boolean,user: User):String{
        if(boolean){
            userDocRef.add(user)
            return "success"
        }
        else
            return "fail"

    }
     override fun validate(user: User): Boolean {
        if( user.identificationNumber==null||user.identificationNumber==0L
            ||user.identificationNumber!! %2!=0L
            ||user.identificationNumber.toString().length!=11
            ||user.identificationNumber.toString().startsWith("0")
            ||user.userFirstName==null||user.userLastName==null
            ||user.userPassword===null
            ||user.userPassword.toString().length>5
            ||user.bloodGroup==null||user.address?.city==null
            ||user.address?.district==null||user.address?.apartmentNumber==null
            ||user.address?.buildingNumber==null||user.address?.street==null
            ||user.address?.quarter==null|| user.communication!!.email==null
            || user.communication!!.phoneNumber==null||user.birthDate==null
        ) return  false
        return true

    }

    override fun login(idNo: Long, password: String): Boolean {
        var result=false
        userDocRef.whereEqualTo("identificationNumber",idNo)
            .whereEqualTo("userPassword",password).get().addOnSuccessListener { task->
                result = task.isEmpty
                Log.d(TAG,"döküman var mı ? ${task.isEmpty}")
                if(result)
                    saveSharedPref(result,task.elementAt(0).toObject(User::class.java))
                else
                    throw java.lang.IllegalArgumentException("yanlış veya hatalı  bilgi girdiniz.")
            }
            .addOnFailureListener{
                throw IllegalArgumentException()
            }
        return true
    }
    fun saveSharedPref(boolean: Boolean,user: User){


    }
    override fun logOut() {

    }

    override fun getUser(idNo: Long):String {
        var user:User
        val collectionReference: CollectionReference =db.collection("users")
        val query: Query =collectionReference.whereEqualTo("identificationNumber",idNo)
        val qsnap: Task<QuerySnapshot> =query.get()

        return qsnap.toString()
    }

    constructor(){}

}