package com.kumar.test.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kumar.test.R
import com.kumar.test.databinding.ActivityMainBinding
import com.kumar.test.presentation.ui.dialogs.DoubleActionAlertDialog
import com.kumar.test.presentation.viewmodel.PictureViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val pictureViewModel: PictureViewModel by viewModel()

    private val onErrorDialog = DoubleActionAlertDialog(
        R.string.dialog_error_title,
        R.string.dialog_error_message,
        R.string.dialog_error_positive_button,
        R.string.dialog_error_negative_button,
        {
            //Todo: load data again
        },
        { finish() })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initViews()
        setObservers()


    }

    private fun initViews() {
        //Todo: Init view here
    }

    private fun setObservers() {

        pictureViewModel.onError.observe(this) {
            //Todo: show error dialog
        }
    }

}