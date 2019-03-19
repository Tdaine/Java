package com.example.abaka.homework;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Second extends Activity {
    private DBAdapter dbAdepter ;
    private MainActivity mainActivity;
    public EditText idEntry;

    private TextView labelView;
    private TextView displayView;
    public static long id_1;
    private String time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        MainActivity mainActivity = new MainActivity();
        idEntry = (EditText)findViewById(R.id.id_entry);

        labelView = (TextView)findViewById(R.id.label);
        displayView = (TextView)findViewById(R.id.display);
//        timeText=(EditText)findViewById(R.id.inputTime2);
//        time = timeText.getText().toString();

        Button queryAllButton = (Button)findViewById(R.id.query_all);
        Button clearButton = (Button)findViewById(R.id.clear);
        Button deleteAllButton = (Button)findViewById(R.id.delete_all);

        Button alterButton = (Button)findViewById(R.id.query);
        Button deleteButton = (Button)findViewById(R.id.delete);
       // Button updateButton = (Button)findViewById(R.id.update);


        queryAllButton.setOnClickListener(queryAllButtonListener);
        clearButton.setOnClickListener(clearButtonListener);
        deleteAllButton.setOnClickListener(deleteAllButtonListener);

        alterButton.setOnClickListener(queryButtonListener);
        deleteButton.setOnClickListener(deleteButtonListener);
       // updateButton.setOnClickListener(updateButtonListener);

        dbAdepter = new DBAdapter(this);
        dbAdepter.open();

        alterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id_1 = Integer.parseInt(idEntry.getText().toString());
                    Intent intent = new Intent(Second.this, Firsty1.class);
                    startActivity(intent);
            }
        });

    }
    View.OnClickListener queryAllButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Bill[] peoples = dbAdepter.queryAllData(mainActivity.sName);
            if (peoples == null){
                labelView.setText("数据库中没有数据");
                return;
            }
            labelView.setText("数据库：");
            String msg = "";
            for (int i = 0 ; i<peoples.length; i++){
                msg += peoples[i].toString()+"\n";
            }
            displayView.setText(msg);
        }
    };

    View.OnClickListener clearButtonListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            displayView.setText("");
        }
    };

    View.OnClickListener deleteAllButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dbAdepter.deleteAllData(mainActivity.sName);
            String msg = "数据全部删除";
            labelView.setText(msg);
        }
    };

    View.OnClickListener queryButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = Integer.parseInt(idEntry.getText().toString());
                Bill[] peoples = dbAdepter.queryOneData(id);

                if (peoples == null) {
                    labelView.setText("数据库中没有ID为" + String.valueOf(id) + "的数据");
                    return;
                }
                labelView.setText("数据库：");
                displayView.setText(peoples[0].toString());
            }
    };

    View.OnClickListener deleteButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            long id = Integer.parseInt(idEntry.getText().toString());
                long result = dbAdepter.deleteOneData(id);
                String msg = "删除ID为" + idEntry.getText().toString() + "的数据" + (result > 0 ? "成功" : "失败");
                labelView.setText(msg);
            }
    };



}
