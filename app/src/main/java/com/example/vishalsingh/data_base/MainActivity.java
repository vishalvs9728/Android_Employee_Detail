package com.example.vishalsingh.data_base;

import android.database.Cursor;
import android.database.sqlite.*;
import android.app.Activity;
import android.os.Bundle;

import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.io.IOException;

public class MainActivity extends Activity implements OnClickListener {
    private static final String database_name="details.db";
    private static final String table_name="details";
    private SQLiteDatabase sqldb;

    Button btn1, btn2, btn3, btn4;
    Spinner spn;
    TextView tv1,tv2,tv3;
    EditText edt1, edt2,edt3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);

        //spn = (Spinner) findViewById(R.id.spinner);
        edt1 = (EditText) findViewById(R.id.editText1);
        edt2 = (EditText) findViewById(R.id.editText2);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv3 = (TextView) findViewById(R.id.tv3);
        edt3=(EditText)findViewById(R.id.editText3);
        sqldb = openOrCreateDatabase("details.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        try{sqldb.execSQL("create table "+table_name+"(empid INTEGER PRIMARY KEY ,NAME TEXT, Desig TEXT)");}
        catch(Exception e){}

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

      /* spn=(Spinner)findViewById(R.id.spinner);
        String[] spin1={"First_name","Vishal","gopal","uska_roomie","priyanka","vineeta","kamal","trisha","Ashish","Tarun","Sachin","Ravi","Himashu","Pankaj"};
        ArrayAdapter  mAdap=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,spin1);
        mAdap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(mAdap);*/


    }
    public void onClick(View vp )
    {
        String str1=edt1.getText().toString();
        String str2=edt2.getText().toString();
        String str=edt3.getText().toString();


        switch(vp.getId())
        {case R.id.button1:
            tv1.setText(null);
            tv2.setText(null);
            tv3.setText(null);

            try {
                sqldb.execSQL("insert into details(empid,Name,Desig) values('"+str1+"','"+str2+"','"+str+"')");
                Toast.makeText(MainActivity.this, "Detail added", Toast.LENGTH_LONG).show();
                edt1.setText(null);
                edt3.setText(null);
                edt2.setText(null);

            }catch(Exception e){
                Toast.makeText(MainActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            break;

            case R.id.button2:
                try{ sqldb.execSQL("delete from details where empid='"+str1+"'");
                    Toast.makeText(MainActivity.this,"Detail deleted",Toast.LENGTH_LONG).show();}
                catch(Exception e)
                {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();}
                edt1.setText(null);
                edt3.setText(null);
                edt2.setText(null);
                break;
            case R.id.button3:
                      if(edt3.getText().toString().length()==0) {
                          try {
                              sqldb.execSQL("UPDATE details Set NAME='" + str2 + "' where empid='" + str1 + "'");
                              Toast.makeText(MainActivity.this, "Detail Updated", Toast.LENGTH_LONG).show();
                          } catch (Exception e) {
                              Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                          }
                      }
                if(edt2.getText().toString().length()==0) {
                    try {
                        sqldb.execSQL("UPDATE details Set Desig='"+str+"' where empid='" + str1 + "'");
                        Toast.makeText(MainActivity.this, "Detail Updated", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
                edt1.setText(null);
                edt3.setText(null);
                edt2.setText(null);
                break;
            case R.id.button4:
                try



                {
                    tv1.setText("empid");
                    tv2.setText("Name");
                    tv3.setText("Design");
                    Cursor c = sqldb.query(table_name,null,null,null,null,null,null);
                    if(c.moveToFirst())
                    {
                        while(c.isAfterLast()==false)
                        {
                            tv1.append("\n");
                                tv1.append(c.getString(0));
                                tv2.append("\n");
                                tv2.append(c.getString(1));
                            tv3.append("\n");
                                tv3.append(c.getString(2));

                            c.moveToNext();




                        }


                    }



                }


                catch(Exception e)
                {}

                Toast.makeText(MainActivity.this,"DATA",Toast.LENGTH_LONG).show();
                break;




                }




        }




    }



