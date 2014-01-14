package com.example.PomodoroTimer;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends Activity {

    //number formatter
    private NumberFormat nf = NumberFormat.getCurrencyInstance();
    //entered bill text view
    private EditText billAmntTextView;
    //entered dtip text view
    private EditText tipTextView;
    //entered num people text view
    private EditText numPeopleTextView;

    //calculated total
    private TextView calculated_total_text;
    //calculated total per person
    private TextView calculated_total_per_person_text;
    //total tip value
    private TextView calculated_tipvalue_text;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //save reference to elements
        billAmntTextView = (EditText) findViewById(R.id.billText);
        tipTextView = (EditText) findViewById(R.id.tipText);
        numPeopleTextView = (EditText) findViewById(R.id.num_people_text);
        calculated_total_text = (TextView) findViewById(R.id.calculated_total_text);
        calculated_tipvalue_text = (TextView) findViewById(R.id.calculated_tipvalue_text);

        calculated_total_per_person_text = (TextView) findViewById(R.id.calculated_total_per_person_text);

        findViewById(R.id.calculateButton).setOnClickListener(clickListener);
    }


    //on calculate click, call the onclick listener
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            calculate();
        }
    };

    //calculate tip
    private void calculate() {
        double bill = 0;
        int numPeople = 0;
        double tip = 0;

        //read bill
        String text = billAmntTextView.getText().toString();

        if (text != null && text.trim().length() > 0) {
            bill = Double.parseDouble(text);
        }

        //read number of people
        text = numPeopleTextView.getText().toString();

        if (text != null && text.trim().length() > 0) {
            numPeople = Integer.parseInt(text);
        }


        //read tip
        text = tipTextView.getText().toString();
        if (text != null && text.trim().length() > 0) {
            tip = Double.parseDouble(text) / 100;
        }


        //calculated total tip
        double calculatedTip = bill * tip;
        //total
        double calculated_total = (bill + calculatedTip);
        //total per person
        double calculatedTotal_per_person = calculated_total / numPeople;


        calculated_total_text.setText(nf.format(calculated_total));
        calculated_total_per_person_text.setText(nf.format(calculatedTotal_per_person));
        calculated_tipvalue_text.setText(nf.format(calculatedTip));

    }
}
