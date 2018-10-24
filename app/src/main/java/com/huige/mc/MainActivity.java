package com.huige.mc;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.huige.mc.jsinterface.JavascriptInterface;
import com.huige.mc.view.BaseActivity;
import com.huige.mc.view.SettingActivity;
import com.huige.mc.vo.VoServerLinkParameter;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.IOException;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    @ViewInject(R.id.web_view)
    private WebView webView;

    private Handler handler;

    private VoServerLinkParameter linkParameter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        gson = new Gson();
        Log.i(TAG, "初始化");
        initHandler();
        try {
            initWebView();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG,"initWebView 初始化失败!");
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() throws IOException {
        linkParameter = (VoServerLinkParameter) mACache.getAsObject(SettingActivity.SERVER_LINK_PARM);

        WebSettings webSettings = webView.getSettings();
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(false);

        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);

        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        webView.loadUrl("file:///android_asset/web/index.html");

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.i(TAG, "onPageFinished url:" + url);
                if (url.equals("file:///android_asset/web/index.html")) {
                    initWebPage(view);
                }
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView webView, String s, String s1, final JsResult jsResult) {
//                new AlertDialog.Builder(MainActivity.this)
//                        .setTitle("消息")
//                        .setMessage(s1)
//                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                jsResult.confirm();
//                            }
//                        })
//                        .setCancelable(false)
//                        .create().show();
                Toast.makeText(MainActivity.this,s1,Toast.LENGTH_LONG).show();
                jsResult.confirm();
                return true;
            }
        });

        webView.addJavascriptInterface(new JavascriptInterface(), "android");

    }

    private void initWebPage(WebView view) {
        view.post(new Runnable() {
            @Override
            public void run() {
                if (linkParameter != null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        String parmJson = gson.toJson(linkParameter);
                        webView.evaluateJavascript("javascript:init('" + parmJson + "')", new ValueCallback<String>() {
                            @Override
                            public void onReceiveValue(String s) {
                                Log.i(TAG, "服务器连接参数回调" + s);
                            }
                        });
                    } else {
                        webView.loadUrl("javascript:init()");
                    }
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        webView.evaluateJavascript("javascript:initFailure()", new ValueCallback<String>() {
                            @Override
                            public void onReceiveValue(String s) {
                                Log.e(TAG, s);
                                Log.e(TAG, "初始化失败");
                            }
                        });
                    } else {
                        webView.loadUrl("javascript:initFailure()");
                    }
                }
            }
        });
    }

    private void initHandler() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        Toast.makeText(MainActivity.this, (String) msg.obj, Toast.LENGTH_LONG).show();
                        break;
                }
            }
        };
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webView.clearHistory();
            ((ViewGroup) webView.getParent()).removeView(webView);
            webView.destroy();
            webView = null;
        }
    }
}
