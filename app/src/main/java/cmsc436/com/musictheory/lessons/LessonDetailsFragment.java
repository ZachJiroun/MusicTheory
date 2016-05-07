package cmsc436.com.musictheory.lessons;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewFragment;

/**
 * Created by zach on 5/7/16.
 */
public class LessonDetailsFragment extends WebViewFragment {
    private static final String ARGUMENT_URL = "url";

    public static LessonDetailsFragment newInstance(String url) {
        final Bundle args = new Bundle();
        args.putString(ARGUMENT_URL, url);
        final LessonDetailsFragment fragment = new LessonDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public LessonDetailsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = super.onCreateView(inflater, container, savedInstanceState);

        final Bundle args = getArguments();

        // Setup webview
        WebView webView = getWebView();
        webView.loadUrl(args.getString(ARGUMENT_URL));
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET);
        // Enable responsive layout
        webView.getSettings().setUseWideViewPort(true);
        // Zoom out if the content width is greater than the width of the veiwport
        webView.getSettings().setLoadWithOverviewMode(true);

        return view;
    }
}
