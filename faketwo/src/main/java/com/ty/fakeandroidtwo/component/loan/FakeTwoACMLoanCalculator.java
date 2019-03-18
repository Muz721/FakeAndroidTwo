package com.ty.fakeandroidtwo.component.loan;

import java.math.BigDecimal;

/**
 * 等额本金还款法
 *
 * 每月还本付息金额=(本金/还款月数)+(本金-累计已还本金)×月利率
 *
 * 每月本金=总本金/还款月数
 *
 * 每月利息=(本金-累计已还本金)×月利率
 *
 * 还款总利息=（还款月数+1）*贷款额*月利率/2
 *
 * 还款总额=(还款月数+1)*贷款额*月利率/2+贷款额
 *
 */
public class FakeTwoACMLoanCalculator extends FakeTwoLoanCalculatorAdapter {

    @Override
    public FakeTwoLoan calLoan(BigDecimal totalLoanMoney, int totalMonth, double loanRate, int rateType) {
        FakeTwoLoan loan = new FakeTwoLoan();
        BigDecimal loanRateMonth = rateType == FakeTwoLoanUtil.RATE_TYPE_YEAR ? new BigDecimal(loanRate / 100 / 12) : new BigDecimal(loanRate / 100);

        BigDecimal avgRepayment = totalLoanMoney.divide(new BigDecimal(totalMonth),2, BigDecimal.ROUND_HALF_UP).add(totalLoanMoney.multiply(loanRateMonth)).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal totalInterest = new BigDecimal(totalMonth).add(new BigDecimal(1)).multiply(totalLoanMoney).multiply(loanRateMonth).divide(new BigDecimal(2)).setScale(2, BigDecimal.ROUND_HALF_UP);
        loan.setTotalMonth(totalMonth);
        loan.setTotalLoanMoney(totalLoanMoney);
        loan.setTotalRepayment(totalInterest.add(totalLoanMoney));
        loan.setAvgRepayment(avgRepayment);
        loan.setTotalInterest(totalInterest);


        return loan;
    }

}
