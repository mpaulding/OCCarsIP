package edu.orangecoastcollege.cs273.mpaulding.occars;

/**
 * Data model for an auto
 */
public class Car {
    static final double STATE_TAX = .08;


    private double mPrice;
    private double mDownPayment;
    private int mLoanTerm;

    public void setPrice(double price) {
        mPrice = price;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setDownPayment(double down) {
        mDownPayment = down;
    }

    public double getDownPayment() {
        return mDownPayment;
    }

    public void setLoanTerm(int term) {
        mLoanTerm = term;
    }

    public int getLoanTerm() {
        return mLoanTerm;
    }

    public double taxAmount() {
        return mPrice * STATE_TAX;
    }

    public double totalCost() {
        return mPrice + taxAmount();
    }

    public double borrowedAmount() {
        return totalCost() - mDownPayment;
    }

    public double interestAmount() {
        double interestRate;
        if (mLoanTerm == 3)
            interestRate = 0.0462;
        else if (mLoanTerm == 4)
            interestRate = 0.0416;
        else
            interestRate = 0.0419;
        return borrowedAmount() * interestRate;
    }

    public double monthlyPayment() {
        return (borrowedAmount() + interestAmount()) / (mLoanTerm * 12);
    }

}
