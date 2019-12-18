package shiful.android.babycare.Baby;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import shiful.android.babycare.R;

public class VaccineList extends AppCompatActivity {
    String getToDate,getFromDate;
    TextView fromDate,toDate,fromDate1,toDate1,fromDate2,toDate2,fromDate3,toDate3,fromDate4,toDate4,fromDate5,toDate5,fromDate6,toDate6,fromDate7,toDate7,fromDate8,toDate8,fromDate9,toDate9,fromDate10,toDate10,fromDate11,toDate11,fromDate12,toDate12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Vaccine Schedule");

        fromDate=findViewById(R.id.fromdatetv);
        toDate=findViewById(R.id.todatetv);
        fromDate1=findViewById(R.id.fromdatetv1);
        toDate1=findViewById(R.id.todatetv1);
        fromDate2=findViewById(R.id.fromdatetv2);
        toDate2=findViewById(R.id.todatetv2);
        fromDate3=findViewById(R.id.fromdatetv3);
        toDate3=findViewById(R.id.todatetv3);
        fromDate4=findViewById(R.id.fromdatetv4);
        toDate4=findViewById(R.id.todatetv4);
        fromDate5=findViewById(R.id.fromdatetv5);
        toDate5=findViewById(R.id.todatetv5);
        fromDate6=findViewById(R.id.fromdatetv6);
        toDate6=findViewById(R.id.todatetv6);
        fromDate7=findViewById(R.id.fromdatetv7);
        toDate7=findViewById(R.id.todatetv7);
        fromDate8=findViewById(R.id.fromdatetv8);
        toDate8=findViewById(R.id.todatetv8);
        fromDate9=findViewById(R.id.fromdatetv9);
        toDate9=findViewById(R.id.todatetv9);
        fromDate10=findViewById(R.id.fromdatetv10);
        toDate10=findViewById(R.id.todatetv10);
        fromDate11=findViewById(R.id.fromdatetv11);
        toDate11=findViewById(R.id.todatetv11);
        fromDate12=findViewById(R.id.fromdatetv12);
        toDate12=findViewById(R.id.todatetv12);
        getFromDate = getIntent().getExtras().getString("fromdate");
        getToDate = getIntent().getExtras().getString("todate");

        fromDate.setText(getFromDate);
        toDate.setText(getToDate);

        String sDate = getFromDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date date = null;
        try {
            date = dateFormat.parse(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 42);
        String nextOne = dateFormat.format(calendar.getTime());
        fromDate1.setText(nextOne);

        String aDate = nextOne;
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date date1 = null;
        try {
            date1 = dateFormat1.parse(aDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        calendar1.add(Calendar.DATE, 7);
        String nextTwo = dateFormat1.format(calendar1.getTime());
        toDate1.setText(nextTwo);

        fromDate2.setText(nextOne);
        toDate2.setText(nextTwo);

        fromDate3.setText(nextOne);
        toDate3.setText(nextTwo);

        String bDate = nextTwo;
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date date2 = null;
        try {
            date2 = dateFormat2.parse(bDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        calendar2.add(Calendar.DATE, 30);
        String nextThree = dateFormat2.format(calendar2.getTime());
        fromDate4.setText(nextThree);

        String mDate = nextThree;
        SimpleDateFormat dateFormat10 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date date10 = null;
        try {
            date10 = dateFormat10.parse(mDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar10 = Calendar.getInstance();
        calendar10.setTime(date10);
        calendar10.add(Calendar.DATE, 7);
        String nextTen = dateFormat10.format(calendar10.getTime());
        toDate4.setText(nextTen);

        fromDate5.setText(nextThree);
        toDate5.setText(nextTen);

        fromDate6.setText(nextThree);
        toDate6.setText(nextTen);

        String cDate = nextTen;
        SimpleDateFormat dateFormat3 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date date3 = null;
        try {
            date3 = dateFormat3.parse(cDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar3 = Calendar.getInstance();
        calendar3.setTime(date3);
        calendar3.add(Calendar.DATE, 30);
        String nextFour = dateFormat3.format(calendar3.getTime());
        fromDate7.setText(nextFour);

        String nDate = nextFour;
        SimpleDateFormat dateFormat11 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date date11 = null;
        try {
            date11 = dateFormat11.parse(nDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar11 = Calendar.getInstance();
        calendar11.setTime(date11);
        calendar11.add(Calendar.DATE, 7);
        String nextEleven = dateFormat11.format(calendar11.getTime());
        toDate7.setText(nextEleven);

        fromDate8.setText(nextFour);
        toDate8.setText(nextEleven);

        fromDate9.setText(nextFour);
        toDate9.setText(nextEleven);

        String dDate = nextEleven;
        SimpleDateFormat dateFormat4 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date date4 = null;
        try {
            date4 = dateFormat4.parse(dDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar4 = Calendar.getInstance();
        calendar4.setTime(date4);
        calendar4.add(Calendar.DATE, 30);
        String nextFive = dateFormat4.format(calendar4.getTime());
        fromDate10.setText(nextFive);
        toDate10.setText("As early as possible.");


        String eDate = getFromDate;
        SimpleDateFormat dateFormat5 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date date5 = null;
        try {
            date5 = dateFormat5.parse(eDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar5 = Calendar.getInstance();
        calendar5.setTime(date5);
        calendar5.add(Calendar.DATE, 275);
        String nextSix = dateFormat5.format(calendar5.getTime());
        fromDate11.setText(nextSix);
        toDate11.setText("As early as possible.");

        String fDate = getFromDate;
        SimpleDateFormat dateFormat6 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date date6 = null;
        try {
            date6 = dateFormat6.parse(fDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar6 = Calendar.getInstance();
        calendar6.setTime(date6);
        calendar6.add(Calendar.DATE, 455);
        String nextSeven = dateFormat6.format(calendar6.getTime());
        fromDate12.setText(nextSeven);
        toDate12.setText("As early as possible.");
    }
}