package com.shuniuyun.refresh.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shuniuyun.refresh.R;
import com.shuniuyun.refresh.bean.TestBean;

import java.util.List;


/**
 * author: wyb
 */
public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {
    private List<TestBean> mList;

    public TestAdapter(List<TestBean> mList) {
        this.mList = mList;
    }

    public int getItemCount() {
        return mList.size();
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        TestBean bean = mList.get(position);
        if (null != bean) {

        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test, parent, false));
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView test_title_tv;
        TextView test_des_tv;

        private ViewHolder(View itemView) {
            super(itemView);
            test_title_tv = itemView.findViewById(R.id.test_title_tv);
            test_des_tv = itemView.findViewById(R.id.test_des_tv);
        }
    }

}