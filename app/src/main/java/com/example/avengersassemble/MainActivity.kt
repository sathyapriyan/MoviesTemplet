package com.example.avengersassemble

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.avengersassemble.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    override fun onBackPressed() {
        MaterialAlertDialogBuilder(this)
            .setMessage("Are you sure want to Exit?")
            .setIcon(R.drawable.ic_disabled)
            .setCancelable(false)
            .setPositiveButton("YES") {  dialog, _ -> finishAffinity() }
            .setNeutralButton("LOGOUT") {  dialog, _ -> dialog.dismiss() }
            .setNegativeButton("CANCEL") {  dialog, _ -> dialog.dismiss() }
            .show()

    }


}