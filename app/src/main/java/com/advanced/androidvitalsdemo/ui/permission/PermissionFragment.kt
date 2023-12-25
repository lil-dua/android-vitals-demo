package com.advanced.androidvitalsdemo.ui.permission

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.advanced.androidvitalsdemo.databinding.FragmentPermissionBinding
import com.google.android.material.snackbar.Snackbar

class PermissionFragment : Fragment() {

    private lateinit var binding: FragmentPermissionBinding
    private val viewModel: PermissionViewModel by viewModels()

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                viewModel.updateCameraPermission(1)
            } else {
                viewModel.updateCameraPermission(0)
            }
        }

    private val cameraActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val imageBitmap = data?.extras?.get("data") as Bitmap?
                imageBitmap?.let {
                    binding.imageCamera.setImageBitmap(it)
                }
            } else {
                // Handle other cases if needed
            }
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPermissionBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.cameraPermisson.observe(viewLifecycleOwner) {
            if (it == 1) binding.textData.text = "Allow"
            else binding.textData.text = "Not Allow"
        }

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnOpenCamera.setOnClickListener {
            accessCamera()
        }

        binding.btnRequestPermission.setOnClickListener {
            requestCameraPermission()
        }
    }

    private fun hasCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCameraPermission() {
        if (!hasCameraPermission()) {
            // Launch the camera permission request
            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        } else {
            // Permission already granted, you can proceed with using the camera
            accessCamera()
        }
    }

    private fun accessCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (hasCameraPermission()) {
            cameraActivityResultLauncher.launch(cameraIntent)
        }else {
            showSnackBar("You need accept permission for camera")
        }
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}