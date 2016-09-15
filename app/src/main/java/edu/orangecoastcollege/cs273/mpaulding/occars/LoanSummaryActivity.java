package edu.orangecoastcollege.cs273.mpaulding.occars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoanSummaryActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_summary);

        TextView monthlyPaymentTextView = (TextView) findViewById(R.id.monthlyPaymentTextView);
        TextView loanSummaryTextView = (TextView) findViewById(R.id.loanSummaryTextView);

        Intent purchaseIntent = getIntent();
        String monthlyPaymentText = purchaseIntent.getStringExtra("MonthlyPayment");
        String loanSummaryText = purchaseIntent.getStringExtra("LoanSummary");

        monthlyPaymentTextView.setText(monthlyPaymentText);
        loanSummaryTextView.setText(loanSummaryText);
    }

    public void returnToDataEntry(View view)
    {
        this.finish();
    }

}
