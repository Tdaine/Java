package com.example.abaka.homework;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Firsty1 extends AppCompatActivity {
    private DBAdapter dbAdapter;

    private Button datePicker,addButton,reButton;
    public TextView date1,moneyView,commentView;
    public EditText moneyText,commentText;
    public  float reMoney;
    public  String reComment;

    private int Year,Month,Day;
    static final int DATE_DIALOG_ID=0;
    public  String type,str1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firsty1);
        datePicker=(Button) findViewById(R.id.time);
        date1=(TextView)findViewById(R.id.inputTime);

        moneyText=(EditText)findViewById(R.id.inputNum);
        commentText=(EditText)findViewById(R.id.inputRemark);
        moneyView=(TextView)findViewById(R.id.num);
        commentView=(TextView)findViewById(R.id.remark);


        addButton = (Button)findViewById(R.id.add);
        reButton=(Button)findViewById(R.id.re);
        //spinner
        final Spinner spinner = (Spinner)findViewById(R.id.spinner);
        List<String> list = new ArrayList<String>();
        list.add("支出");
        list.add("收入");
//        String[] list=new String[]{"支出","收入","haha"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //spinner.setOnItemClickListener(spnSexItemSelLis);
        //获取spinner值
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = parent.getSelectedItem().toString();//拿到spinner类型
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //获取当前时间
        final Calendar currentDate = Calendar.getInstance();

        Year = currentDate.get(Calendar.YEAR);
        Month = currentDate.get(Calendar.MONTH);
        Day = currentDate.get(Calendar.DAY_OF_MONTH);
        // 设置文本的内容：
        str1 = new String(new StringBuilder().append(Year).append("年")
                .append(Month + 1).append("月")// 得到的月份+1，因为从0开始
                .append(Day).append("日"));
        date1.setText(str1);


        //手动设置日期
        datePicker.setOnClickListener(new btnDow_OnClickListenner());



        reButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Firsty1.this, count.class);
                startActivity(intent);
            }
        });

       addButton.setOnClickListener(updateButtonListener);

        dbAdapter = new DBAdapter(this);
        dbAdapter.open();

    }

    View.OnClickListener updateButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Bill bill = new Bill();
            Second second = new Second();
            long id = second.id_1;
            MainActivity mainActivity = new MainActivity();

            bill._name = mainActivity.sName;
            bill._type = type;
            bill._date = str1;
            bill._money = Float.parseFloat(moneyText.getText().toString());
            bill._comment = commentText.getText().toString();
            long count = dbAdapter.updateOneData(id, bill);
        }
    };

    private Spinner.OnItemSelectedListener spnSexItemSelLis =
            new Spinner.OnItemSelectedListener(){
                public void onItemSelected(AdapterView<?> parent, View v,int position,long id)

                {
                    type = parent.getSelectedItem().toString();//拿到spinner类型
                }
                public void onNothingSelected(AdapterView<?> parent) {

                }
            };

    public class btnDow_OnClickListenner implements View.OnClickListener{
        @Override
        public void onClick(View v){
            showDialog(DATE_DIALOG_ID);
        }
    }
    //定义弹出的DAtePicker对话框的时间监听器
    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Year = year;
            Month = month;
            Day = dayOfMonth;
            str1 = new String(new StringBuilder().append(year).append("年")
                    .append(Month + 1).append("月").append(Day).append("日"));//拿取到时间
            date1.setText(str1);//设置时间
        }
    };

    /**
     * 当Acrivity调用showDialog函数时会触发改函数的调用
     * @param id
     * @return
     */
    @Override
    protected Dialog onCreateDialog(int id){
        switch (id){
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,mDateSetListener,Year, Month,Day);
        }
        return null;
    }
}
