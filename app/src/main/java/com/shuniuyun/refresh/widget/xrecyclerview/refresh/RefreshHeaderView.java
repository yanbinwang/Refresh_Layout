package com.shuniuyun.refresh.widget.xrecyclerview.refresh;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.IHeaderView;
import com.lcodecore.tkrefreshlayout.OnAnimEndListener;
import com.shuniuyun.refresh.R;

/**
 * author: wyb
 * date: 2018/8/8.
 * 自定义刷新控件头部
 */
public class RefreshHeaderView implements IHeaderView {
    private Context context;
    private ImageView refreshArrowIv;//刷新的view
    private ProgressBar refreshProgress;//刷新进度条
    private TextView refreshContentTv;//刷新文字
    private static final String REFRESH_PULL_DOWN = "下拉刷新";
    private static final String REFRESH_RELEASE = "释放更新";
    private static final String REFRESH_LOADING = "加载中...";

    public RefreshHeaderView(Context context) {
        this.context = context;
    }

    //获取刷新的整体view
    @Override
    public View getView() {
        View rootView = View.inflate(context, R.layout.view_refresh_header, null);
        refreshArrowIv = rootView.findViewById(R.id.refresh_arrow_iv);
        refreshProgress = rootView.findViewById(R.id.refresh_progress);
        refreshContentTv = rootView.findViewById(R.id.refresh_content_tv);
        return rootView;
    }

    //当控件下拉时触发
    @Override
    public void onPullingDown(float fraction, float maxHeadHeight, float headHeight) {
        Log.e("wyb","RefreshHeaderView:" + fraction);
        if (fraction < 1f) refreshContentTv.setText(REFRESH_PULL_DOWN);//控件拉开至未完全拉开
        if (fraction > 1f) refreshContentTv.setText(REFRESH_RELEASE);//控件拉开有一段距离至完全拉开
        refreshArrowIv.setRotation(fraction * headHeight / maxHeadHeight * 180);//图片转一圈
    }

    //刷新被复原时--复位
    @Override
    public void onPullReleasing(float fraction, float maxHeadHeight, float headHeight) {
        if (fraction < 1f) {//加上距离判断加快重置的速度，否则会闪屏
            refreshContentTv.setText(REFRESH_PULL_DOWN);
            refreshArrowIv.setRotation(fraction * headHeight / maxHeadHeight * 180);
            if (refreshArrowIv.getVisibility() == View.GONE) {
                refreshArrowIv.setVisibility(View.VISIBLE);
                refreshProgress.setVisibility(View.GONE);
            }
        }
    }

    //触发执行动画时，文字和图片的样式
    @Override
    public void startAnim(float maxHeadHeight, float headHeight) {
        refreshContentTv.setText(REFRESH_LOADING);
        refreshArrowIv.setVisibility(View.GONE);
        refreshProgress.setVisibility(View.VISIBLE);
    }

    //动画执行完毕时，结束
    @Override
    public void onFinish(OnAnimEndListener animEndListener) {
        animEndListener.onAnimEnd();
    }

    //重置复位
    @Override
    public void reset() {
        refreshArrowIv.setVisibility(View.VISIBLE);
        refreshProgress.setVisibility(View.GONE);
    }

}
