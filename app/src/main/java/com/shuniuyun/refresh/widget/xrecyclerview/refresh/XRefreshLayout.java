package com.shuniuyun.refresh.widget.xrecyclerview.refresh;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.shuniuyun.refresh.R;
import com.shuniuyun.refresh.utils.DisplayUtil;

/**
 * author:wyb
 * 对第三方自定义刷新控件再次封装
 */
public class XRefreshLayout extends TwinklingRefreshLayout {
    private Context context;

    public XRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public XRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.XRefreshLayout);
        int refreshDirection = mTypedArray.getInt(R.styleable.XRefreshLayout_direction, 2);
        mTypedArray.recycle();
        //定义刷新控件的一些属性
        setHeaderHeight(DisplayUtil.dip2px(context, 15));
        setMaxHeadHeight(DisplayUtil.dip2px(context, 20));
        setBottomHeight(DisplayUtil.dip2px(context, 15));
        setMaxBottomHeight(DisplayUtil.dip2px(context, 20));
        setDirection(refreshDirection);
    }

    //设置刷新样式
    public void setDirection(int refreshDirection) {
        switch (refreshDirection) {
            //顶部
            case 0:
                setHeaderView(new RefreshHeaderView(context));
                setEnableLoadmore(false);
                break;
            //底部
            case 1:
                setBottomView(new RefreshBottomView(context));
                setEnableRefresh(false);
                break;
            //都有（默认）
            case 2:
                setHeaderView(new RefreshHeaderView(context));
                setBottomView(new RefreshBottomView(context));
                break;
        }
    }

}
