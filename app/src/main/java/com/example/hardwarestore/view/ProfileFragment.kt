package com.example.hardwarestore.view


import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hardwarestore.R
import com.example.hardwarestore.databinding.ProfileFragmentBinding
import com.example.hardwarestore.viewmodel.ActivityViewModel
import com.example.hardwarestore.viewmodel.RegistrationViewModel
import com.example.hardwarestore.viewmodel.UserTimeViewModel


class ProfileFragment : Fragment() {
    private var _binding: ProfileFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var image:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ProfileFragmentBinding.inflate(inflater,container, false)
        return binding.root


        }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val activityViewModel = ViewModelProvider(requireActivity())[ActivityViewModel::class.java]
        val userTimeViewModel = ViewModelProvider(this)[UserTimeViewModel::class.java]
        val userViewModel = ViewModelProvider(this)[RegistrationViewModel::class.java]



        binding.idUser.text = "${activityViewModel.user!!.id}"
        binding.nameUser.text = "${activityViewModel.user!!.lastname} ${activityViewModel.user!!.firstName}"

        binding.settingsProfile.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_settingFragment)
        }

        binding.idUser.setOnClickListener {
            copyTextToClipboard()
        }

        binding.exit.setOnClickListener {
            userTimeViewModel.getUserTime().observe(viewLifecycleOwner) {
                for (i in it) {
                    userTimeViewModel.delete(activityViewModel.user!!.id,i.id)
                }
            }
            val intent = Intent(requireContext(),StartActivity::class.java)
            startActivity(intent)
        }

        userViewModel.getImage(activityViewModel.user!!.id).observe(viewLifecycleOwner){
            Log.v("test7","$it")
            if (it == "2"){
                binding.image.setImageResource(R.drawable.camera)
            }else{
                //val uri: Uri? = requireContext().contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, it)
           binding.image.setImageURI(it.toUri())
        }
        }


       binding.imageProfile.setOnClickListener {
           val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
           intent.type = "image/*"
           startActivityForResult(intent, 1)
       }

        binding.historyBuy.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_fragmentHistory)
        }

        binding.address.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_locationFragment)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val userViewModel = ViewModelProvider(this)[RegistrationViewModel::class.java]
        val activityViewModel = ViewModelProvider(requireActivity())[ActivityViewModel::class.java]
        if (resultCode == Activity.RESULT_OK && requestCode == 1) {
            val imageUri = data?.data
            image = "$imageUri"

            userViewModel.update(imageUri.toString(), activityViewModel.user!!.id)


        }
    }

    private fun copyTextToClipboard() {
        val textToCopy = binding.idUser.text
        val clipboardManager = activity?.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("text", textToCopy)
        clipboardManager.setPrimaryClip(clipData)
        Toast.makeText(requireContext(), "Скопировано", Toast.LENGTH_LONG).show()
    }




}