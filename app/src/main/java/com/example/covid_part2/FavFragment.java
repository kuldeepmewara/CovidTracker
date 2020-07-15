package com.example.covid_part2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Mom on 05-04-2020.
 */

public class FavFragment extends Fragment {
    public WebView mWebView;
    public FavFragment()
    {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_fav,container,false);



        mWebView = (WebView) v.findViewById(R.id.webview);
        mWebView.loadUrl("https://www.covid19india.org");

        // Enable Javascript
        WebSettings settings = mWebView.getSettings();
        settings.setDomStorageEnabled(true);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());

       return v;


    }

}
