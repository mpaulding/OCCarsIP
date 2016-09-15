package edu.orangecoastcollege.cs273.mpaulding.occars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class PurchaseActivity extends Activity {
    // THE AUTO OBJECT CONTAINS THE INFORMATION ABOUT THE VEHICLE BEING PURCHASED
    Car mCar;

    // THE DATA TO BE PASSED TO THE LOAN ACTIVITY
    String loanReport;
    String monthlyPayment;

    // LAYOUT INPUT REFERENCES
    private EditText carPriceET;
    private EditText downPayET;
    private RadioGroup loanTermRG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        //ESTABLISH REFERENCES TO EDITABLE TEXT FIELDS AND RADIO BUTTON
        carPriceET = (EditText) findViewById(R.id.carPriceEditText);
        downPayET = (EditText) findViewById(R.id.downPaymentEditText);
        loanTermRG = (RadioGroup) findViewById(R.id.loanTermRadioGroup);

        //CREATE AN AUTOMOBILE OBJECT TO STORE AUTO DATA
        mCar = new Car();
    }

    private void collectAutoInputData() {
        // TASK 1: SET THE CAR PRICE50
        mCar.setPrice (Double.parseDouble(carPriceET.getText()
                .toString()));

        //TASK 2: SET THE DOWN PAYMENT
        mCar.setDownPayment((double)
                Integer.valueOf(downPayET.getText()
                        .toString()));

        //TASK 3 SET THE LOAN TERM
        Integer radioId = loanTermRG.getCheckedRadioButtonId();
        RadioButton term = (RadioButton) findViewById(radioId);
        mCar.setLoanTerm(term.getText().toString());
    }
    private void buildLoanReport() {
        // TASK 1: CONSTRUCT THE MONTHLY PAYMENT
        monthlyPayment = getString(R.string.report_line1)
                + String.format("%.02f", mCar.monthlyPayment());


        // TASK 2: CONSTRUCT THE LOAN REPORT
        loanReport = getString(R.string.report_line2)
                + String.format("%10.02f", mCar.getPrice());
        loanReport += getString(R.string.report_line3)
                + String.format("%10.02f", mCar.getDownPayment());

        loanReport += getString(R.string.report_line5)
                + String.format("%18.02f", mCar.taxAmount());
        loanReport += getString(R.string.report_line6)
                + String.format("%18.02f", mCar.totalCost());
        loanReport += getString(R.string.report_line7)
                + String.format("%12.02f", mCar.borrowedAmount());
        loanReport += getString(R.string.report_line8)
                + String.format("%12.02f", mCar.interestAmount());

        loanReport += "\n" + getString(R.string.report_line4) + " " + mCar.getLoanTerm() + " years.";

        loanReport += "\n" + getString(R.string.report_line9);
        loanReport += getString(R.string.report_line10);
        loanReport += getString(R.string.report_line11);

    }

    public void activateLoanSummary(View view) {
        //TASK 1: BUILD A LOAN REPORT FROM THE INPUT DATA
        collectAutoInputData();
        buildLoanReport();

        //TASK 2: CREATE AN INTENT TO DISPLAY THE LOAN SUMMARY ACTIVITY
        Intent launchReport = new Intent(this, LoanSummaryActivity.class);

        //TASK 3: PASS THE LOAN SUMMARY ACTIVITY TWO PIECES OF DATA:
        //     THE LOAN REPORT CONTAINING LOAN DETAILS
        //     THE MONTHLY PAYMENT
        launchReport.putExtra("LoanReport", loanReport);
        launchReport.putExtra("MonthlyPayment", monthlyPayment);

        //TASK 4: START THE LOAN ACTIVITY
        startActivity(launchReport);
    }


}
