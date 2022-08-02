package com.example.projekt_zespolowy_and

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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var  username: EditText
    private lateinit var password: EditText
    private lateinit var check_password: EditText
    private lateinit var button_register: Button
    private lateinit var fAuth: FirebaseAuth

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
        var view =  inflater.inflate(R.layout.fragment_register, container, false)

        username = view.findViewById(R.id.username_register)
        password = view.findViewById(R.id.password_register)
        check_password = view.findViewById(R.id.password_check_register)
        button_register = view.findViewById<Button>(R.id.button_register_reg)
        fAuth = Firebase.auth

        view.findViewById<Button>(R.id.button_login_reg).setOnClickListener {
            var navRegister = activity as FragmentNavigation
            navRegister.navigateFrag(LoginFragment(),false)
        }

        view.findViewById<Button>(R.id.button_register_reg).setOnClickListener{
            validateEmptyForm()
        }

        return view
    }

    private fun validateEmptyForm() {
        val icon = AppCompatResources.getDrawable(requireContext(),R.drawable.img_logo)

        icon?.setBounds(0,0,icon.intrinsicWidth,icon.intrinsicHeight)
        when{
            TextUtils.isEmpty(username.text.toString().trim())->{
                username.setError("Podaj adres email")
            }
            TextUtils.isEmpty(password.text.toString().trim())->{
                password.setError("Wprowadź hasło")
            }
            TextUtils.isEmpty(check_password.text.toString().trim())->{
                check_password.setError("Potwierdź hasło")
            }

            username.text.toString().isNotEmpty() &&
                    password.text.toString().isNotEmpty() &&
                    check_password.text.toString().isNotEmpty() ->
            {
                if (username.text.toString().matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))){
                    if(password.text.toString().length>=5){
                        if(check_password.text.toString() == password.text.toString()){
                            firebaseSignUp()
                        }else{
                            check_password.setError("Hasła muszą się zgadzać")
                        }
                    }else{
                        password.setError("Hasło musi być dłuższe niż 5 znaków")
                    }
                }else{
                    username.setError("Wprowadź poprawny adres email")
                }
            }
        }
    }

    private fun firebaseSignUp() {
        button_register.isEnabled = false
        button_register.alpha = 0.5f
        fAuth.createUserWithEmailAndPassword(username.text.toString(),
            password.text.toString()).addOnCompleteListener{
             task ->
                if (task.isSuccessful){
                    Toast.makeText(context,"Utworzono konto",Toast.LENGTH_SHORT).show()
                    var navHome = activity as FragmentNavigation
                    navHome.navigateFrag(LoginFragment(),false)
                }else{
                    button_register.isEnabled = true
                    button_register.alpha = 1.0f
                    Toast.makeText(context,task.exception?.message,Toast.LENGTH_SHORT).show()
                }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegisterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}