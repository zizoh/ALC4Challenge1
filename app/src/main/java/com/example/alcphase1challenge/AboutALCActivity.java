package com.example.alcphase1challenge;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.SslError;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.google.android.material.snackbar.Snackbar;


public class AboutALCActivity extends AppCompatActivity {

    WebView webView;
    ProgressBar progressBar;
    LinearLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_alc);

        webView = findViewById(R.id.webview);
        progressBar = findViewById(R.id.progress_bar);
        root = findViewById(R.id.root);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        webView.setWebChromeClient(new WebChromeClient() {

            public void onProgressChanged(WebView view, int progress) {
                progressBar.setProgress(progress * 100);
                if (progress == 100) {
                    progressBar.setVisibility(View.GONE);
                    webView.setVisibility(View.VISIBLE  );
                }
            }


        });

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                progressBar.setVisibility(View.VISIBLE);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
            }
        });

        if (isConnected()) {
            webView.loadUrl("https://andela.com/alc/");
        } else {
            showSnackBar(getString(R.string.no_internet), v -> snackRetryAction());
        }
    }

    private void retryFetch() {
        webView.loadUrl("https://andela.com/alc/");
    }

    private boolean isConnected() {
        if (!isOnline((this))) {
            showSnackBar(getString(R.string.no_internet), v -> snackRetryAction());
        }
        return true;
    }

    private void showSnackBar(String message, View.OnClickListener listener) {
        Snackbar snackbar = Snackbar.make(root, message, Snackbar.LENGTH_LONG)
                .setAction(R.string.retry, listener);

        snackbar.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        snackbar.setActionTextColor(ContextCompat.getColor(this, R.color.white));

        snackbar.show();
    }

    private void snackRetryAction() {
        if (isConnected()) {
            retryFetch();
        }
    }

    public boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, AboutALCActivity.class);
    }
}
