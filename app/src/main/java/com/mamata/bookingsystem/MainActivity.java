package com.mamata.bookingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private Spinner spi;
    private TextView etcheck, etcheckout;
    private EditText AdultNo,ChildNo, RoomNo;
    private Button btncal;
    private TextView tvprice, tvRes;

    int year1, year2;
    int month1, month2;
    int day1, day2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etcheck = findViewById(R.id.etcheck);
        etcheckout = findViewById(R.id.etcheckout);
        AdultNo = findViewById(R.id.AdultNo);
        ChildNo = findViewById(R.id.ChildNo);
        RoomNo = findViewById(R.id.RoomNo);
        spi = findViewById(R.id.spi);
        tvprice = findViewById(R.id.tvprice);
        tvRes = findViewById(R.id.tvRes);
        btncal = findViewById(R.id.btncal);

        final String roomtype[] = {"Select room type", "Deluxe - Rs.2000", "Presidential - Rs.5000", "Premium - Rs.4000"};
        final ArrayAdapter adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                roomtype
        );
        spi.setAdapter(adapter);

        etcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDatePicker();
            }
        });

        etcheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDatePickerII();

            }
        });
        btncal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rooms;
                double total, price, vat, grossTotal;


                if (TextUtils.isEmpty(etcheck.getText())) {
                    etcheck.setError("Please select check date.");
                    return;
                } else if (TextUtils.isEmpty(etcheckout.getText())) {
                    etcheckout.setError("Please select the checkout date");
                    return;
                } else if (TextUtils.isEmpty(AdultNo.getText())) {
                    AdultNo.setError("Please enter no of adult");
                    return;
                } else if (TextUtils.isEmpty(ChildNo.getText())) {
                    ChildNo.setError("Please enter no of children");
                    return;
                } else if (TextUtils.isEmpty(RoomNo.getText())) {
                    RoomNo.setError("Enter no of rooms.");
                    return;
                }
                rooms = Integer.parseInt(RoomNo.getText().toString());

                if (spi.getSelectedItem() == "Deluxe - Rs.2000") ;
                {
                    tvprice.setText("2000");
                }

                if (spi.getSelectedItem() == "Presidential - Rs.5000") ;
                {
                    tvprice.setText("5000");
                }

                if (spi.getSelectedItem() == "Premium - Rs.4000") ;
                {
                    tvprice.setText("4000");


                }
                Calendar date1 = Calendar.getInstance();
                Calendar date2 = Calendar.getInstance();

                date1.clear();
                date1.set(year1,month1,day1);
                date2.clear();
                date2.set(year2,month2,day2);

                long diff = date2.getTimeInMillis() - date1.getTimeInMillis();
                float dayCount = (float) diff / (24 * 60 * 60 *1000);

                price = Integer.parseInt(tvprice.getText().toString());
                total = price * rooms;
                vat = 0.13 * total;
                grossTotal = total + vat;
                tvRes.append("Total: Rs. " + total + "\n VAT (13%): Rs. " + vat + "\n Grand Total: Rs. " + grossTotal + "\n");


            }
        });
    }

        private void loadDatePicker(){
            final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                final int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String date = (month + 1) + "/" + day + "/" + year;
                        year1 = year;
                        month1 = month;
                        day1 = dayOfMonth;
                        etcheck.setText(date);
                    }
                }, year, month, day);

                datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
                datePickerDialog.show();
            }

    private void loadDatePickerII(){
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = (month + 1) + "/" + day + "/" + year;
                year2 = year;
                month2 = month;
                day2 = dayOfMonth;
                etcheckout.setText(date);
            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
        datePickerDialog.show();
    }
}

