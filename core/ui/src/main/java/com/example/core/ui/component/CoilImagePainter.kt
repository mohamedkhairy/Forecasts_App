package com.example.core.ui.component

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.ImageLoader
import coil.request.CachePolicy
import coil.util.DebugLogger
import okhttp3.OkHttpClient
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.*

@Composable
fun CoilImagePainter(imageUrl: String?): Painter {


//    val imageLoader = createInsecureImageLoader(LocalContext.current)

//    val painter = rememberAsyncImagePainter(
//        model = imageUrl,
//        imageLoader = imageLoader
//    )

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .error(android.R.drawable.ic_menu_gallery)
            .data(imageUrl)
            .crossfade(true)
            .crossfade(200)
            .build(),
   )

    when (painter.state) {
        is AsyncImagePainter.State.Loading -> {
            Log.d("xxx", "painter loading")
            // Show loading indicator
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(48.dp),
                )
            }
        }

        is AsyncImagePainter.State.Error -> {
            // Show error message
            Log.d("xxx" , "error")
            (painter.state as AsyncImagePainter.State.Error).apply {
                result.throwable.printStackTrace()
                result.throwable.message
            }
            Text(
                text = "Error loading image",
                color = MaterialTheme.colorScheme.error,
            )
        }

        else -> {
            Log.d("xxx", "painter else")
        }
    }

    return painter
}


fun createInsecureImageLoader(context: Context): ImageLoader {
    val trustAllCerts = arrayOf<TrustManager>(
        @SuppressLint("CustomX509TrustManager")
        object : X509TrustManager {
            @SuppressLint("TrustAllX509TrustManager")
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
            @SuppressLint("TrustAllX509TrustManager")
            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
            override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
        }
    )

    val sslContext = SSLContext.getInstance("SSL").apply {
        init(null, trustAllCerts, java.security.SecureRandom())
    }

    val okHttpClient = OkHttpClient.Builder()
        .sslSocketFactory(sslContext.socketFactory, trustAllCerts[0] as X509TrustManager)
        .hostnameVerifier { _, _ -> true }
        .build()

    return ImageLoader.Builder(context)
        .logger(DebugLogger())
        .okHttpClient(okHttpClient)
        .diskCachePolicy(CachePolicy.ENABLED)
        .build()
}
