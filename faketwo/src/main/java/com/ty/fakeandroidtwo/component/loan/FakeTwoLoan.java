package com.ty.fakeandroidtwo.component.loan;

import java.math.BigDecimal;
import java.util.List;


public class FakeTwoLoan {

    private BigDecimal totalLoanMoney; //贷款总额
    private int totalMonth; //还款月份
    private double loanRate; //贷款年利率

    private BigDecimal totalInterest; // 总利息数
    private BigDecimal totalRepayment; // 还款总额
    private BigDecimal avgRepayment; // 月均还款额



    public BigDecimal getTotalLoanMoney() {
        return totalLoanMoney;
    }

    public void setTotalLoanMoney(BigDecimal totalLoanMoney) {
        this.totalLoanMoney = totalLoanMoney;
    }

    public int getTotalMonth() {
        return totalMonth;
    }

    public void setTotalMonth(int totalMonth) {
        this.totalMonth = totalMonth;
    }

    public double getLoanRate() {
        return loanRate;
    }

    public void setLoanRate(double loanRate) {
        this.loanRate = loanRate;
    }

    public BigDecimal getTotalInterest() {
        return totalInterest;
    }

    public void setTotalInterest(BigDecimal totalInterest) {
        this.totalInterest = totalInterest;
    }

    public BigDecimal getTotalRepayment() {
        return totalRepayment;
    }

    public void setTotalRepayment(BigDecimal totalRepayment) {
        this.totalRepayment = totalRepayment;
    }

    public BigDecimal getAvgRepayment() {
        return avgRepayment;
    }

    public void setAvgRepayment(BigDecimal avgRepayment) {
        this.avgRepayment = avgRepayment;
    }



    @Override
    public String toString() {

        return "每月还款: " + getAvgRepayment() + "\t总利息: " + getTotalInterest() +
                "\t还款总额: " + getTotalRepayment() + "\n";
    }
}
