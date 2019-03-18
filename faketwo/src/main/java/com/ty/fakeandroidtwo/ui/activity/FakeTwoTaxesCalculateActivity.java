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
import com.ty.fakeandroidtwo.component.recyclerview.decoration.FakeTwoDividerItemDecoration;
import com.ty.fakeandroidtwo.ui.adapter.FakeTwoWageAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 营改增税费和二手房交易税费
 */
public class FakeTwoTaxesCalculateActivity extends FakeTwoBaseActivity {


    @BindView(R.id.fake_two_title_img_finish)
    ImageView fakeTwoTitleImgFinish;
    @BindView(R.id.fake_two_title_txt_other)
    TextView fakeTwoTitleTxtOther;
    @BindView(R.id.fake_two_title_txt_title)
    TextView fakeTwoTitleTxtTitle;
    @BindView(R.id.fake_two_taxes_calculate_txt_totalLoans)
    TextView fakeTwoTaxesCalculateTxtTotalLoans;

    @BindView(R.id.fake_two_taxes_calculate_recycler_details)
    RecyclerView fakeTwoTaxesCalculateRecyclerDetails;
    private int taxesTypeOfTaxPosition, firstHomePurchasePosition, uniqueHousingPosition, propertyCertificatePeriodPosition;
    private long totalHousePrice, originalPriceOfTheHouse;
    private double deed_tax, vat, personalIncomeTax, stampDuty, all;
    private ArrayList<FakeTwoDetailsBean> fakeTwoDetailsBeans = new ArrayList<>();
    @Override
    protected int getLayout() {
        return R.layout.fake_two_activity_taxes_calculate;
    }

    @Override
    protected void initViewAndEvent() {
        fakeTwoTitleImgFinish.setImageResource(R.drawable.fake_two_finish_black);
        fakeTwoTitleTxtTitle.setText("计算结果");
        Intent intent = getIntent();
        totalHousePrice = intent.getLongExtra("totalHousePrice", 0) * 10000;
        originalPriceOfTheHouse = intent.getLongExtra("originalPriceOfTheHouse", 0) * 10000;
        taxesTypeOfTaxPosition = intent.getIntExtra("taxesTypeOfTaxPosition", 0);
        firstHomePurchasePosition = intent.getIntExtra("firstHomePurchasePosition", 0);
        uniqueHousingPosition = intent.getIntExtra("uniqueHousingPosition", 0);
        propertyCertificatePeriodPosition = intent.getIntExtra("propertyCertificatePeriodPosition", 0);
        personalIncomeTax = (totalHousePrice - originalPriceOfTheHouse) * 0.2;
        switch (firstHomePurchasePosition) {//判断是否是首次购房  0: 是，1: 否
            case 0:
                deed_tax = totalHousePrice * 0.015;
                break;
            case 1:
                deed_tax = totalHousePrice * 0.02;
        }
        switch (taxesTypeOfTaxPosition) {//判断税收种类    0: 营改增税费，1: 二手房交易税费
            case 0://营改增税费
                fakeTwoTitleTxtOther.setText("营改增税费");
                switch (propertyCertificatePeriodPosition) {//判断房产证购房年限 0: 2年以内，1: 2-5年(不含5年)，2: 5年及以上
                    case 0:
                        DecimalFormat df = new DecimalFormat("0.00");
                        vat = totalHousePrice / (1.05) * 0.05;
                        vat = Double.parseDouble(df.format(vat));
                        break;
                    case 1:
                        vat = 0.00;
                        break;
                    case 2:
                        vat = 0.00;
                        if (uniqueHousingPosition == 0) {
                            personalIncomeTax = 0;
                        }
                        break;
                }
                break;
            case 1://二手房交易税费
                fakeTwoTitleTxtOther.setText("二手房交易税费");
                switch (propertyCertificatePeriodPosition) {//判断房产证购房年限 0: 2年以内，1: 2-5年(不含5年)，2: 5年及以上
                    case 0:
                        vat = totalHousePrice * 0.056;
                        break;
                    case 1:
                        vat = 0.00;
                        break;
                    case 2:
                        vat = 0.00;
                        if (uniqueHousingPosition == 0) {
                            personalIncomeTax = 0;
                        }
                        break;
                }
                break;
        }
        stampDuty = 5.00;
        all = deed_tax + vat + personalIncomeTax + stampDuty;
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        fakeTwoDetailsBeans.add(new FakeTwoDetailsBean(0,"契税:",decimalFormat.format(deed_tax)+"元"));
        fakeTwoDetailsBeans.add(new FakeTwoDetailsBean(1,taxesTypeOfTaxPosition == 0 ? "增值税:" : "营业税:",decimalFormat.format(vat)+"元"));
        fakeTwoDetailsBeans.add(new FakeTwoDetailsBean(2,"个人所得税:",decimalFormat.format(personalIncomeTax)+"元"));
        fakeTwoDetailsBeans.add(new FakeTwoDetailsBean(3,"印花税:",decimalFormat.format(stampDuty)+"元"));
        fakeTwoTaxesCalculateRecyclerDetails.setLayoutManager(new LinearLayoutManager(this));
        fakeTwoTaxesCalculateRecyclerDetails.setAdapter(new FakeTwoWageAdapter(this,R.layout.fake_two_recycler,fakeTwoDetailsBeans));
        fakeTwoTaxesCalculateRecyclerDetails.addItemDecoration(new FakeTwoDividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        fakeTwoTaxesCalculateTxtTotalLoans.setText("合计"+decimalFormat.format(all) + "元");
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

