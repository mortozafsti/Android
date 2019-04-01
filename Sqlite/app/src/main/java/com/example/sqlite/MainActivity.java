package com.example.sqlite;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

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

    public void getProductByProductId(View view){
        int pid = Integer.parseInt(id.getText().toString().trim());
        Product p = adapter.findProductById(pid);
        if (p != null){
            name.setText(p.getName());
            qty.setText(String.valueOf(p.getQty()));
        }else {
            Toast.makeText(context, "No Data Exixt", Toast.LENGTH_SHORT).show();
        }
    }

    public void delete(View view){
        int pid = Integer.parseInt(id.getText().toString().trim());
        adapter.deleteProduct(pid);
        Toast.makeText(context, "Delete Successfully", Toast.LENGTH_SHORT).show();
    }

    public void getProductlist(){
        List<Product> p = adapter.getList();
        System.out.println(p.size());
    }
}
