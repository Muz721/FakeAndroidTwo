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
import com.ty.fakeandroidtwo.ui.activity.FakeTwoCalculateActivity;
import com.ty.fakeandroidtwo.utils.FakeTwoAmountUtils;
import com.ty.fakeandroidtwo.utils.FakeTwoGeneralUtils;
import com.ty.fakeandroidtwo.utils.FakeTwoOptionsPickerViewUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 商业贷款
 */
public class FakeTwoHouseFragment extends FakeTwoBaseFragment {

    @BindView(R.id.fake_two_title_txt_title)
    TextView fakeTwoTitleTxtTitle;
    @BindView(R.id.fake_two_house_txt_repayment_method)
    TextView fakeTwoHouseTxtRepaymentMethod;
    @BindView(R.id.fake_two_house_linear_repayment_method)
    RelativeLayout fakeTwoHouseLinearRepaymentMethod;
    @BindView(R.id.fake_two_house_txt_calculate_method)
    TextView fakeTwoHouseTxtCalculateMethod;
    @BindView(R.id.fake_two_house_linear_calculate_method)
    RelativeLayout fakeTwoHouseLinearCalculateMethod;
    @BindView(R.id.fake_two_house_txt_total_loans)
    EditText fakeTwoHouseTxtTotalLoans;
    @BindView(R.id.fake_two_house_linear_total_loans)
    RelativeLayout fakeTwoHouseLinearTotalLoans;
    @BindView(R.id.fake_two_house_txt_mortgage_years)
    TextView fakeTwoHouseTxtMortgageYears;
    @BindView(R.id.fake_two_house_linear_mortgage_years)
    RelativeLayout fakeTwoHouseLinearMortgageYears;
    @BindView(R.id.fake_two_house_txt_interest_rate)
    EditText fakeTwoHouseTxtInterestRate;
    @BindView(R.id.fake_two_house_linear_interest_rate)
    RelativeLayout fakeTwoHouseLinearInterestRate;
    @BindView(R.id.fake_two_house_txt_calculate)
    TextView fakeTwoHouseTxtCalculate;
    private OptionsPickerView fakeTwoRepaymentMethodSelect, fakeTwoMortgageYearsSelect;

    private ArrayList<FakeTwoBaseBean> fakeTwoRepaymentMethodBean = new ArrayList<>();
    private ArrayList<FakeTwoBaseBean> fakeTwoMortgageYearsBean = new ArrayList<>();
    private int repaymentMethodPosition, mortgageYearsPosition;
    private int allMortgageYears = 30;
    private double totalLoans;
    private double bankInterestRate;

    @Override
    protected int getLayout() {
        return R.layout.fake_two_fragment_house;
    }


    @Override
    protected void initViewAndEvent() {
        fakeTwoTitleTxtTitle.setText(getResources().getString(R.string.fake_two_house));
        fakeTwoRepaymentMethodBean.add(new FakeTwoBaseBean(0, getResources().getString(R.string.fake_two_principal_1)));
        fakeTwoRepaymentMethodBean.add(new FakeTwoBaseBean(1, getResources().getString(R.string.fake_two_principal_2)));
        fakeTwoRepaymentMethodSelect = FakeTwoOptionsPickerViewUtils.open(getContext(),"选择还款方式", fakeTwoRepaymentMethodSelect, fakeTwoRepaymentMethodBean, pickerSelect);
        for (int i = 0; i < allMortgageYears; i++) {
            fakeTwoMortgageYearsBean.add(new FakeTwoBaseBean(1, (i + 1) + "年"));
            fakeTwoMortgageYearsSelect = FakeTwoOptionsPickerViewUtils.open(getContext(),"选择还款方式", fakeTwoMortgageYearsSelect, fakeTwoMortgageYearsBean, pickerSelect);
        }

        fakeTwoHouseTxtRepaymentMethod.setText(fakeTwoRepaymentMethodBean.get(0).getName());
        fakeTwoHouseTxtMortgageYears.setText(fakeTwoMortgageYearsBean.get(0).getName());
        fakeTwoHouseTxtCalculateMethod.setText("总价");
        fakeTwoHouseTxtTotalLoans.setHint(totalLoans + "");
        fakeTwoHouseTxtInterestRate.setHint(bankInterestRate + "");
        fakeTwoHouseTxtTotalLoans.addTextChangedListener(txtTotalLoans);
        fakeTwoHouseTxtInterestRate.addTextChangedListener(txtBankInterestRate);
        FakeTwoRxView.clicks(fakeTwoHouseTxtCalculate)
                .subscribe(new FakeTwoRxViewOnclick<View>() {
                    @Override
                    public void onNext(View view) {
                        String txtTotalLoans = fakeTwoHouseTxtTotalLoans.getText().toString().trim();
                        String txtBankInterestRate = fakeTwoHouseTxtInterestRate.getText().toString().trim();

                        if (!TextUtils.isEmpty(txtTotalLoans) && txtTotalLoans.charAt(txtTotalLoans.length() - 1) == '.') {
                            txtTotalLoans = txtTotalLoans.substring(0, txtTotalLoans.length() - 1);
                        }
                        if (!TextUtils.isEmpty(txtBankInterestRate) && txtBankInterestRate.charAt(txtBankInterestRate.length() - 1) == '.') {
                            txtBankInterestRate = txtBankInterestRate.substring(0, txtBankInterestRate.length() - 1);
                        }
                        //判断末尾是否的字符是'.',如果是则删除，防止无法转成double
                        totalLoans = Double.parseDouble(TextUtils.isEmpty(txtTotalLoans) ? 0.00 + "" : txtTotalLoans);
                        bankInterestRate = Double.parseDouble(TextUtils.isEmpty(txtBankInterestRate) ? 0.00 + "" : txtBankInterestRate);

                        if (totalLoans != 0.00 && bankInterestRate != 0.00) {
                            Intent intent = new Intent(getActivity(), FakeTwoCalculateActivity.class);
                            intent.putExtra("totalLoans", totalLoans);
                            intent.putExtra("bankInterestRate", bankInterestRate);
                            intent.putExtra("mortgageYearsPosition", mortgageYearsPosition);
                            intent.putExtra("repaymentMethodPosition", repaymentMethodPosition);
                            startActivity(intent);
                        } else if (totalLoans == 0.00 && bankInterestRate == 0.00) {
                            hint("请填写贷款总额和银行利率");
                        } else if (totalLoans == 0.00) {
                            hint("请填写贷款总额");
                        } else {
                            hint("请填写银行利率");
                        }

                    }
                });
    }


