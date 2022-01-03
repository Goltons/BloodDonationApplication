package com.example.blooddonationapplication.ui.gallery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GalleryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {

        value = "abc"
    }
    var text: MutableLiveData<String> = _text
    /*private val _bloodDonationApplyments= ArrayList<String>().apply {

    }*/

}