package com.mikerzdev.newsapp.presentation.util

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.mikerzdev.newsapp.R

fun Context.createErrorDialog(error: Throwable) = AlertDialog.Builder(this)
    .setTitle(getString(R.string.dialog_error_title))
    .setMessage("${getString(R.string.error_dialog_message_start)} ${error.localizedMessage}")
    .setPositiveButton(getString(R.string.word_dismiss), null)
    .create()