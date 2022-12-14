package com.example.projekt_zespolowy_and

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projekt_zespolowy_and.login_register.SignInFragment
import com.example.projekt_zespolowy_and.login_register.RegisterFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    var recyclerView: RecyclerView? = null
    var productList = ArrayList<Product>()
    private var adapter: ProductAdapter? = null
    private lateinit var homeButton: ImageButton
    private lateinit var menuButton: ImageButton
    private lateinit var accountButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_home, container, false)

        homeButton = view.findViewById(R.id.glownaButton)
        menuButton = view.findViewById(R.id.menuButton)
        accountButton = view.findViewById(R.id.kontoButton)

        val firebaseListener = object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("test",snapshot.toString())

                productList.clear()

                val child = snapshot.children
                child.forEach{
                    var products = Product(it.child("img").value.toString(),
                    it.child("name").value.toString(),
                    it.child("description").value.toString())

                    productList.add(products)
                }

                adapter?.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("test",error.message)
            }

        }

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = GridLayoutManager(context,2,
            GridLayoutManager.VERTICAL,false)

        adapter = ProductAdapter(productList)
        recyclerView?.adapter = adapter

        /*view.findViewById<Button>(R.id.button_logout).setOnClickListener{
            var navLogin = activity as FragmentNavigation
            navLogin.navigateFrag(LoginFragment(),false)
        }*/

        view.findViewById<ImageButton>(R.id.glownaButton).setOnClickListener {
            var navRegister = activity as FragmentNavigation
            navRegister.navigateFrag(HomeFragment(),false)
        }
        view.findViewById<ImageButton>(R.id.menuButton).setOnClickListener {
            var navRegister = activity as FragmentNavigation
            navRegister.navigateFrag(MenuFragment(),true)
        }
        /*
        view.findViewById<Button>(R.id.kontoButton).setOnClickListener {
            var navRegister = activity as FragmentNavigation
            navRegister.navigateFrag(HomeFragment(),true)
        }*/
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}