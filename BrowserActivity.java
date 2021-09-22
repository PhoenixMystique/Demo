package com.nordvpn.android.browser;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.browser.customtabs.CustomTabsClient;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.customtabs.CustomTabsServiceConnection;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.nordvpn.android.R;
import com.nordvpn.android.browser.j;
import com.nordvpn.android.utils.e0;
import com.nordvpn.android.utils.e1;
import com.nordvpn.android.utils.n1;
import com.nordvpn.android.utils.o2;
import com.nordvpn.android.utils.r0;
import com.nordvpn.android.views.TransparentToolbar;
import com.stripe.android.networking.AnalyticsDataFactory;
import j.g0.d.l;
import j.g0.d.s;
import j.g0.d.x;
import j.n0.p;
import j.z;
import java.util.HashMap;
import java.util.Objects;
import javax.inject.Inject;

public final class BrowserActivity extends f.b.k.b {

    /* renamed from: b  reason: collision with root package name */
    static final /* synthetic */ j.l0.g[] f6566b = {x.e(new s(BrowserActivity.class, "url", "getUrl()Ljava/lang/String;", 0)), x.e(new s(BrowserActivity.class, "browserType", "getBrowserType()Lcom/nordvpn/android/browser/BrowserType;", 0))};

    /* renamed from: c  reason: collision with root package name */
    public static final a f6567c = new a(null);
    @Inject

    /* renamed from: d  reason: collision with root package name */
    public r0 f6568d;
    @Inject

    /* renamed from: e  reason: collision with root package name */
    public e1 f6569e;
    @Inject

    /* renamed from: f  reason: collision with root package name */
    public com.nordvpn.android.a0.a f6570f;

    /* renamed from: g  reason: collision with root package name */
    private final j.i0.d f6571g = com.nordvpn.android.utils.b.b(this, "extra_url");

    /* renamed from: h  reason: collision with root package name */
    private final j.i0.d f6572h = com.nordvpn.android.utils.b.b(this, "browser_type");

    /* renamed from: i  reason: collision with root package name */
    private CustomTabsServiceConnection f6573i;

    /* renamed from: j  reason: collision with root package name */
    private final b f6574j = new b(this);

    /* renamed from: k  reason: collision with root package name */
    private final c f6575k = new c(this);

