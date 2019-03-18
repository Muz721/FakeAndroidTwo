package com.ty.fakeandroidtwo.ui.fragment;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.ty.fakeandroidtwo.R;
import com.ty.fakeandroidtwo.base.FakeTwoBaseFragment;
import com.ty.fakeandroidtwo.bean.FakeTwoBaseBean;
import com.ty.fakeandroidtwo.component.rx.FakeTwoRxView;
import com.ty.fakeandroidtwo.component.rx.FakeTwoRxViewOnclick;
import com.ty.fakeandroidtwo.ui.activity.FakeTwoTaxesCalculateActivity;
import com.ty.fakeandroidtwo.utils.FakeTwoAmountUtils;
import com.ty.fakeandroidtwo.utils.FakeTwoGeneralUtils;
import com.ty.fakeandroidtwo.utils.FakeTwoOptionsPickerViewUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 税费计算
 */
public class FakeTwoTaxesFragment extends FakeTwoBaseFragment {

    @BindView(R.id.fake_two_title_txt_title)
    TextView fakeTwoTitleTxtTitle;
    @BindView(R.id.fake_two_taxes_txt_Type_of_tax)
    TextView fakeTwoTaxesTxtTypeOfTax;
    @BindView(R.id.fake_two_taxes_linear_Type_of_tax)
    RelativeLayout fakeTwoTaxesLinearTypeOfTax;
    @BindView(R.id.fake_two_taxes_txt_first_home_purchase)
    TextView fakeTwoTaxesTxtFirstHomePurchase;
    @BindView(R.id.fake_two_taxes_linear_first_home_purchase)
    RelativeLayout fakeTwoTaxesLinearFirstHomePurchase;
    @BindView(R.id.fake_two_taxes_txt_unique_housing)
    TextView fakeTwoTaxesTxtUniqueHousing;
    @BindView(R.id.fake_two_taxes_linear_unique_housing)
    RelativeLayout fakeTwoTaxesLinearUniqueHousing;
    @BindView(R.id.fake_two_taxes_txt_property_certificate_period)
    TextView fakeTwoTaxesTxtPropertyCertificatePeriod;
    @BindView(R.id.fake_two_taxes_linear_property_certificate_period)
    RelativeLayout fakeTwoTaxesLinearPropertyCertificatePeriod;
    @BindView(R.id.fake_two_taxes_txt_total_house_price)
    EditText fakeTwoTaxesTxtTotalHousePrice;
    @BindView(R.id.fake_two_taxes_linear_total_house_price)
    RelativeLayout fakeTwoTaxesLinearTotalHousePrice;
    @BindView(R.id.fake_two_taxes_txt_original_price_of_the_house)
    EditText fakeTwoTaxesTxtOriginalPriceOfTheHouse;
    @BindView(R.id.fake_two_taxes_linear_original_price_of_the_house)
    RelativeLayout fakeTwoTaxesLinearOriginalPriceOfTheHouse;
    @BindView(R.id.fake_two_taxes_txt_calculate)
    TextView fakeTwoTaxesTxtCalculate;

    private ArrayList<FakeTwoBaseBean> fakeTwoTaxesTypeOfTaxBean = new ArrayList<>();
    private ArrayList<FakeTwoBaseBean> fakeTwoTaxesFirstHomePurchaseBean = new ArrayList<>();
    private ArrayList<FakeTwoBaseBean> fakeTwoTaxesUniqueHousingBean = new ArrayList<>();
    private ArrayList<FakeTwoBaseBean> fakeTwoTaxesPropertyCertificatePeriodBean = new ArrayList<>();
    private OptionsPickerView fakeTwoTypeOfTaxSelect, fakeTwoFirstHomePurchaseSelect,fakeTwoUniqueHousingSelect,fakeTwoPropertyCertificatePeriodSelect;
    private int taxesTypeOfTaxPosition, firstHomePurchasePosition,uniqueHousingPosition,propertyCertificatePeriodPosition;
    private long totalHousePrice,originalPriceOfTheHouse;
    @Override
    protected int getLayout() {
        return R.layout.fake_two_fragment_taxes;
    }

