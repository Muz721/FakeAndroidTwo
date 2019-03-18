package com.ty.fakeandroidtwo.ui.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ty.fakeandroidtwo.R;
import com.ty.fakeandroidtwo.base.FakeTwoBaseFragment;
import com.ty.fakeandroidtwo.bean.FakeTwoMineBean;
import com.ty.fakeandroidtwo.component.recyclerview.FakeTwoMultiItemTypeAdapter;
import com.ty.fakeandroidtwo.component.recyclerview.decoration.FakeTwoDividerItemDecoration;
import com.ty.fakeandroidtwo.component.rx.FakeTwoRxView;
import com.ty.fakeandroidtwo.component.rx.FakeTwoRxViewOnclick;
import com.ty.fakeandroidtwo.ui.activity.FakeTwoFeebackActvity;
import com.ty.fakeandroidtwo.ui.activity.FakeTwoLoginActivity;
import com.ty.fakeandroidtwo.ui.activity.FakeTwoSettingActivity;
import com.ty.fakeandroidtwo.ui.activity.FakeTwoWebViewActivity;
import com.ty.fakeandroidtwo.ui.adapter.FakeTwoMineAdapter;
import com.ty.fakeandroidtwo.view.FakeTwoCircleImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FakeTwoMineFragment extends FakeTwoBaseFragment implements FakeTwoMultiItemTypeAdapter.OnItemClickListener {
    @BindView(R.id.fake_two_title_txt_title)
    TextView fakeTwoTitleTxtTitle;
    @BindView(R.id.fake_two_circle_portrait)
    FakeTwoCircleImageView fakeTwoCirclePortrait;
    @BindView(R.id.fake_two_user)
    TextView fakeTwoUser;
    @BindView(R.id.fake_two_mine_recycler)
    RecyclerView fakeTwoMineRecycler;
    @BindView(R.id.fake_two_user_unlogin)
    TextView fakeTwoUserUnlogin;
    private boolean isLogin = false;
    private ArrayList<FakeTwoMineBean> fakeTwoMineBeans = new ArrayList<>();
    private FakeTwoMineAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.fake_two_fragment_mine;
    }

    @Override
    protected void initViewAndEvent() {
        fakeTwoTitleTxtTitle.setText("我的");
        fakeTwoMineBeans.add(new FakeTwoMineBean(0, "帮助中心", R.mipmap.fake_two_icon_user_helper));
        fakeTwoMineBeans.add(new FakeTwoMineBean(1, "意见反馈", R.mipmap.fake_two_icon_user_feedback));
        fakeTwoMineBeans.add(new FakeTwoMineBean(2, "关于我们", R.mipmap.fake_two_icon_user_about));
        fakeTwoMineBeans.add(new FakeTwoMineBean(3, "设置", R.mipmap.fake_two_icon_user_set));
        adapter = new FakeTwoMineAdapter(getContext(), R.layout.fake_two_adpter_mine_recycler, fakeTwoMineBeans);
        fakeTwoMineRecycler.setAdapter(adapter);
        fakeTwoMineRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        fakeTwoMineRecycler.addItemDecoration(new FakeTwoDividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        adapter.setOnItemClickListener(this);
        FakeTwoRxView.clicks(fakeTwoCirclePortrait)
                .subscribe(new FakeTwoRxViewOnclick<View>() {
                    @Override
                    public void onNext(View view) {
                        if (!isLogin) {
                            Intent intent = new Intent(getContext(), FakeTwoLoginActivity.class);
                            startActivity(intent);
                        }
                    }
                });
        FakeTwoRxView.clicks(fakeTwoUser)
                .subscribe(new FakeTwoRxViewOnclick<View>() {
                    @Override
                    public void onNext(View view) {
                        if (!isLogin) {
                            Intent intent = new Intent(getContext(), FakeTwoLoginActivity.class);
                            startActivity(intent);
                        }
                    }
                });
        FakeTwoRxView.clicks(fakeTwoUserUnlogin)
                .subscribe(new FakeTwoRxViewOnclick<View>() {
                    @Override
                    public void onNext(View view) {
                        fakeTwoUser.setVisibility(View.VISIBLE);
                        fakeTwoCirclePortrait.setImageResource(R.mipmap.fake_two_user_unlogin);
                        fakeTwoUserUnlogin.setVisibility(View.GONE);
                        SharedPreferences user = getContext().getSharedPreferences("user", 0);
                        SharedPreferences.Editor editor = user.edit();
                        editor.putBoolean("isLogin", false)
                                .apply();
                        isLogin = !isLogin;
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences user = getActivity().getSharedPreferences("user", 0);
        isLogin = user.getBoolean("isLogin", false);
        if (isLogin) {
            fakeTwoUser.setVisibility(View.INVISIBLE);
            fakeTwoCirclePortrait.setImageResource(R.mipmap.fake_two_user_login);
//            fakeTwoUserUnlogin.setVisibility(View.VISIBLE);
        } else {
            fakeTwoUser.setVisibility(View.VISIBLE);
            fakeTwoCirclePortrait.setImageResource(R.mipmap.fake_two_user_unlogin);
            fakeTwoUserUnlogin.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        Intent intent;
        switch (position) {
            case 0:
                intent = new Intent(getActivity(), FakeTwoWebViewActivity.class);
                intent.putExtra("title", "帮助中心");
                intent.putExtra("url", "file:///android_asset/fakeTwoHelp.html");
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(getActivity(), FakeTwoFeebackActvity.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(getActivity(), FakeTwoWebViewActivity.class);
                intent.putExtra("title", "关于我们");
                intent.putExtra("url", "file:///android_asset/fakeTwoAbout.html");
                startActivity(intent);
                break;
            case 3:
                if (isLogin) {
                    intent = new Intent(getActivity(), FakeTwoSettingActivity.class);
                } else {
                    intent = new Intent(getActivity(), FakeTwoLoginActivity.class);
                }
                startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }
}
