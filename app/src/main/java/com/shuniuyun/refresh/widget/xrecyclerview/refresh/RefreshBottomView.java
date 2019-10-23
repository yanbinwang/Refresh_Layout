package com.shuniuyun.refresh.widget.xrecyclerview.refresh;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.IBottomView;
import com.shuniuyun.refresh.R;

/**
 * author: wyb
 * date: 2018/8/8.
 * 自定义刷新控件头部
 */
public class RefreshBottomView implements IBottomView {
    private Context context;
    private ProgressBar refreshProgress;
    private TextView refreshContentTv;
    private static final String REFRESH_PULL_UP = "加载更多";
    private static final String REFRESH_LOADING = "正在加载";

    public RefreshBottomView(Context context) {
        this.context = context;
    }

    @Override
    public View getView() {
        View rootView = View.inflate(context, R.layout.view_refresh_bottom, null);
        refreshProgress = rootView.findViewById(R.id.refresh_progress);
        refreshContentTv = rootView.findViewById(R.id.refresh_content_tv);
        return rootView;
    }

    @Override
    public void onPullingUp(float fraction, float maxBottomHeight, float bottomHeight) {
        if (fraction < 1f) refreshContentTv.setText(REFRESH_PULL_UP);//控件拉开至未完全拉开
        if (fraction > 1f) refreshContentTv.setText(REFRESH_LOADING);//控件拉开有一段距离至完全拉开
    }

    @Override
    public void onPullReleasing(float fraction, float maxBottomHeight, float bottomHeight) {
        if (fraction < 1f) {
            refreshProgress.setVisibility(View.GONE);
            refreshContentTv.setText(REFRESH_PULL_UP);
        }
    }

    @Override
    public void startAnim(float maxBottomHeight, float bottomHeight) {
        refreshProgress.setVisibility(View.VISIBLE);
        refreshContentTv.setText(REFRESH_LOADING);
    }

    @Override
    public void onFinish() {
    }

    @Override
    public void reset() {
        refreshProgress.setVisibility(View.GONE);
    }

}
