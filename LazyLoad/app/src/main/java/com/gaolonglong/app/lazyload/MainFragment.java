package com.gaolonglong.app.lazyload;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by gaohailong on 2017/2/13.
 */

public class MainFragment extends Fragment {

    private TextView tvText;
    private LinearLayout progressBar;
    private String title;
    private boolean isVisible;
    private boolean isPrepare;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        title = bundle.getString("title");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tvText = (TextView) view.findViewById(R.id.tv_text);
        progressBar = (LinearLayout) view.findViewById(R.id.progress_bar);
    }

    public static MainFragment getInstance(String title){
        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title",title);
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

    /**
     * onViewCreated在onCreateView之后执行，表示布局已经渲染完成
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isPrepare = true;
        onLazyLoad();
    }

    /**
     * 参考：http://t.cn/RJopwyZ
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()){
            isVisible = true;
            onVisible();
        }else {
            isVisible = false;
            onInVisible();
        }
    }

    private void onInVisible() {

    }

    private void onVisible() {
        onLazyLoad();
    }

    private void onLazyLoad() {
        if (!isVisible || !isPrepare){
            return;
        }
        Log.e("tag",title);
        tvText.setText(title);
        progressBar.setVisibility(View.GONE);
    }
}