    @OnClick({R.id.fake_two_house_txt_interest_rate, R.id.fake_two_house_txt_total_loans, R.id.fake_two_house_linear_repayment_method, R.id.fake_two_house_linear_calculate_method, R.id.fake_two_house_linear_total_loans, R.id.fake_two_house_linear_mortgage_years, R.id.fake_two_house_linear_interest_rate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fake_two_house_linear_repayment_method:
                fakeTwoRepaymentMethodSelect.show(fakeTwoHouseLinearRepaymentMethod);
                break;
            case R.id.fake_two_house_linear_calculate_method:
                break;
            case R.id.fake_two_house_linear_total_loans:
            case R.id.fake_two_house_txt_total_loans:
                FakeTwoGeneralUtils.getFocusAndOpenSoftKeyboard(fakeTwoHouseTxtTotalLoans);
                fakeTwoHouseTxtTotalLoans.setSelection(fakeTwoHouseTxtTotalLoans.getText().length());
                break;
            case R.id.fake_two_house_linear_mortgage_years:
                fakeTwoMortgageYearsSelect.show(fakeTwoHouseLinearMortgageYears);
                break;
            case R.id.fake_two_house_linear_interest_rate:
            case R.id.fake_two_house_txt_interest_rate:
                FakeTwoGeneralUtils.getFocusAndOpenSoftKeyboard(fakeTwoHouseTxtInterestRate);
                fakeTwoHouseTxtInterestRate.setSelection(fakeTwoHouseTxtInterestRate.getText().length());
                break;
        }
    }

    /**
     * 选择器点击事件
     */
    public OnOptionsSelectListener pickerSelect = new OnOptionsSelectListener() {
        @Override
        public void onOptionsSelect(int options1, int options2, int options3, View v) {
            switch (v.getId()) {
                case R.id.fake_two_house_linear_repayment_method:
                    fakeTwoHouseTxtRepaymentMethod.setText(fakeTwoRepaymentMethodBean.get(options1).getName());
                    repaymentMethodPosition = options1;
                    break;
                case R.id.fake_two_house_linear_mortgage_years:
                    fakeTwoHouseTxtMortgageYears.setText(fakeTwoMortgageYearsBean.get(options1).getName());
                    mortgageYearsPosition = options1;
                    break;
            }
        }
    };
    /**
     * 贷款总数
     */
    private TextWatcher txtTotalLoans = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (TextUtils.isEmpty(s.toString())) {
                s = "0.00";
            }

            if (!s.toString().equals(FakeTwoAmountUtils.amount(s.toString()))) {
                fakeTwoHouseTxtTotalLoans.setText(FakeTwoAmountUtils.amount(s.toString()));
            }
            fakeTwoHouseTxtTotalLoans.setSelection(fakeTwoHouseTxtTotalLoans.getText().length());
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };
    /**
     * 年利率
     */
    private TextWatcher txtBankInterestRate = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (TextUtils.isEmpty(s.toString())) {
                s = "0.00";
            }
            if (!s.toString().equals(FakeTwoAmountUtils.amount(s.toString()))) {

                fakeTwoHouseTxtInterestRate.setText(FakeTwoAmountUtils.amount(s.toString()));
            }
            fakeTwoHouseTxtInterestRate.setSelection(fakeTwoHouseTxtInterestRate.getText().length());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}
