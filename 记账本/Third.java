package com.example.abaka.homework;

import android.app.Activity;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Third extends Activity {
    private DBAdapter dbAdepter;
    private MainActivity mainActivity;
    private EditText timeText;
    private Button yearButton, monthButton, weekButton;
    private TextView inView, outView;

    private String allDate, year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        MainActivity mainActivity = new MainActivity();
        timeText = (EditText) findViewById(R.id.inputTime2);
        yearButton = (Button) findViewById(R.id.year);
        monthButton = (Button) findViewById(R.id.month);
        weekButton = (Button) findViewById(R.id.week);
        inView = (TextView) findViewById(R.id.inDisplay);
        outView = (TextView) findViewById(R.id.outDisplay);


        dbAdepter = new DBAdapter(this);
        dbAdepter.open();

        yearButton.setOnClickListener(queryYearButtonListener);
        monthButton.setOnClickListener(queryMonthButtonListener);
        weekButton.setOnClickListener(queryWeakButtonListener);
    }

    View.OnClickListener queryYearButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Bill[] peoples = dbAdepter.queryAllData(mainActivity.sName);
            if (peoples == null) {
                inView.setText("数据库中没有数据");
                return;
            }
            long inMoney = 0, outMoney = 0;
            allDate=timeText.getText().toString();
            year = allDate.substring(0, 4);//截取年
            month = allDate.substring(5, 7);
            day = allDate.substring(8, 10);
            for (int i = 0; i < peoples.length; i++) {
                if (year.equals(peoples[i]._date.substring(0, 4))) {

                    if(peoples[i]._type.equals("收入"))
                    {
                        inMoney += peoples[i]._money;
                    }else if(peoples[i]._type.equals("支出")){
                        outMoney +=peoples[i]._money;
                    }
                }
            }
            inView.setText(String.valueOf(inMoney));
            outView.setText(String.valueOf(outMoney));
        }
    };

    View.OnClickListener queryMonthButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Bill[] peoples = dbAdepter.queryAllData(mainActivity.sName);
            if (peoples == null){
                inView.setText("数据库中没有数据");
                return;
            }
            long inMoney = 0,outMoney = 0;
            allDate=timeText.getText().toString();
            year = allDate.substring(0, 4);//截取年
            month = allDate.substring(5, 7);
            day = allDate.substring(8, 10);
            for (int i = 0 ; i<peoples.length; i++){
                if(month.equals(peoples[i]._date.substring(5,7)) && year.equals( peoples[i]._date.substring(0, 4))){
                    if(peoples[i]._type.equals("收入"))
                    {
                        inMoney += peoples[i]._money;
                    }else if(peoples[i]._type.equals("支出")){
                        outMoney +=peoples[i]._money;
                    }
                }
            }
            inView.setText(String.valueOf(inMoney));
            outView.setText(String.valueOf(outMoney));
        }
    };

    View.OnClickListener queryWeakButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Bill[] peoples = dbAdepter.queryAllData(mainActivity.sName);
            if (peoples == null){
                inView.setText("数据库中没有数据");
                return;
            }
            long inMoney = 0,outMoney = 0;
            allDate=timeText.getText().toString();
            for (int i = 0 ; i<peoples.length; i++){
                if(Bill.getWeekth(peoples[i]._date) == Bill.getWeekth(allDate) ){
                    if(peoples[i]._type.equals("收入"))
                    {
                        inMoney += peoples[i]._money;
                    }else if(peoples[i]._type.equals("支出")){
                        outMoney +=peoples[i]._money;
                    }
                }
            }
            inView.setText(String.valueOf(inMoney));
            outView.setText(String.valueOf(outMoney));
        }
    };
}



