package com.example.health

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.health.ui.theme.HealthTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val healthConnectManager = HealthConnectManager(applicationContext)

        if (!healthConnectManager.getSdkStatus()) return;

        setContentView(R.layout.activity_main)
        val myWebView: WebView = findViewById(R.id.webview)
        val connectClient = healthConnectManager.getConnectClient()
        val healthClient = HealthClient(connectClient, myWebView)

        myWebView.settings.javaScriptEnabled = true
        myWebView.settings.domStorageEnabled = true
        myWebView.webViewClient = HealthWebViewClient(healthClient)
        myWebView.addJavascriptInterface(myWebView.webViewClient, "HealthConnect")
        myWebView.loadUrl("file:///android_asset/web.html")

//        enableEdgeToEdge()
//        setContent {
//            HealthTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    WebViewScreen(client)
//                }
//            }
//        }
    }
}

//@Composable
//fun WebViewScreen(client: HealthClient) {
//    AndroidView(
//        factory = { context ->
//            WebView(context).apply {
//                settings.javaScriptEnabled = true;
//                settings.domStorageEnabled = true
//                addJavascriptInterface(client, "HealthConnect")
//                loadUrl("file:///android_asset/web.html") // 로컬 HTML 파일 사용 권장
//            }
//        },
//        update = { webView ->
//            // WebView 업데이트 로직 (필요한 경우)
//        }
//    )
//}
//
//@Composable
//fun AppTheme(content: @Composable () -> Unit) {
//    MaterialTheme(
//        colorScheme = MaterialTheme.colorScheme
//    ) {
//        content()
//    }
//}