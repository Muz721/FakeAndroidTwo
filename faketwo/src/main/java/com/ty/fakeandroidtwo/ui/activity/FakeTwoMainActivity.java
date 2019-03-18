package com.ty.fakeandroidtwo.ui.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;


import com.ty.fakeandroidtwo.R;
import com.ty.fakeandroidtwo.base.FakeTwoBaseActivity;
import com.ty.fakeandroidtwo.ui.fragment.FakeTwoMineFragment;
import com.ty.fakeandroidtwo.ui.fragment.FakeTwoTaxesFragment;
import com.ty.fakeandroidtwo.ui.fragment.FakeTwoWageFragment;
import com.ty.fakeandroidtwo.ui.fragment.FakeTwoHouseFragment;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * 首页
 */
public class FakeTwoMainActivity extends FakeTwoBaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    FakeTwoHouseFragment fakeTwoHouseFragment;
    FakeTwoTaxesFragment fakeTwoCalculatorFragment;
    FakeTwoWageFragment fakeTwoCarFragment;
    FakeTwoMineFragment fakeTwoMineFragment;
    @BindView(R.id.fake_two_main_navigation)
    BottomNavigationView fakeTwoMainNavigation;

    int hideFragment;//当前fragment坐标   0: 商业贷款； 1:税费计算； 2:个人所得税； 3: 我的

    @Override
    protected int getLayout() {
        return R.layout.fake_two_activity_main;
    }

    @Override
    protected void initViewAndEvent() {
        fakeTwoHouseFragment = new FakeTwoHouseFragment();
        fakeTwoCalculatorFragment = new FakeTwoTaxesFragment();
        fakeTwoCarFragment = new FakeTwoWageFragment();
        fakeTwoMineFragment = new FakeTwoMineFragment();
        loadMultipleRootFragment(R.id.fake_two_main_fragment, 0, fakeTwoHouseFragment, fakeTwoCalculatorFragment, fakeTwoCarFragment,fakeTwoMineFragment);
        fakeTwoMainNavigation.setOnNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.fake_two_menu_house:
                showHideFragment(fakeTwoHouseFragment, getHideFragment(hideFragment));
                hideFragment = 0;
                break;
            case R.id.fake_two_menu_calculator:
                showHideFragment(fakeTwoCalculatorFragment, getHideFragment(hideFragment));
                hideFragment = 1;
                break;
            case R.id.fake_two_menu_car:
                showHideFragment(fakeTwoCarFragment, getHideFragment(hideFragment));
                hideFragment = 2;
                break;
            case R.id.fake_two_menu_mine:
                showHideFragment(fakeTwoMineFragment, getHideFragment(hideFragment));
                hideFragment = 3;
                break;
        }
        return true;
    }

    /**
     * 调用对应的fragment
     * @param hideFragment   当前fragment 坐标
     * @return   返回当前的fragment
     */
    private SupportFragment getHideFragment(int hideFragment) {
        switch (hideFragment) {
            case 0:
                return fakeTwoHouseFragment;
            case 1:
                return fakeTwoCalculatorFragment;
            case 2:
                return fakeTwoCarFragment;
            case 3:
                return fakeTwoMineFragment;
        }
        return null;
    }
}
