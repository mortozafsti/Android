package com.example.sqlite;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText id, name,qty;
    MyDbAdapter adapter;
    Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = (EditText) findViewById(R.id.editTextd);
        name = (EditText) findViewById(R.id.editText2);
        qty = (EditText) findViewById(R.id.editText3);

        adapter = new MyDbAdapter(this);


    }

    public void addUser(View view)
    {
        Product product = new Product(name.getText().toString(), Integer.parseInt(qty.getText().toString()));
        long i = adapter.insertData(product);
        if(i<0)
        {
            Message.message(context,"Unsuccessful");
        } else
        {
            Message.message(context,"Successful");
        }
    }
}
