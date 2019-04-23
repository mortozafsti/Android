package com.example.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    WebView simpleWebView;
    Button loadWebPage, loadFromStaticHtml;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initiate buttons and a web view
        loadFromStaticHtml = (Button) findViewById(R.id.loadFromStaticHtml);
        loadFromStaticHtml.setOnClickListener(this);
        loadWebPage = (Button) findViewById(R.id.loadWebPage);
        loadWebPage.setOnClickListener(this);
        simpleWebView = (WebView) findViewById(R.id.simpleWebView);
        simpleWebView.setWebViewClient(new MyWebViewClient());
        String url = "http://coderbd.com/";
        simpleWebView.getSettings().setJavaScriptEnabled(true);
        simpleWebView.loadUrl(url); // load a web page in a web view
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loadFromStaticHtml:
                // define static html text
                String customHtml = "<html><body><h1>Hello, Nadia Tasnim Tonima Tanzim Rakib</h1>" +
                        "<h1>Heading 1</h1><h2>Heading 2</h2><h3>Heading 3</h3>" +
                        "<p>This is a sample paragraph of static HTML In Web view</p>" +
                        "</body></html>";
                simpleWebView.loadData(customHtml, "text/html", "UTF-8"); // load html string data in a web view
                break;
            case R.id.loadWebPage:
                simpleWebView.setWebViewClient(new MyWebViewClient());
                String url = "http://coderbd.com/";
                simpleWebView.getSettings().setJavaScriptEnabled(true);
                simpleWebView.loadUrl(url); // load a web page in a web view
                break;       }    }
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
