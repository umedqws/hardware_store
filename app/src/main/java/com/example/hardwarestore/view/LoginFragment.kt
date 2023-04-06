package com.example.hardwarestore.view

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.IntDef
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hardwarestore.R
import com.example.hardwarestore.databinding.FragmentLoginBinding
import com.example.hardwarestore.viewmodel.RegistrationViewModel
import com.example.hardwarestore.viewmodel.UserTimeViewModel
import com.google.android.material.tabs.TabLayout.Mode
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater)
        return binding.root

    }

    @SuppressLint("CommitPrefEdits")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val userViewModel = ViewModelProvider(this)[RegistrationViewModel::class.java]
        val userTimeViewModel = ViewModelProvider(this)[UserTimeViewModel::class.java]



        binding.logoText.setOnClickListener {
           // userViewModel.insertNewUser("user","user","user","111","111")
        }

        binding.registration.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_loginFragment2_to_registrationFragment2)
        }

        val sharedPreferences = context?.getSharedPreferences("myPref",MODE_PRIVATE)
        val editor = sharedPreferences?.edit()

      /*  lifecycleScope.launch{
            delay(0)
            val password =sharedPreferences?.getString("user_password",null)
            val number = sharedPreferences?.getString("user_phone",null)
            Log.v("asdf","$password - - $number")
            binding.numberLogin.setText(binding.numberLogin.text.toString() + number)
            binding.passwordLogin.setText(binding.passwordLogin.text.toString()+password)
            if( binding.numberLogin.text.toString() == number && binding.passwordLogin.text.toString() == password){
               val intent =Intent(requireContext(), MainActivity::class.java)
               startActivity(intent)
           }else{
                Toast.makeText(
                    binding.root.context,
                    "Неправльный пароль или номер телефона",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
*/


        binding.showPasswordToggle.setOnCheckedChangeListener {_,isChecked->
            if (isChecked) {
                binding.passwordLogin.inputType = InputType.TYPE_CLASS_TEXT

                binding.showPasswordToggle.setBackgroundResource(R.drawable.ic_baseline_visibility)
            } else {
                binding.showPasswordToggle.setBackgroundResource(R.drawable.ic_baseline_visibility_off_24)
                binding.passwordLogin.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
        }

        binding.login.setOnClickListener {

            if ((binding.numberLogin.text.toString() == "") && (binding.passwordLogin.text.toString() == "")){
                Toast.makeText(
                    binding.root.context,
                    "Неправльный пароль или номер телефона",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                userViewModel.getUsers(
                    binding.passwordLogin.text.toString(),
                    binding.numberLogin.text.toString()
                ).observe(viewLifecycleOwner) {
                    if (it == null) {
                        Toast.makeText(
                            binding.root.context,
                            "Неправльный пароль или номер телефона",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        if (binding.numberLogin.text.toString() == it.numberPhone && binding.passwordLogin.text.toString() == it.password) {
                            val intent =Intent(requireContext(), MainActivity::class.java)
                            intent.putExtra("title", it)
                            startActivity(intent)
                            userTimeViewModel.insertTImeUser(it.id)
                        } else {
                            Toast.makeText(
                                binding.root.context,
                                "Неправльный пароль или номер телефона",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

            }
        }

    }
}




