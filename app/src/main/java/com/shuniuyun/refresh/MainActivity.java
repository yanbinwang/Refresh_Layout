package com.shuniuyun.refresh;

import android.os.Bundle;
import android.os.Handler;
import android.util.SparseArray;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.shuniuyun.refresh.adapter.TestAdapter;
import com.shuniuyun.refresh.bean.TestBean;
import com.shuniuyun.refresh.utils.DisplayUtil;
import com.shuniuyun.refresh.widget.xrecyclerview.SCommonItemDecoration;
import com.shuniuyun.refresh.widget.xrecyclerview.refresh.RefreshBottomView;
import com.shuniuyun.refresh.widget.xrecyclerview.refresh.RefreshHeaderView;
import com.shuniuyun.refresh.widget.xrecyclerview.refresh.XRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private XRefreshLayout mRefresh;
    private List<TestBean> mList = new ArrayList<>();
    private TestAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addData();
        initView();
        initEvent();
    }

    private void initView() {
        mRefresh = findViewById(R.id.mRefresh);

        RecyclerView mRecyclerView = findViewById(R.id.mRecyclerView);
        //RecyclerView保持固定的大小
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        GridLayoutManager manager = new GridLayoutManager(this, 1);//设置一行设定是1个占位
        mRecyclerView.setLayoutManager(manager);
        //画间距
        SparseArray<SCommonItemDecoration.ItemDecorationProps> propMap = new SparseArray<>();
        SCommonItemDecoration.ItemDecorationProps prop1 = new SCommonItemDecoration.ItemDecorationProps(DisplayUtil.dip2px(this, 1), 0, true, false);
        propMap.put(0, prop1);
        mRecyclerView.addItemDecoration(new SCommonItemDecoration(propMap));
        mAdapter = new TestAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initEvent() {
        mRefresh.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                new Handler().postDelayed(() -> {
                    refreshLayout.finishRefreshing();
                    mList.clear();
                    addData();
                    mAdapter.notifyDataSetChanged();
                }, 2000);
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                new Handler().postDelayed(() -> {
                    //注意此处--（如果写成mListView.loadMoreEnd();则表示不在加载更多）
                    refreshLayout.finishLoadmore();
                    addData();
                    //进行局部刷新，这样原先的itemview就不会重绘
                    mAdapter.notifyItemRangeInserted(mAdapter.getItemCount(), mList.size() - 1);
                }, 2000);
            }
        });
    }

    private void addData() {
        for (int i = 0; i < 10; i++) {
            mList.add(new TestBean());
        }
    }

}
