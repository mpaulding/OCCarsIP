package edu.orangecoastcollege.cs273.mpaulding.occars;

import android.app.Activity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

public class LoanSummaryActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_summary);
        TextView monthlyPayTextView = (TextView) findViewById(R.id.monthlyPaymentTextView);
        TextView loanReportTextView = (TextView) findViewById(R.id.loanReportTextView);

        // PASS DATA
        Intent intent = getIntent();

        String report = intent.getStringExtra("LoanReport");
        String monthlyPay = intent.getStringExtra("MonthlyPayment");

        monthlyPayTextView.setText(monthlyPay);
        loanReportTextView.setText(report);
    }

    public void goDataEntry(View view) {
        this.finish();
    }
}
