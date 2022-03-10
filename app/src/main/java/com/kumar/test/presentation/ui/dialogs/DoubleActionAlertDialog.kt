package com.kumar.test.presentation.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class DoubleActionAlertDialog(
    private val titleResId: Int,
    private val messageResId: Int,
    private val positiveButtonResId: Int,
    private val negativeButtonResId: Int,
    private val onPositiveClickListener: () -> Unit,
    private val onNegativeClickListener: () -> Unit
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setTitle(titleResId)
            .setMessage(messageResId)
            .setPositiveButton(positiveButtonResId) { _, _ ->
                onPositiveClickListener()
            }
            .setNegativeButton(negativeButtonResId) { _, _ ->
                onNegativeClickListener()
            }
            .create()

}