    @Override
    protected void initViewAndEvent() {
        fakeTwoTitleTxtTitle.setText("税费计算");
        fakeTwoTaxesTypeOfTaxBean.add(new FakeTwoBaseBean(0, "营改增税费"));
        fakeTwoTaxesTypeOfTaxBean.add(new FakeTwoBaseBean(1, "二手房交易税费"));
        fakeTwoTaxesFirstHomePurchaseBean.add(new FakeTwoBaseBean(0, "是"));
        fakeTwoTaxesFirstHomePurchaseBean.add(new FakeTwoBaseBean(1, "否"));
        fakeTwoTaxesUniqueHousingBean.add(new FakeTwoBaseBean(0, "是"));
        fakeTwoTaxesUniqueHousingBean.add(new FakeTwoBaseBean(1, "否"));
        fakeTwoTaxesPropertyCertificatePeriodBean.add(new FakeTwoBaseBean(0, "2年以内"));
        fakeTwoTaxesPropertyCertificatePeriodBean.add(new FakeTwoBaseBean(1, "2-5年（不含5年）"));
        fakeTwoTaxesPropertyCertificatePeriodBean.add(new FakeTwoBaseBean(2, "5年及以上"));
        fakeTwoTaxesTxtTypeOfTax.setText(fakeTwoTaxesTypeOfTaxBean.get(taxesTypeOfTaxPosition).getName());
        fakeTwoTaxesTxtFirstHomePurchase.setText(fakeTwoTaxesFirstHomePurchaseBean.get(firstHomePurchasePosition).getName());
        fakeTwoTaxesTxtUniqueHousing.setText(fakeTwoTaxesUniqueHousingBean.get(uniqueHousingPosition).getName());
        fakeTwoTaxesTxtPropertyCertificatePeriod.setText(fakeTwoTaxesPropertyCertificatePeriodBean.get(propertyCertificatePeriodPosition).getName());
        fakeTwoTypeOfTaxSelect = FakeTwoOptionsPickerViewUtils.open(getContext(),"税收种类", fakeTwoTypeOfTaxSelect, fakeTwoTaxesTypeOfTaxBean, pickerSelect);
        fakeTwoFirstHomePurchaseSelect = FakeTwoOptionsPickerViewUtils.open(getContext(),"首次购房", fakeTwoFirstHomePurchaseSelect, fakeTwoTaxesFirstHomePurchaseBean, pickerSelect);
        fakeTwoUniqueHousingSelect = FakeTwoOptionsPickerViewUtils.open(getContext(),"唯一住房", fakeTwoUniqueHousingSelect, fakeTwoTaxesUniqueHousingBean, pickerSelect);
        fakeTwoPropertyCertificatePeriodSelect = FakeTwoOptionsPickerViewUtils.open(getContext(), "房产证年限",fakeTwoPropertyCertificatePeriodSelect, fakeTwoTaxesPropertyCertificatePeriodBean, pickerSelect);
        fakeTwoTaxesTxtTotalHousePrice.setHint(totalHousePrice+"");
        fakeTwoTaxesTxtOriginalPriceOfTheHouse.setHint(originalPriceOfTheHouse+"");
        fakeTwoTaxesTxtTotalHousePrice.addTextChangedListener(txtTotalHousePrice);
        fakeTwoTaxesTxtOriginalPriceOfTheHouse.addTextChangedListener(txtOriginalPriceOfTheHouse);
        FakeTwoRxView.clicks(fakeTwoTaxesTxtCalculate)
                .subscribe(new FakeTwoRxViewOnclick<View>(){
                    @Override
                    public void onNext(View view) {
                        String txtTotalHousePrice = fakeTwoTaxesTxtTotalHousePrice.getText().toString().trim();
                        String txtOriginalPriceOfTheHouse = fakeTwoTaxesTxtOriginalPriceOfTheHouse.getText().toString().trim();
                        //判断末尾是否的字符是'.',如果是则删除，防止无法转成Long
                        totalHousePrice = Long.parseLong(TextUtils.isEmpty(txtTotalHousePrice)?0+"":txtTotalHousePrice);
                        originalPriceOfTheHouse = Long.parseLong(TextUtils.isEmpty(txtOriginalPriceOfTheHouse)?0+"":txtOriginalPriceOfTheHouse);
                        if (totalHousePrice!=0&&originalPriceOfTheHouse!=0){
                            Intent intent = new Intent(getActivity(), FakeTwoTaxesCalculateActivity.class);
                            intent.putExtra("totalHousePrice", totalHousePrice);
                            intent.putExtra("originalPriceOfTheHouse", originalPriceOfTheHouse);
                            intent.putExtra("taxesTypeOfTaxPosition", taxesTypeOfTaxPosition);
                            intent.putExtra("firstHomePurchasePosition", firstHomePurchasePosition);
                            intent.putExtra("uniqueHousingPosition", uniqueHousingPosition);
                            intent.putExtra("propertyCertificatePeriodPosition", propertyCertificatePeriodPosition);
                            startActivity(intent);
                        }else if (totalHousePrice == 0 && originalPriceOfTheHouse == 0) {
                            hint("请填写房屋总价和房屋原价");
                        } else if (totalHousePrice == 0.00) {
                            hint("请填写房屋总价");
                        } else {
                            hint("请填写房屋原价");
                        }
                    }
                });
    }


