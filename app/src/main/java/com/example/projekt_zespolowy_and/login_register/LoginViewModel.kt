package com.example.projekt_zespolowy_and.login_register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    var logged = MutableLiveData<Boolean>()
    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()
}