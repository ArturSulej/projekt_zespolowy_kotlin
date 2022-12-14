package com.example.projekt_zespolowy_and.login_register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.projekt_zespolowy_and.FragmentNavigation
import com.example.projekt_zespolowy_and.HomeFragment
import com.example.projekt_zespolowy_and.R
import com.example.projekt_zespolowy_and.databinding.FragmentLoginBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignInFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignInFragment : BottomSheetDialogFragment() {
    // TODO: Rename and change types of parameters
    //private lateinit var username: EditText
    //private lateinit var password: EditText
    //private lateinit var button_login: Button

    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        loginViewModel = ViewModelProvider(activity).get(LoginViewModel::class.java)
        binding.loginFragmentLoginButton.setOnClickListener {
            saveAction()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

        /*
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_login, container, false)

        //username = view.findViewById(R.id.username_login)
        //password = view.findViewById(R.id.password_login)
        button_login = view.findViewById(R.id.button_register)

        view.findViewById<Button>(R.id.button_register).setOnClickListener {
            var navRegister = activity as FragmentNavigation
            navRegister.navigateFrag(RegisterFragment(),false)
        }

        view.findViewById<Button>(R.id.button_register).setOnClickListener{
            //validateForm()
            firebaseSignIn()
        }

        return view
        */
    }

    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = requireActivity()
        loginViewModel = ViewModelProvider(activity).get(LoginViewModel::class.java)
        binding.loginFragmentLoginButton.setOnClickListener {
            saveAction()
        }
    }*/

    private fun saveAction() {
        //loginViewModel.email.value = binding.editTextTextEmailAddress2.text.toString()
        //loginViewModel.password.value = binding.editTextTextPassword.text.toString()
        loginViewModel.logged.value = true
        dismiss()
    }



    /*
    private fun validateForm() {
        val icon = AppCompatResources.getDrawable(requireContext(),R.drawable.img_logo)

        icon?.setBounds(0,0,icon.intrinsicWidth,icon.intrinsicHeight)
        when{
            TextUtils.isEmpty(username.text.toString().trim())->{
                username.setError("Podaj adres email")
            }
            TextUtils.isEmpty(password.text.toString().trim())->{
                password.setError("Podaj has??o")
            }

            username.text.toString().isNotEmpty() &&
                    password.text.toString().isNotEmpty() ->
            {
                if (username.text.toString().matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))){
                    if(password.text.toString().length>=5){
                        firebaseSignIn()
                        //Toast.makeText(context,"Logged In",Toast.LENGTH_SHORT).show()
                    }else{
                        password.setError("Has??o musi by?? d??u??sze ni?? 5 znak??w")
                    }
                }else{
                    username.setError("Podaj poprawny adres email")
                }
            }
        }
    }
*//*
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
            SignInFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }*/
}