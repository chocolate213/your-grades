package com.jxzhang.yourgrades.activity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.jxzhang.yourgrades.R;
import com.jxzhang.yourgrades.util.MyApplication;

/**
 * Created by J.X.Zhang on 2015/10/22.
 * 图书详细信息Activity
 */

public class BookInfoActivity extends Activity {

    private WebView web_view;
    final Activity activity = this;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);

        web_view = (WebView) findViewById(R.id.web_view);

        final String bookName = getIntent().getStringExtra("book_name");
        final String bookCode = getIntent().getStringExtra("book_code");

        setBooknameToActionBar(bookName);

        String url = "http://lib.hhhxy.cn:88/ggjs/ckgc.jsp?xz=" + bookCode + "&jstj=%D5%FD%CC%E2%C3%FB&value1=" + bookName + "&jsfs=%D6%D0%BC%E4%C6%A5%C5%E4&pxtj=ZTM&pagesize=500&goNum=1&servertype=&ljh=&tslx=zw&ckyy=yy&page=1&jstj=%D5%FD%CC%E2%C3%FB&value1=" + bookName + "&jsfs=%D6%D0%BC%E4%C6%A5%C5%E4&pxtj=ZTM&pagesize=500";

        initBookInfoWebView(url);
    }

    private void initBookInfoWebView(String url) {

        web_view.getSettings().setJavaScriptEnabled(true);
        web_view.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                Toast.makeText(MyApplication.getContext(), "Oh no! " + description,
                        Toast.LENGTH_SHORT).show();
                activity.finish();
            }

            /**
             * 在当前窗口打开页面链接
             * @param view
             * @param url
             * @return
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        web_view.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                activity.setProgress(progress * 1000);
            }
        });

        web_view.loadUrl(url);
    }

    private void setBooknameToActionBar(String bookName) {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle(bookName);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(false);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
