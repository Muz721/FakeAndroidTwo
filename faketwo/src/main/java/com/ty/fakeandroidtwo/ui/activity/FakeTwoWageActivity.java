package com.ty.fakeandroidtwo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ty.fakeandroidtwo.R;
import com.ty.fakeandroidtwo.base.FakeTwoBaseActivity;
import com.ty.fakeandroidtwo.bean.FakeTwoDetailsBean;
import com.ty.fakeandroidtwo.component.recyclerview.decoration.FakeTwoDividerItemDecoration;
import com.ty.fakeandroidtwo.ui.adapter.FakeTwoWageAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 个人所得税
 */
public class FakeTwoWageActivity extends FakeTwoBaseActivity {
    @BindView(R.id.fake_two_title_img_finish)
    ImageView fakeTwoTitleImgFinish;
    @BindView(R.id.fake_two_title_txt_other)
    TextView fakeTwoTitleTxtOther;
    @BindView(R.id.fake_two_title_txt_title)
    TextView fakeTwoTitleTxtTitle;
    @BindView(R.id.fake_two_wage_txt_personal_income_tax)
    TextView fakeTwoWageTxtPersonalIncomeTax;
    @BindView(R.id.fake_two_wage_recycler_details)
    RecyclerView fakeTwoWageRecyclerDetails;

    private double beforeTax, insurance, markingPoint, taxRate, quickDeduction;
    private double personalIncomeTaxs;
    private double afterTaxs;

    private ArrayList<FakeTwoDetailsBean> fakeTwoDetailsBeans = new ArrayList<>();
    @Override
    protected int getLayout() {
        return R.layout.fake_two_activity_wage;
    }

    @Override
    protected void initViewAndEvent() {
        fakeTwoTitleImgFinish.setImageResource(R.drawable.fake_two_finish_black);
        fakeTwoTitleTxtTitle.setText("计算结果");
        fakeTwoTitleTxtOther.setText("个人所得税");
        Intent intent = getIntent();
        beforeTax = intent.getDoubleExtra("beforeTax", 0.00);
        insurance = intent.getDoubleExtra("insurance", 0.00);
        markingPoint = intent.getDoubleExtra("markingPoint", 0.00);
        taxRate = intent.getDoubleExtra("taxRate", 0.00);
        quickDeduction = intent.getDoubleExtra("quickDeduction", 0.00);
        personalIncomeTaxs = (beforeTax - insurance - markingPoint) * taxRate * 0.01 - quickDeduction;
        afterTaxs = beforeTax - personalIncomeTaxs - insurance;
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        fakeTwoWageTxtPersonalIncomeTax.setText("个人所得税" + decimalFormat.format(personalIncomeTaxs) + "元");

        fakeTwoDetailsBeans.add(new FakeTwoDetailsBean(0,"税前工资:",decimalFormat.format(beforeTax)+"元"));
        fakeTwoDetailsBeans.add(new FakeTwoDetailsBean(1,"税后工资:",decimalFormat.format(afterTaxs)+"元"));
        fakeTwoWageRecyclerDetails.setLayoutManager(new LinearLayoutManager(this));
        fakeTwoWageRecyclerDetails.setAdapter(new FakeTwoWageAdapter(this,R.layout.fake_two_recycler,fakeTwoDetailsBeans));
        fakeTwoWageRecyclerDetails.addItemDecoration(new FakeTwoDividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }


    @OnClick({R.id.fake_two_title_img_finish, R.id.fake_two_title_txt_other})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fake_two_title_img_finish:
            case R.id.fake_two_title_txt_other:
                finish();
                break;
        }
    }

}
