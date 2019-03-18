package com.ty.fakeandroidtwo.component.loan;

import android.util.Log;

import java.math.BigDecimal;

/**
 * 等额本息还款法
 *
 * 每月还本付息金额 =[ 本金*月利率*(1+月利率)^贷款月数 ]/[(1+月利率)^还款月数 - 1]
 *
 * 还款总利息=贷款额*贷款月数*月利率*(1+月利率)^贷款月数/[(1+月利率)^还款月数 - 1] -贷款额
 *
 * 还款总额=还款月数*贷款额*月利率*(1+月利率)^贷款月数/[(1+月利率)^还款月数 - 1]
 *
 * 每月利息 = 剩余本金*贷款月利率
 *
 * 每月本金=月供-每月利息
 */
public class FakeTwoACPIMLoanCalculator extends FakeTwoLoanCalculatorAdapter {

    @Override
    public FakeTwoLoan calLoan(BigDecimal totalLoanMoney, int totalMonth, double loanRate, int rateType) {
        FakeTwoLoan loan = new FakeTwoLoan();
        BigDecimal loanRateMonth = rateType == FakeTwoLoanUtil.RATE_TYPE_YEAR ? new BigDecimal(loanRate / 100 / 12) : new BigDecimal(loanRate / 100);
        BigDecimal factor = new BigDecimal(Math.pow(1 + loanRateMonth.doubleValue(), totalMonth));
        BigDecimal avgRepayment = totalLoanMoney.multiply(loanRateMonth).multiply(factor).divide(factor.subtract(new BigDecimal(1)), 2, BigDecimal.ROUND_HALF_UP);
        BigDecimal totalRepayment = totalLoanMoney.multiply(new BigDecimal(totalMonth)).multiply(loanRateMonth).multiply(factor).divide(factor.subtract(new BigDecimal(1)), 2, BigDecimal.ROUND_HALF_UP);//还款总额
        BigDecimal totalInterest = totalRepayment.subtract(totalLoanMoney);//总利息数
        Log.e("totalRepayment=",totalRepayment+"");
        Log.e("totalInterest=",totalInterest+"");
        loan.setLoanRate(loanRate);//贷款年利率
        loan.setTotalLoanMoney(totalLoanMoney);//贷款总额
        loan.setTotalMonth(totalMonth);//还款月份
        loan.setAvgRepayment(avgRepayment);//月均还款额
        loan.setTotalRepayment(totalRepayment);// 还款总额
        loan.setTotalInterest(totalInterest);//总利息数

        return loan;
    }

}
