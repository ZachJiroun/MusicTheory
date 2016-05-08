package cmsc436.com.musictheory.lessons;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewFragment;
import android.widget.Toast;

/**
 * Created by zach on 5/7/16.
 */
public class LessonDetailsFragment extends WebViewFragment {
    private static final String ARGUMENT_URL = "url";
    private static final String ARGUMENT_LESSON = "lesson";
    private AppCompatActivity mActivity;

    public static LessonDetailsFragment newInstance(String lesson, String url) {
        final Bundle args = new Bundle();
        args.putString(ARGUMENT_URL, url);
        args.putString(ARGUMENT_LESSON, lesson);
        final LessonDetailsFragment fragment = new LessonDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public LessonDetailsFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            mActivity =  (AppCompatActivity) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        ActionBar actionBar = mActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            ((LessonsActivity) mActivity).setActionBarTitle(getArguments().getString(ARGUMENT_LESSON));
        }
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

    @Override
    public void onResume() {
        super.onResume();
        ((LessonsActivity) mActivity).setDrawerIndicatorEnabled(false);
    }
}
