package com.komalmane.practicalTest.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.komalmane.practicalTest.R
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val USER_NAME = "test@android.com"
    private val PASSWORD = "123456"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        login_button.setOnClickListener {
            val username = editUsername.text.toString().toLowerCase()
            val password = editPassword.text.toString()

            if (username.isEmpty() && username != USER_NAME) {
                Toast.makeText(activity, "Invalid Username ", Toast.LENGTH_SHORT).show()
            } else if (password.isEmpty() && password != PASSWORD) {
                Toast.makeText(activity, "Invalid Password ", Toast.LENGTH_SHORT).show()
            } else {
                progressBar.visibility = View.VISIBLE
                Handler(Looper.getMainLooper()).postDelayed({
                    findNavController().navigate(R.id.listingFragment2)
                    progressBar.visibility = View.GONE
                    editUsername.text!!.clear()
                    editPassword.text!!.clear()
                }, 2000)
            }
        }
    }
}