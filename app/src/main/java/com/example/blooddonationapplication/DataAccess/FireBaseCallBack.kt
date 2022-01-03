package com.example.blooddonationapplication.DataAccess

import com.example.blooddonationapplication.data.Response

interface FirebaseCallback {
    fun onResponse(response: Response)
}