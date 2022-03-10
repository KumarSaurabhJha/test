package com.kumar.test.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.kumar.test.R
import com.kumar.test.databinding.ActivityMainBinding
import com.kumar.test.presentation.ui.adapter.PictureAdapter
import com.kumar.test.presentation.ui.dialogs.DoubleActionAlertDialog
import com.kumar.test.presentation.viewmodel.PictureViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val pictureViewModel: PictureViewModel by viewModel()

    private val adapter = PictureAdapter { id ->
        onThumbnailClick(id)
    }

    private fun onThumbnailClick(id: Int) {
        PictureDetailActivity.launchActivity(this, id)
    }

    private val onErrorDialog = DoubleActionAlertDialog(
        R.string.dialog_error_title,
        R.string.dialog_error_message,
        R.string.dialog_error_positive_button,
        R.string.dialog_error_negative_button,
        {
            hideErrorDialog()
            showPicturesView()
            pictureViewModel.onRetryClick()
        },
        { finish() })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        pictureViewModel.init()
        setContentView(view)
        initViews()
        setObservers()
    }

    private fun initViews() {
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val recyclerView = binding.mainScreenRecyclerView
        recyclerView.layoutManager = GridLayoutManager(this, 5)
        recyclerView.adapter = adapter

    }

    private fun setObservers() {

        pictureViewModel.photoDisplayList.observe(this) {
            it.getContentIfNotHandled()?.let { pictureList ->
                adapter.setPictureList(pictureList)
                showPicturesView()
            }
        }

        pictureViewModel.onError.observe(this) {
            it.getContentIfNotHandled()?.let { onError ->
                if (onError) {
                    showErrorDialog()
                }
            }
        }

        pictureViewModel.noElement.observe(this) {
            it.getContentIfNotHandled()?.let { noElement ->
                if (noElement) {
                    showNoPictureFoundView()
                }
            }
        }
    }

    private fun showErrorDialog() {
        if (!onErrorDialog.isAdded) {
            onErrorDialog.isCancelable = false
            onErrorDialog.show(supportFragmentManager, "onError")
        }
    }

    private fun hideErrorDialog() {
        if (onErrorDialog.isAdded) {
            onErrorDialog.dismiss()
        }
    }

    private fun showNoPictureFoundView() {
        binding.mainScreenRecyclerView.visibility = View.GONE
        binding.mainScreenNoElement.visibility = View.VISIBLE
    }

    private fun showPicturesView() {
        binding.mainScreenRecyclerView.visibility = View.VISIBLE
        binding.mainScreenNoElement.visibility = View.GONE

    }


}