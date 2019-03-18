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

import com.ty.fakeandroidtwo.R;
import com.ty.fakeandroidtwo.base.FakeTwoBaseFragment;
import com.ty.fakeandroidtwo.component.rx.FakeTwoRxView;
import com.ty.fakeandroidtwo.component.rx.FakeTwoRxViewOnclick;
import com.ty.fakeandroidtwo.ui.activity.FakeTwoWageActivity;
import com.ty.fakeandroidtwo.utils.FakeTwoAmountUtils;
import com.ty.fakeandroidtwo.utils.FakeTwoGeneralUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 个人所得税
 */
public class FakeTwoWageFragment extends FakeTwoBaseFragment {

    @BindView(R.id.fake_two_title_txt_title)
    TextView fakeTwoTitleTxtTitle;
    @BindView(R.id.fake_two_wage_txt_before_tax)
    EditText fakeTwoWageTxtBeforeTax;
    @BindView(R.id.fake_two_wage_linear_before_tax)
    RelativeLayout fakeTwoWageLinearBeforeTax;
    @BindView(R.id.fake_two_wage_txt_five_insurance)
    EditText fakeTwoWageTxtFiveInsurance;
    @BindView(R.id.fake_two_wage_linear_five_insurance)
    RelativeLayout fakeTwoWageLinearFiveInsurance;
    @BindView(R.id.fake_two_wage_txt_marking_point)
    EditText fakeTwoWageTxtMarkingPoint;
    @BindView(R.id.fake_two_wage_linear_marking_point)
    RelativeLayout fakeTwoWageLinearMarkingPoint;
    @BindView(R.id.fake_two_wage_txt_tax_rate)
    EditText fakeTwoWageTxtTaxRate;
    @BindView(R.id.fake_two_wage_linear_tax_rate)
    RelativeLayout fakeTwoWageLinearTaxRate;
    @BindView(R.id.fake_two_wage_txt_quick_deduction)
    EditText fakeTwoWageTxtQuickDeduction;
    @BindView(R.id.fake_two_wage_linear_quick_deduction)
    RelativeLayout fakeTwoWageLinearQuickDeduction;
    @BindView(R.id.fake_two_wage_txt_calculate)
    TextView fakeTwoWageTxtCalculate;
    private double beforeTax, insurance, markingPoint, taxRate, quickDeduction;

    @Override
    protected int getLayout() {
        return R.layout.fake_two_fragment_wage;
    }

    @Override
    protected void initViewAndEvent() {
        fakeTwoTitleTxtTitle.setText("个人所得税");
        fakeTwoWageTxtBeforeTax.setHint(beforeTax + "");
        fakeTwoWageTxtFiveInsurance.setHint(insurance + "");
        fakeTwoWageTxtMarkingPoint.setHint(markingPoint + "");
        fakeTwoWageTxtTaxRate.setHint(taxRate + "");
        fakeTwoWageTxtQuickDeduction.setHint(quickDeduction + "");
        fakeTwoWageTxtBeforeTax.addTextChangedListener(txtBeforeTax);
        fakeTwoWageTxtFiveInsurance.addTextChangedListener(txtInsurance);
        fakeTwoWageTxtMarkingPoint.addTextChangedListener(txtMarkingPoint);
        fakeTwoWageTxtTaxRate.addTextChangedListener(txtTaxRate);
        fakeTwoWageTxtQuickDeduction.addTextChangedListener(txtQuickDeduction);
        FakeTwoRxView.clicks(fakeTwoWageTxtCalculate)
                .subscribe(new FakeTwoRxViewOnclick<View>() {
                    @Override
                    public void onNext(View view) {

                        beforeTax = processDate(fakeTwoWageTxtBeforeTax);
                        insurance = processDate(fakeTwoWageTxtFiveInsurance);
                        markingPoint = processDate(fakeTwoWageTxtMarkingPoint);
                        taxRate = processDate(fakeTwoWageTxtTaxRate);
                        quickDeduction = processDate(fakeTwoWageTxtQuickDeduction);


                        if (beforeTax != 0.00) {//判断是否填写税前工资
                            Intent intent = new Intent(getActivity(), FakeTwoWageActivity.class);
                            intent.putExtra("beforeTax", beforeTax);
                            intent.putExtra("insurance", insurance);
                            intent.putExtra("markingPoint", markingPoint);
                            intent.putExtra("taxRate", taxRate);
                            intent.putExtra("quickDeduction", quickDeduction);
                            startActivity(intent);
                        } else {
                            hint("请填写税前工资");
                        }
                    }
                });
    }

    /**
     * 填写的数据处理
     * @param editText  输入框
     * @return
     */
    private double processDate(EditText editText) {
        String txtDate = editText.getText().toString().trim();
        //判断末尾是否的字符是'.',如果是则删除，防止无法转成double
        if (!TextUtils.isEmpty(txtDate) && txtDate.charAt(txtDate.length() - 1) == '.') {
            txtDate = txtDate.substring(0, txtDate.length() - 1);
        }
        //对所有填写的数据进行处理
        return Double.parseDouble(TextUtils.isEmpty(txtDate) ? 0.00 + "" : txtDate);
    }

