package com.xujun.administrator.recycleviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.xujun.administrator.recycleviewdemo.adapter.MyRecycleAdapter;
import com.xujun.administrator.recycleviewdemo.utils.UIUtils;
import com.xujun.administrator.recycleviewdemo.view.PulltoRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecycleViewActivity extends AppCompatActivity {

    @Bind(R.id.ll_recycle_view)
    PulltoRefreshRecyclerView mRefreshRecyclerView;
    private MyRecycleAdapter mAdapter;
    private List<String> mList;
    String mes="这是数据";
    private int start;
    private int end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        ButterKnife.bind(this);
        mList = new ArrayList<>();
        end = 20;
        for(start = 0; start < end; start++){
            mList.add(mes+ start);
        }
        mAdapter = new MyRecycleAdapter(this, R.layout.item_recycleview,mList);
        mRefreshRecyclerView.setAdapter(mAdapter);
        mRefreshRecyclerView.setOnRefreshListener(new PulltoRefreshRecyclerView.onRefreshListener() {

            @Override
            public void onRefresh(RecyclerView recyclerView) {
                UIUtils.showShortText(recyclerView.getContext(),"onRefresh");
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRefreshRecyclerView.OnRefreshCompleted();


                    }
                },1000);
            }

            @Override
            public void onLoadMore(RecyclerView recyclerView) {
               final ArrayList<String> list = new ArrayList<>();
                for(end=end+10; start <end+10; start++){
                    list.add(mes+ start);
               }
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRefreshRecyclerView.OnRefreshCompleted();
                        mAdapter.addDates(list);

                    }
                },1000);
            }
        });
    }
}