    /* renamed from: l  reason: collision with root package name */
    private HashMap f6576l;

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(j.g0.d.g gVar) {
            this();
        }
    }

    public static final class b extends WebChromeClient {
        final /* synthetic */ BrowserActivity a;

        b(BrowserActivity browserActivity) {
            this.a = browserActivity;
        }

        public void onProgressChanged(WebView webView, int i2) {
            super.onProgressChanged(webView, i2);
            BrowserActivity browserActivity = this.a;
            int i3 = com.nordvpn.android.c.loading_progress;
            ProgressBar progressBar = (ProgressBar) browserActivity.c(i3);
            progressBar.setVisibility(0);
            progressBar.setProgress(i2);
            if (i2 == 100) {
                ProgressBar progressBar2 = (ProgressBar) this.a.c(i3);
                l.d(progressBar2, "loading_progress");
                progressBar2.setVisibility(8);
            }
        }
    }

    public static final class d extends CustomTabsServiceConnection {
        final /* synthetic */ BrowserActivity a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ CustomTabsIntent f6577b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f6578c;

        d(BrowserActivity browserActivity, CustomTabsIntent customTabsIntent, String str) {
            this.a = browserActivity;
            this.f6577b = customTabsIntent;
            this.f6578c = str;
        }

        @Override // androidx.browser.customtabs.CustomTabsServiceConnection
        public void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
            l.e(componentName, "name");
            l.e(customTabsClient, "client");
            customTabsClient.warmup(0);
            this.f6577b.launchUrl(this.a, Uri.parse(this.f6578c));
            this.a.finish();
        }

        public void onServiceDisconnected(ComponentName componentName) {
            l.e(componentName, "name");
        }
    }

    /* access modifiers changed from: package-private */
    public static final class e implements View.OnClickListener {
        final /* synthetic */ BrowserActivity a;

        e(BrowserActivity browserActivity) {
            this.a = browserActivity;
        }

        public final void onClick(View view) {
            this.a.finish();
        }
    }

    /* access modifiers changed from: package-private */
    public static final class f implements View.OnClickListener {
        final /* synthetic */ BrowserActivity a;

        f(BrowserActivity browserActivity) {
            this.a = browserActivity;
        }

        public final void onClick(View view) {
            BrowserActivity browserActivity = this.a;
            int i2 = com.nordvpn.android.c.webview;
            WebView webView = (WebView) this.a.c(i2);
            l.d(webView, "webview");
            ((WebView) browserActivity.c(i2)).loadUrl(webView.getUrl());
        }
    }

    /* access modifiers changed from: package-private */
    public static final class g implements SwipeRefreshLayout.OnRefreshListener {
        final /* synthetic */ BrowserActivity a;

        g(BrowserActivity browserActivity) {
            this.a = browserActivity;
        }

        @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
        public final void onRefresh() {
            BrowserActivity browserActivity = this.a;
            int i2 = com.nordvpn.android.c.webview;
            WebView webView = (WebView) this.a.c(i2);
            l.d(webView, "webview");
            ((WebView) browserActivity.c(i2)).loadUrl(webView.getUrl());
            SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) this.a.c(com.nordvpn.android.c.swipe_refresh);
            l.d(swipeRefreshLayout, "swipe_refresh");
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    /* access modifiers changed from: package-private */
    public static final class h implements View.OnClickListener {
        final /* synthetic */ BrowserActivity a;

        h(BrowserActivity browserActivity) {
            this.a = browserActivity;
        }

        public final void onClick(View view) {
            this.a.finish();
        }
    }

    /* access modifiers changed from: package-private */
    public static final class i implements View.OnClickListener {
        final /* synthetic */ BrowserActivity a;

        i(BrowserActivity browserActivity) {
            this.a = browserActivity;
        }

        public final void onClick(View view) {
            ((WebViewGenericErrorView) this.a.c(com.nordvpn.android.c.generic_error)).setIsProgressVisibile(true);
            BrowserActivity browserActivity = this.a;
            int i2 = com.nordvpn.android.c.webview;
            WebView webView = (WebView) this.a.c(i2);
            l.d(webView, "webview");
            ((WebView) browserActivity.c(i2)).loadUrl(webView.getUrl());
        }
    }

    static final class j<T> implements Observer<j.b> {
        final /* synthetic */ BrowserActivity a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ Bundle f6579b;

        j(BrowserActivity browserActivity, Bundle bundle) {
            this.a = browserActivity;
            this.f6579b = bundle;
        }

        /* renamed from: a */
        public final void onChanged(j.b bVar) {
            String b2;
            String a2;
            j.a a3;
            e0<j.a> e2 = bVar.e();
            if (!(e2 == null || (a3 = e2.a()) == null)) {
                this.a.A(a3.a(), a3.b());
            }
            e0<String> d2 = bVar.d();
            if (!(d2 == null || (a2 = d2.a()) == null)) {
                this.a.z(a2);
            }
            e0<String> f2 = bVar.f();
            if (!(f2 == null || (b2 = f2.b()) == null)) {
                try {
                    this.a.B(b2, this.f6579b, bVar.g());
                } catch (Exception unused) {
                    this.a.x().p();
                }
            }
            o2 c2 = bVar.c();
            if (!(c2 == null || ((z) c2.a()) == null)) {
                Toast.makeText(this.a, (int) R.string.no_browser_application_toast_message, 1).show();
                this.a.finish();
            }
            boolean h2 = bVar.h();
            if (h2) {
                TextView textView = (TextView) this.a.c(com.nordvpn.android.c.web_view_title);
                if (textView != null) {
                    textView.setVisibility(h2 ? 0 : 8);
                    textView.setText(this.a.getString(R.string.word_nordaccount));
                }
                TextView textView2 = (TextView) this.a.c(com.nordvpn.android.c.web_address);
                if (textView2 != null) {
                    textView2.setVisibility(4);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private final void A(CustomTabsIntent customTabsIntent, String str) {
        this.f6573i = new d(this, customTabsIntent, str);
        Intent intent = customTabsIntent.intent;
        l.d(intent, "customTabsIntent.intent");
        String str2 = intent.getPackage();
        CustomTabsServiceConnection customTabsServiceConnection = this.f6573i;
        l.c(customTabsServiceConnection);
        if (!CustomTabsClient.bindCustomTabsService(this, str2, customTabsServiceConnection)) {
            x().o();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private final void B(String str, Bundle bundle, boolean z) {
        setContentView(R.layout.activity_webview);
        if (z) {
            TransparentToolbar transparentToolbar = (TransparentToolbar) c(com.nordvpn.android.c.J4);
            l.d(transparentToolbar, "toolbar");
            transparentToolbar.setNavigationIcon((Drawable) null);
            int i2 = com.nordvpn.android.c.tv_refresh_button;
            ImageView imageView = (ImageView) c(i2);
            l.d(imageView, "tv_refresh_button");
            imageView.setVisibility(0);
            ImageView imageView2 = (ImageView) c(com.nordvpn.android.c.tv_back_button);
            imageView2.setVisibility(0);
            imageView2.setOnClickListener(new e(this));
            ((ImageView) c(i2)).setOnClickListener(new f(this));
            ((WebView) c(com.nordvpn.android.c.webview)).setInitialScale(v());
            Button button = (Button) c(com.nordvpn.android.c.webview_error_button);
            l.d(button, "webview_error_button");
            button.setFocusableInTouchMode(true);
        }
        ((SwipeRefreshLayout) c(com.nordvpn.android.c.swipe_refresh)).setOnRefreshListener(new g(this));
        ((TransparentToolbar) c(com.nordvpn.android.c.J4)).setNavigationOnClickListener(new h(this));
        ((Button) c(com.nordvpn.android.c.webview_error_button)).setOnClickListener(new i(this));
        int i3 = com.nordvpn.android.c.webview;
        WebView webView = (WebView) c(i3);
        webView.setWebViewClient(this.f6575k);
        webView.setWebChromeClient(this.f6574j);
        WebSettings settings = webView.getSettings();
        l.d(settings, "settings");
        settings.setJavaScriptEnabled(true);
        if (bundle == null) {
            ((WebView) c(i3)).loadUrl(str);
        }
    }

    private final int v() {
        Point point = new Point();
        Object systemService = getSystemService("window");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        ((WindowManager) systemService).getDefaultDisplay().getSize(point);
        return (int) ((((double) point.x) / 1500.0d) * ((double) 100));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private final j x() {
        r0 r0Var = this.f6568d;
        if (r0Var == null) {
            l.t("viewModelFactory");
        }
        ViewModel viewModel = new ViewModelProvider(this, r0Var).get(j.class);
        l.d(viewModel, "ViewModelProvider(this, …ctory).get(T::class.java)");
        return (j) viewModel;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private final boolean y(String str) {
        if ((p.G(str, "nordvpn://", false, 2, null)) || (p.G(str, "market://", false, 2, null)) || (p.G(str, "amzn://", false, 2, null))) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private final void z(String str) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        intent.addFlags(1677721600);
        startActivity(intent);
        finish();
    }

    public View c(int i2) {
        if (this.f6576l == null) {
            this.f6576l = new HashMap();
        }
        View view = (View) this.f6576l.get(Integer.valueOf(i2));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i2);
        this.f6576l.put(Integer.valueOf(i2), findViewById);
        return findViewById;
    }

    @Override // androidx.activity.ComponentActivity
    public void onBackPressed() {
        int i2 = com.nordvpn.android.c.webview;
        WebView webView = (WebView) c(i2);
        if (webView == null || !webView.canGoBack()) {
            super.onBackPressed();
        } else {
            ((WebView) c(i2)).goBack();
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, f.b.k.b
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.simple_activity);
        x().m().observe(this, new j(this, bundle));
    }

    /* access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onDestroy() {
        WebView webView = (WebView) c(com.nordvpn.android.c.webview);
        if (webView != null) {
            webView.clearCache(true);
        }
        CustomTabsServiceConnection customTabsServiceConnection = this.f6573i;
        if (customTabsServiceConnection != null) {
            unbindService(customTabsServiceConnection);
        }
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Bundle bundle) {
        l.e(bundle, "savedInstanceState");
        super.onRestoreInstanceState(bundle);
        WebView webView = (WebView) c(com.nordvpn.android.c.webview);
        if (webView != null) {
            webView.restoreState(bundle);
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity
    public void onSaveInstanceState(Bundle bundle) {
        l.e(bundle, "outState");
        super.onSaveInstanceState(bundle);
        WebView webView = (WebView) c(com.nordvpn.android.c.webview);
        if (webView != null) {
            webView.saveState(bundle);
        }
    }

    public final h s() {
        return (h) this.f6572h.getValue(this, f6566b[1]);
    }

    public final com.nordvpn.android.a0.a t() {
        com.nordvpn.android.a0.a aVar = this.f6570f;
        if (aVar == null) {
            l.t("logger");
        }
        return aVar;
    }

    public final e1 u() {
        e1 e1Var = this.f6569e;
        if (e1Var == null) {
            l.t("networkChangeHandler");
        }
        return e1Var;
    }

    public final String w() {
        return (String) this.f6571g.getValue(this, f6566b[0]);
    }

    public static final class c extends WebViewClient {
        final /* synthetic */ BrowserActivity a;

        c(BrowserActivity browserActivity) {
            this.a = browserActivity;
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            ((WebViewGenericErrorView) this.a.c(com.nordvpn.android.c.generic_error)).setIsProgressVisibile(false);
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            if (i.a(this.a.s()) && str != null) {
                TextView textView = (TextView) this.a.c(com.nordvpn.android.c.web_address);
                l.d(textView, "web_address");
                Uri parse = Uri.parse(str);
                l.d(parse, "Uri.parse(it)");
                textView.setText(String.valueOf(parse.getHost()));
            }
            WebViewGenericErrorView webViewGenericErrorView = (WebViewGenericErrorView) this.a.c(com.nordvpn.android.c.generic_error);
            l.d(webViewGenericErrorView, "generic_error");
            webViewGenericErrorView.setVisibility(8);
        }

        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            l.e(webView, "view");
            l.e(webResourceRequest, "request");
            l.e(webResourceError, AnalyticsDataFactory.FIELD_ERROR_DATA);
            if (webResourceRequest.isForMainFrame()) {
                if (n1.c(this.a.u().d())) {
                    ((WebViewGenericErrorView) this.a.c(com.nordvpn.android.c.generic_error)).setErrorText(n.NO_NETWORK);
                } else {
                    com.nordvpn.android.a0.a t = this.a.t();
                    t.h("Webview received " + webResourceError.getErrorCode() + " error " + "that was caused by: " + webResourceError.getDescription());
                    ((WebView) this.a.c(com.nordvpn.android.c.webview)).clearCache(true);
                    ((WebViewGenericErrorView) this.a.c(com.nordvpn.android.c.generic_error)).setErrorText(n.UNKNOWN);
                }
                WebViewGenericErrorView webViewGenericErrorView = (WebViewGenericErrorView) this.a.c(com.nordvpn.android.c.generic_error);
                l.d(webViewGenericErrorView, "generic_error");
                webViewGenericErrorView.setVisibility(0);
                ((Button) this.a.c(com.nordvpn.android.c.webview_error_button)).requestFocus();
            }
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            Uri uri = null;
            if (this.a.y(String.valueOf(webResourceRequest != null ? webResourceRequest.getUrl() : null))) {
                BrowserActivity browserActivity = this.a;
                if (webResourceRequest != null) {
                    uri = webResourceRequest.getUrl();
                }
                browserActivity.startActivity(new Intent("android.intent.action.VIEW", uri));
                return true;
            } else if (webResourceRequest == null || !webResourceRequest.hasGesture()) {
                return false;
            } else {
                return super.shouldOverrideUrlLoading(webView, webResourceRequest);
            }
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (str == null || !this.a.y(str)) {
                return false;
            }
            this.a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            return true;
        }
    }
}