    @OnClick({R.id.fake_two_wage_txt_before_tax, R.id.fake_two_wage_linear_before_tax, R.id.fake_two_wage_txt_five_insurance, R.id.fake_two_wage_linear_five_insurance, R.id.fake_two_wage_txt_marking_point, R.id.fake_two_wage_linear_marking_point, R.id.fake_two_wage_txt_tax_rate, R.id.fake_two_wage_linear_tax_rate, R.id.fake_two_wage_txt_quick_deduction, R.id.fake_two_wage_linear_quick_deduction})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fake_two_wage_txt_before_tax:
            case R.id.fake_two_wage_linear_before_tax:
                FakeTwoGeneralUtils.getFocusAndOpenSoftKeyboard(fakeTwoWageTxtBeforeTax);
                fakeTwoWageTxtBeforeTax.setSelection(fakeTwoWageTxtBeforeTax.getText().length());
                break;
            case R.id.fake_two_wage_txt_five_insurance:
            case R.id.fake_two_wage_linear_five_insurance:
                FakeTwoGeneralUtils.getFocusAndOpenSoftKeyboard(fakeTwoWageTxtFiveInsurance);
                fakeTwoWageTxtFiveInsurance.setSelection(fakeTwoWageTxtFiveInsurance.getText().length());
                break;
            case R.id.fake_two_wage_txt_marking_point:
            case R.id.fake_two_wage_linear_marking_point:
                FakeTwoGeneralUtils.getFocusAndOpenSoftKeyboard(fakeTwoWageTxtMarkingPoint);
                fakeTwoWageTxtMarkingPoint.setSelection(fakeTwoWageTxtMarkingPoint.getText().length());
                break;
            case R.id.fake_two_wage_txt_tax_rate:
            case R.id.fake_two_wage_linear_tax_rate:
                FakeTwoGeneralUtils.getFocusAndOpenSoftKeyboard(fakeTwoWageTxtTaxRate);
                fakeTwoWageTxtTaxRate.setSelection(fakeTwoWageTxtTaxRate.getText().length());
                break;
            case R.id.fake_two_wage_txt_quick_deduction:
            case R.id.fake_two_wage_linear_quick_deduction:
                FakeTwoGeneralUtils.getFocusAndOpenSoftKeyboard(fakeTwoWageTxtQuickDeduction);
                fakeTwoWageTxtQuickDeduction.setSelection(fakeTwoWageTxtQuickDeduction.getText().length());
                break;
        }
    }

    /**
     * 税前工资
     */
    private TextWatcher txtBeforeTax = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (TextUtils.isEmpty(s.toString())) {
                s = "0.00";
            }
            if (!s.toString().equals(FakeTwoAmountUtils.amount(s.toString()))) {

                fakeTwoWageTxtBeforeTax.setText(FakeTwoAmountUtils.amount(s.toString()));
            }
            fakeTwoWageTxtBeforeTax.setSelection(fakeTwoWageTxtBeforeTax.getText().length());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    /**
     * 五险一金
     */
    private TextWatcher txtInsurance = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (TextUtils.isEmpty(s.toString())) {
                s = "0.00";
            }
            if (!s.toString().equals(FakeTwoAmountUtils.amount(s.toString()))) {

                fakeTwoWageTxtFiveInsurance.setText(FakeTwoAmountUtils.amount(s.toString()));
            }
            fakeTwoWageTxtFiveInsurance.setSelection(fakeTwoWageTxtFiveInsurance.getText().length());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    /**
     * 起征点
     */
    private TextWatcher txtMarkingPoint = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (TextUtils.isEmpty(s.toString())) {
                s = "0.00";
            }
            if (!s.toString().equals(FakeTwoAmountUtils.amount(s.toString()))) {

                fakeTwoWageTxtMarkingPoint.setText(FakeTwoAmountUtils.amount(s.toString()));
            }
            fakeTwoWageTxtMarkingPoint.setSelection(fakeTwoWageTxtMarkingPoint.getText().length());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    /**
     * 税率
     */
    private TextWatcher txtTaxRate = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (TextUtils.isEmpty(s.toString())) {
                s = "0.00";
            }
            if (!s.toString().equals(FakeTwoAmountUtils.amount(s.toString()))) {

                fakeTwoWageTxtTaxRate.setText(FakeTwoAmountUtils.amount(s.toString()));
            }
            fakeTwoWageTxtTaxRate.setSelection(fakeTwoWageTxtTaxRate.getText().length());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    /**
     * 速算扣除数
     */
    private TextWatcher txtQuickDeduction = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (TextUtils.isEmpty(s.toString())) {
                s = "0.00";
            }
            if (!s.toString().equals(FakeTwoAmountUtils.amount(s.toString()))) {

                fakeTwoWageTxtQuickDeduction.setText(FakeTwoAmountUtils.amount(s.toString()));
            }
            fakeTwoWageTxtQuickDeduction.setSelection(fakeTwoWageTxtQuickDeduction.getText().length());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
