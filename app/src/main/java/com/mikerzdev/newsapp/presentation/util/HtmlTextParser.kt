package com.mikerzdev.newsapp.presentation.util

import android.os.Build
import android.text.Html
import android.text.Spanned


fun getHtmlSpannedText(source: String?): Spanned? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(source)
    }
}