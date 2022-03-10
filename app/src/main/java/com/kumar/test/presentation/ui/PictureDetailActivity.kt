package com.kumar.test.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.kumar.test.R
import com.kumar.test.databinding.ActivityPictureDetailBinding
import com.kumar.test.presentation.ui.dialogs.DoubleActionAlertDialog
import com.kumar.test.presentation.viewmodel.PictureDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PictureDetailActivity : AppCompatActivity() {

    private val pictureDetailViewModel: PictureDetailViewModel by viewModel()
    private lateinit var binding: ActivityPictureDetailBinding

    private var selectedID: Int = -1

    private val onErrorDialog = DoubleActionAlertDialog(
        R.string.dialog_error_title,
        R.string.dialog_error_message,
        R.string.dialog_error_positive_button,
        R.string.dialog_error_negative_button,
        {
            hideErrorDialog()
            pictureDetailViewModel.init(selectedID)
        },
        { finish() })

    companion object {
        private const val KEY_IMAGE_ID = "imageid"
        fun launchActivity(activity: AppCompatActivity, imageId: Int) {
            val intent = Intent(activity, PictureDetailActivity::class.java).apply {
                putExtra(KEY_IMAGE_ID, imageId)
            }
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPictureDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        selectedID = intent.getIntExtra(KEY_IMAGE_ID, -1)
        pictureDetailViewModel.init(selectedID)

        setUpObserver()
    }

    private fun setUpObserver() {
        pictureDetailViewModel.photo.observe(this) {
            it.getContentIfNotHandled()?.let { selectedPicture ->
                binding.pictureDetailName.text = selectedPicture.title
                binding.pictureDetailImage.load(selectedPicture.url) {
                    placeholder(R.drawable.ic_launcher_background)
                }
            }
        }

        pictureDetailViewModel.onError.observe(this) {
            it.getContentIfNotHandled()?.let { onError ->
                if (onError) {
                    showErrorDialog()
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

}