    @OnClick({R.id.fake_two_taxes_linear_Type_of_tax, R.id.fake_two_taxes_linear_first_home_purchase, R.id.fake_two_taxes_linear_unique_housing, R.id.fake_two_taxes_linear_property_certificate_period, R.id.fake_two_taxes_txt_total_house_price, R.id.fake_two_taxes_linear_total_house_price, R.id.fake_two_taxes_txt_original_price_of_the_house, R.id.fake_two_taxes_linear_original_price_of_the_house})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fake_two_taxes_linear_Type_of_tax:
                fakeTwoTypeOfTaxSelect.show(fakeTwoTaxesLinearTypeOfTax);
                break;
            case R.id.fake_two_taxes_linear_first_home_purchase:
                fakeTwoFirstHomePurchaseSelect.show(fakeTwoTaxesLinearFirstHomePurchase);
                break;
            case R.id.fake_two_taxes_linear_unique_housing:
                fakeTwoUniqueHousingSelect.show(fakeTwoTaxesLinearUniqueHousing);
                break;
            case R.id.fake_two_taxes_linear_property_certificate_period:
                fakeTwoPropertyCertificatePeriodSelect.show(fakeTwoTaxesLinearPropertyCertificatePeriod);
                break;
            case R.id.fake_two_taxes_txt_total_house_price:
            case R.id.fake_two_taxes_linear_total_house_price:
                FakeTwoGeneralUtils.getFocusAndOpenSoftKeyboard(fakeTwoTaxesTxtTotalHousePrice);
                fakeTwoTaxesTxtTotalHousePrice.setSelection(fakeTwoTaxesTxtTotalHousePrice.getText().length());
                break;
            case R.id.fake_two_taxes_txt_original_price_of_the_house:
            case R.id.fake_two_taxes_linear_original_price_of_the_house:
                FakeTwoGeneralUtils.getFocusAndOpenSoftKeyboard(fakeTwoTaxesTxtOriginalPriceOfTheHouse);
                fakeTwoTaxesTxtOriginalPriceOfTheHouse.setSelection(fakeTwoTaxesTxtOriginalPriceOfTheHouse.getText().length());
                break;

        }
        /**
         * 选择器点击事件
         */
    }    public OnOptionsSelectListener pickerSelect = new OnOptionsSelectListener() {
        @Override
        public void onOptionsSelect(int options1, int options2, int options3, View v) {
            switch (v.getId()) {
                case R.id.fake_two_taxes_linear_Type_of_tax:
                    fakeTwoTaxesTxtTypeOfTax.setText(fakeTwoTaxesTypeOfTaxBean.get(options1).getName());
                    taxesTypeOfTaxPosition = options1;
                    break;
                case R.id.fake_two_taxes_linear_first_home_purchase:
                    fakeTwoTaxesTxtFirstHomePurchase.setText(fakeTwoTaxesFirstHomePurchaseBean.get(options1).getName());
                    firstHomePurchasePosition = options1;
                    break;
                case R.id.fake_two_taxes_linear_unique_housing:
                    fakeTwoTaxesTxtUniqueHousing.setText(fakeTwoTaxesUniqueHousingBean.get(options1).getName());
                    uniqueHousingPosition = options1;
                    break;
                case R.id.fake_two_taxes_linear_property_certificate_period:
                    fakeTwoTaxesTxtPropertyCertificatePeriod.setText(fakeTwoTaxesPropertyCertificatePeriodBean.get(options1).getName());
                    propertyCertificatePeriodPosition = options1;
                    break;


            }
        }
    };
    /**
     * 房屋总价
     */
    private TextWatcher txtTotalHousePrice = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (TextUtils.isEmpty(s.toString())) {
                s = "0";
            }

            if (!s.toString().equals(FakeTwoAmountUtils.amount(s.toString()))) {
                fakeTwoTaxesTxtTotalHousePrice.setText(FakeTwoAmountUtils.amount(s.toString()));
            }
            fakeTwoTaxesTxtTotalHousePrice.setSelection(fakeTwoTaxesTxtTotalHousePrice.getText().length());
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };
    /**
     * 房屋原价
     */
    private TextWatcher txtOriginalPriceOfTheHouse = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (TextUtils.isEmpty(s.toString())) {
                s = "0";
            }
            if (!s.toString().equals(FakeTwoAmountUtils.amount(s.toString()))) {

                fakeTwoTaxesTxtOriginalPriceOfTheHouse.setText(FakeTwoAmountUtils.amount(s.toString()));
            }
            fakeTwoTaxesTxtOriginalPriceOfTheHouse.setSelection(fakeTwoTaxesTxtOriginalPriceOfTheHouse.getText().length());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}

