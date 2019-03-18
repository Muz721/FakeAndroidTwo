package com.ty.fakeandroidtwo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ty.fakeandroidtwo.R;
import com.ty.fakeandroidtwo.base.FakeTwoBaseActivity;
import com.ty.fakeandroidtwo.bean.FakeTwoDetailsBean;
import com.ty.fakeandroidtwo.component.loan.FakeTwoACMLoanCalculator;
import com.ty.fakeandroidtwo.component.loan.FakeTwoACPIMLoanCalculator;
import com.ty.fakeandroidtwo.component.loan.FakeTwoLoan;
import com.ty.fakeandroidtwo.component.loan.FakeTwoLoanUtil;
import com.ty.fakeandroidtwo.component.recyclerview.decoration.FakeTwoDividerItemDecoration;
import com.ty.fakeandroidtwo.ui.adapter.FakeTwoWageAdapter;

import java.math.BigDecimal;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 商业贷款
 */
public class FakeTwoCalculateActivity extends FakeTwoBaseActivity {
    @BindView(R.id.fake_two_title_img_finish)
    ImageView fakeTwoTitleImgFinish;
    @BindView(R.id.fake_two_title_txt_title)
    TextView fakeTwoTitleTxtTitle;
    @BindView(R.id.fake_two_calculate_txt_totalLoans)
    TextView fakeTwoCalculateTxtTotalLoans;
    @BindView(R.id.fake_two_title_txt_other)
    TextView fakeTwoTitleTxtOther;
    @BindView(R.id.fake_two_calculate_recycler_details)
    RecyclerView fakeTwoCalculateRecyclerDetails;
    private int repaymentMethodPosition, mortgageYearsPosition;
    private double totalLoans,bankInterestRate;
    private int years,months;
    private FakeTwoLoan loan;
    private ArrayList<FakeTwoDetailsBean> fakeTwoDetailsBeans = new ArrayList<>();
    @Override
    protected int getLayout() {
        return R.layout.fake_two_activity_calculate;
    }

    @Override
    protected void initViewAndEvent() {
        fakeTwoTitleTxtTitle.setText(getResources().getString(R.string.fake_two_calculation_results));
        fakeTwoTitleImgFinish.setImageResource(R.drawable.fake_two_finish_black);
        Intent intent = getIntent();
        repaymentMethodPosition = intent.getIntExtra("repaymentMethodPosition", 0);
        mortgageYearsPosition = intent.getIntExtra("mortgageYearsPosition", 0);
        totalLoans = intent.getDoubleExtra("totalLoans", 0.00) * 10000;
        bankInterestRate = intent.getDoubleExtra("bankInterestRate", 0.00);
        fakeTwoTitleTxtOther.setText(getResources().getString(R.string.fake_two_house));
        years = mortgageYearsPosition + 1;
        months = years * 12;
        switch (repaymentMethodPosition) {//判断贷款方式  0: 等额本息；1: 等额本金
            case 0:
                loan = new FakeTwoACPIMLoanCalculator().calLoan(FakeTwoLoanUtil.totalLoanMoney(new BigDecimal(totalLoans), 0), months, FakeTwoLoanUtil.rate(bankInterestRate, 1), 10);

                break;
            case 1:
                loan = new FakeTwoACMLoanCalculator().calLoan(FakeTwoLoanUtil.totalLoanMoney(new BigDecimal(totalLoans), 0), months, FakeTwoLoanUtil.rate(bankInterestRate, 1), 10);

                break;
            default:
                break;
        }
        boolean isPrincipal = repaymentMethodPosition == 0;
        String repayment = isPrincipal ? "等额本息" : "等额本金";
        String fist = isPrincipal ? "月均还款:" : "首月还款:";
        fakeTwoDetailsBeans.add(new FakeTwoDetailsBean(0,"还款总额:",loan.getTotalRepayment()+"元"));
        fakeTwoDetailsBeans.add(new FakeTwoDetailsBean(1,"支付利息:",loan.getTotalInterest()+"元"));
        fakeTwoDetailsBeans.add(new FakeTwoDetailsBean(2,"按揭年数:",years+"年"));
        fakeTwoDetailsBeans.add(new FakeTwoDetailsBean(3,fist,loan.getAvgRepayment()+"元"));
        fakeTwoDetailsBeans.add(new FakeTwoDetailsBean(4,"还款方式:",repayment));
        fakeTwoDetailsBeans.add(new FakeTwoDetailsBean(5,"年利率:",bankInterestRate+"%"));
        fakeTwoCalculateRecyclerDetails.setLayoutManager(new LinearLayoutManager(this));
        fakeTwoCalculateRecyclerDetails.setAdapter(new FakeTwoWageAdapter(this,R.layout.fake_two_recycler,fakeTwoDetailsBeans));
        fakeTwoCalculateRecyclerDetails.addItemDecoration(new FakeTwoDividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        fakeTwoCalculateTxtTotalLoans.setText("贷款总额" + loan.getTotalLoanMoney().divide(new BigDecimal(10000), 0, BigDecimal.ROUND_HALF_UP) + "万元");

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
