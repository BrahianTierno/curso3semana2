package com.example.semana1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity{
    private static ProgressDialog dialog;
    Button button;
    TextInputLayout input_full_name;
    TextInputLayout input_email;
    TextInputLayout input_phone;
    TextInputLayout input_description;

    TextView calendarTextView;
    Calendar calendar;
    MaterialTextView full_name;

    public static void dismissDialog(){
        dialog.dismiss();
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try
        {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        catch (NullPointerException e){

        }
        input_full_name = (TextInputLayout) findViewById(R.id.full_name);
        input_email = (TextInputLayout) findViewById(R.id.email);
        input_phone = (TextInputLayout) findViewById(R.id.phone);
        input_description = (TextInputLayout) findViewById(R.id.description);
        calendarTextView = (TextView) findViewById(R.id.datepicker_text);

        calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();
        final long today = MaterialDatePicker.todayInUtcMilliseconds();
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Birthday");
        builder.setSelection(today);
        MaterialDatePicker materialDatePicker = builder.build();

        materialDatePicker.addOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                dialog.dismiss();
            }
        });
        //Year = calendar.get(Calendar.YEAR) ;
        //Month = calendar.get(Calendar.MONTH);
        //Day = calendar.get(Calendar.DAY_OF_MONTH);


        Button dialog_bt_date = (Button)findViewById(R.id.dialog_bt_date);
        dialog_bt_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = ProgressDialog.show(MainActivity.this, "",
                        "Loading datepicker", true);
                //AddCalendarEvent(findViewById(android.R.id.content).getRootView());
                materialDatePicker.show(getSupportFragmentManager(), "DATE_PICKER");
            }
        });
        Button next = (Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, PersonaDetalle.class);

                myIntent.putExtra("full_name", input_full_name.getEditText().getText().toString());
                myIntent.putExtra("birth", calendarTextView.getText());
                myIntent.putExtra("phone", input_phone.getEditText().getText().toString());
                myIntent.putExtra("email", input_email.getEditText().getText().toString());
                myIntent.putExtra("description", input_description.getEditText().getText().toString());

                startActivity(myIntent);
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                calendarTextView.setText(materialDatePicker.getHeaderText());
            }
        });

    }//onCreate
    public void AddCalendarEvent(View view) {
        Calendar calendarEvent = Calendar.getInstance();
        Intent i = new Intent(Intent.ACTION_EDIT);
        i.setType("vnd.android.cursor.item/event");
        i.putExtra("beginTime", calendarEvent.getTimeInMillis());
        i.putExtra("allDay", true);
        i.putExtra("rule", "FREQ=YEARLY");
        i.putExtra("endTime", calendarEvent.getTimeInMillis() + 60 * 60 * 1000);
        i.putExtra("title", "Calendar Event");
        startActivity(i);
    }

}