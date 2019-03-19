package com.example.abaka.homework;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TabActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



public class First extends Activity {
    private DBAdapter dbAdapter;

    private Button datePicker,addButton;
    private TextView date1,moneyView,commentView,labelView,displayView;
    private EditText moneyText,commentText;

    private int Year,Month,Day;
    static final int DATE_DIALOG_ID=0;
    private String type,str1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_first);
        datePicker=(Button) findViewById(R.id.time);
        date1=(TextView)findViewById(R.id.inputTime);

        moneyText=(EditText)findViewById(R.id.inputNum);
        commentText=(EditText)findViewById(R.id.inputRemark);
        moneyView=(TextView)findViewById(R.id.num);
        commentView=(TextView)findViewById(R.id.remark);

        labelView=(TextView)findViewById(R.id.result);
        displayView=(TextView)findViewById(R.id.display);

        addButton = (Button)findViewById(R.id.add);

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
        str1 = new String(new StringBuilder().append(Year).append("-")
                .append(Month + 1).append("-")// 得到的月份+1，因为从0开始
                .append(Day));
        date1.setText(str1);


        //手动设置日期
        datePicker.setOnClickListener(new btnDow_OnClickListenner());

        //库操作
        addButton.setOnClickListener(addButtonListener);

        dbAdapter = new DBAdapter(this);
        dbAdapter.open();



    }
    View.OnClickListener addButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Bill bill = new Bill();
            MainActivity mainActivity = new MainActivity();

            bill._name = mainActivity.sName;
            bill._type = type;
            bill._date = str1;
            bill._money = Float.parseFloat(moneyText.getText().toString());
            bill._comment = commentText.getText().toString();
            long colunm = dbAdapter.insert(bill);
            if (colunm == -1 ){
                labelView.setText("添加过程错误！");
            } else {
                labelView.setText("成功添加数据，ID："+String.valueOf(colunm));

            }
        }
    };

//    View.OnClickListener queryAllButtonListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Bill[] peoples = dbAdapter.queryAllData();
//            if (peoples == null){
//                labelView.setText("数据库中没有数据");
//                return;
//            }
//            labelView.setText("数据库：");
//            String msg = "";
//            for (int i = 0 ; i<peoples.length; i++){
//                msg += peoples[i].toString()+"\n";
//            }
//            displayView.setText(msg);
//        }
//    };

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
                 str1 = new String(new StringBuilder().append(year).append("-")
                        .append(Month + 1).append("-").append(Day));//拿取到时间
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
