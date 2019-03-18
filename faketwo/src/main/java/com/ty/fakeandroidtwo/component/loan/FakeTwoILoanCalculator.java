package com.ty.fakeandroidtwo.component.loan;

import java.math.BigDecimal;


public interface FakeTwoILoanCalculator {

    /**
     * 贷款计算
     *
     * @param totalLoanMoney 总贷款额
     * @param totalMonth 还款月数
     * @param loanRate 贷款利率
     * @param rateType 可选择年利率或月利率
     * @return
     */
    public FakeTwoLoan calLoan(BigDecimal totalLoanMoney, int totalMonth, double loanRate, int rateType);

}
