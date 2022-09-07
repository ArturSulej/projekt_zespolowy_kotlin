package com.example.projekt_zespolowy_and

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.projekt_zespolowy_and.login_register.SignInFragment
import com.example.projekt_zespolowy_and.welcome.WelcomeFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity(), FragmentNavigation {
    private lateinit var fAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ss:String = intent.getStringExtra("Logged").toString()


        if(ss == "yes"){
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HomeFragment()).addToBackStack(null)
                .commit()
        }else{
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WelcomeFragment()).addToBackStack(null)
                .commit()
            //add
        }
    /*
        fAuth = Firebase.auth
        val currentUser = fAuth.currentUser
        if(currentUser != null){
            supportFragmentManager.beginTransaction()
                .add(R.id.container, WelcomeFragment()).addToBackStack(null)
                .commit()
        }else{
            supportFragmentManager.beginTransaction()
                .add(R.id.container, LoginFragment())
                .commit()
        }
*/

    }

    override fun navigateFrag(fragment: Fragment, addToStack: Boolean) {
        val transaction = supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)

        if(addToStack){
            transaction.addToBackStack(null)
        }

        transaction.commit()
    }

}