package edu.orangecoastcollege.cs273.mpaulding.occars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import java.text.NumberFormat;

public class PurchaseActivity extends Activity {

    private Car mCar;

    private EditText carPriceEditText;
    private EditText downPaymentEditText;
    private RadioButton threeYearsRadioButton;
    private RadioButton fourYearsRadioButton;
    private RadioButton fiveYearsRadioButton;

    private String monthlyPaymentText;
    private String loanSummaryText;

    private static NumberFormat currency = NumberFormat.getCurrencyInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        // Associate controller with views
        carPriceEditText = (EditText) findViewById(R.id.carPriceEditText);
        downPaymentEditText = (EditText) findViewById(R.id.downPaymentEditText);

        threeYearsRadioButton = (RadioButton) findViewById(R.id.threeYearsRadioButton);
        fourYearsRadioButton = (RadioButton) findViewById(R.id.fourYearsRadioButton);
        fiveYearsRadioButton = (RadioButton) findViewById(R.id.fiveYearsRadioButton);

        mCar = new Car();
    }

    private void collectCarInputData()
    {
        double carPrice, downPayment;

        try {
            carPrice = Double.parseDouble(carPriceEditText.getText().toString());
            downPayment = Double.parseDouble(downPaymentEditText.getText().toString());
        }
        catch (NumberFormatException e)
        {
            carPrice = 0.0;
            downPayment = 0.0;
        }

        int loanTerm;

        if (fiveYearsRadioButton.isChecked())
            loanTerm = 5;
        else if (fourYearsRadioButton.isChecked())
            loanTerm = 4;
        else
            loanTerm = 3;

        mCar.setPrice(carPrice);
        mCar.setDownPayment(downPayment);
        mCar.setLoanTerm(loanTerm);
    }

    private void buildLoanReport()
    {
        monthlyPaymentText = getString(R.string.report_line1) + currency.format(mCar.monthlyPayment());

        loanSummaryText = getString(R.string.report_line2) + currency.format(mCar.getPrice())
                + getString(R.string.report_line3) + currency.format(mCar.getDownPayment())
                + getString(R.string.report_line5) + currency.format(mCar.taxAmount())
                + getString(R.string.report_line6) + currency.format(mCar.totalCost())
                + getString(R.string.report_line7) + currency.format(mCar.borrowedAmount())
                + getString(R.string.report_line8) + currency.format(mCar.interestAmount()) + "\n"
                + getString(R.string.report_line4) + mCar.getLoanTerm() + " years.\n"
                + getString(R.string.report_line9)
                + getString(R.string.report_line10)
                + getString(R.string.report_line11);
    }

    public void activateLoanSummary(View view)
    {
        collectCarInputData();
        buildLoanReport();

        // Create Intent, put both Strings (monthlyPaymentText and loanReportText) in Intent
        // then start LoanSummaryActivity
        Intent loanSummaryIntent = new Intent(this, LoanSummaryActivity.class);

        // Pass the loan summary activity as two pieces of data
        loanSummaryIntent.putExtra("MonthlyPayment", monthlyPaymentText);
        loanSummaryIntent.putExtra("LoanSummary", loanSummaryText);

        // Start the LoanSummaryActivity with the Intent data
        startActivity(loanSummaryIntent);
    }

}
