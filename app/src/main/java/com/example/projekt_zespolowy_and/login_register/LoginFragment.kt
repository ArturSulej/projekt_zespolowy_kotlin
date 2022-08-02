package com.example.projekt_zespolowy_and.login_register

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import com.example.projekt_zespolowy_and.FragmentNavigation
import com.example.projekt_zespolowy_and.HomeFragment
import com.example.projekt_zespolowy_and.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var button_login: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //param1 = it.getString(ARG_PARAM1)
            //param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_login, container, false)

        username = view.findViewById(R.id.username_login)
        password = view.findViewById(R.id.password_login)
        button_login = view.findViewById(R.id.button_login)

        view.findViewById<Button>(R.id.button_register).setOnClickListener {
            var navRegister = activity as FragmentNavigation
            navRegister.navigateFrag(RegisterFragment(),false)
        }

        view.findViewById<Button>(R.id.button_login).setOnClickListener{
            validateForm()
        }

        return view
    }

    private fun validateForm() {
        val icon = AppCompatResources.getDrawable(requireContext(),R.drawable.img_logo)

        icon?.setBounds(0,0,icon.intrinsicWidth,icon.intrinsicHeight)
        when{
            TextUtils.isEmpty(username.text.toString().trim())->{
                username.setError("Podaj adres email")
            }
            TextUtils.isEmpty(password.text.toString().trim())->{
                password.setError("Podaj hasło")
            }

            username.text.toString().isNotEmpty() &&
                    password.text.toString().isNotEmpty() ->
            {
                if (username.text.toString().matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))){
                    if(password.text.toString().length>=5){
                        firebaseSignIn()
                        //Toast.makeText(context,"Logged In",Toast.LENGTH_SHORT).show()
                    }else{
                        password.setError("Hasło musi być dłuższe niż 5 znaków")
                    }
                }else{
                    username.setError("Podaj poprawny adres email")
                }
            }
        }
    }

    private fun firebaseSignIn() {
        button_login.isEnabled = false
        button_login.alpha = 0.5f
        Toast.makeText(context,"Zalogowano",Toast.LENGTH_SHORT).show()
        var navHome = activity as FragmentNavigation
        navHome.navigateFrag(HomeFragment(),true)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}