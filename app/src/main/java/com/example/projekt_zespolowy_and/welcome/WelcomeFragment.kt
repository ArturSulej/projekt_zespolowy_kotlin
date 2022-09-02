package com.example.projekt_zespolowy_and.welcome

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.example.projekt_zespolowy_and.FragmentNavigation
import com.example.projekt_zespolowy_and.R
import com.example.projekt_zespolowy_and.login_register.LoginFragment

// TODO: Rename parameter arguments, choose names that match
// TODO: add swipe gestures
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WelcomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WelcomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var button_next: ImageButton
    private lateinit var button_skip: Button
    private lateinit var info1: RadioButton
    private lateinit var info2: RadioButton
    private lateinit var info3: RadioButton
    private lateinit var radioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_welcome, container, false)

        var view = inflater.inflate(R.layout.fragment_welcome, container, false)

        button_next = view.findViewById(R.id.buttonNext)
        button_skip = view.findViewById(R.id.buttonSkip)
        info1 = view.findViewById(R.id.radioButton)
        info2 = view.findViewById(R.id.radioButton2)
        info3 = view.findViewById(R.id.radioButton3)
        radioGroup = view.findViewById(R.id.radioGroup)

        val radioButtonID: Int = radioGroup.getCheckedRadioButtonId()
        val radioButton: View = radioGroup.findViewById(radioButtonID)
        val idx: Int = radioGroup.indexOfChild(radioButton)

        button_next.setOnClickListener{
            navigate(WelcomeFragment2(),false)
        }

        info2.setOnClickListener{
            navigate(WelcomeFragment2(),false)
        }

        info3.setOnClickListener{
            navigate(WelcomeFragment3(),false)
        }

        button_skip.setOnClickListener {
            navigate(LoginFragment(),false)
        }

        return view
    }

    fun navigate(name: Fragment, stack: Boolean){
        var navNext = activity as FragmentNavigation
        navNext.navigateFrag(name,stack)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WelcomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WelcomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}