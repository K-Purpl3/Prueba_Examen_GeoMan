package com.example.prueba_examen_georgemanuel

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
import com.example.prueba_examen_georgemanuel.MainActivityHolder

actual fun openUrl(url: String) {
    val context = MainActivityHolder.context ?: return
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    ContextCompat.startActivity(context, intent, null)
}
