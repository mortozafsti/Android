package com.example.sqlite;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText id, name,qty;
    MyDbAdapter adapter;
    ListView listView;

    Message message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = (EditText) findViewById(R.id.editTextd);
        name = (EditText) findViewById(R.id.editText2);
        qty = (EditText) findViewById(R.id.editText3);

        adapter = new MyDbAdapter(this);

        getProductlist();

    }

    public void addUser(View view)
    {
        Product product = new Product(name.getText().toString(), Integer.parseInt(qty.getText().toString()));
        long i = adapter.insertData(product);
        if( i< 0)
        {
            Message.message(this,"Unsuccessful");
        } else
        {
            getProductlist();
            Message.message(this,"Successful");
        }
    }

    public void updateUser(View view)
    {
        Product product = new Product(Integer.parseInt(id.getText().toString()),name.getText().toString(),Integer.parseInt(qty.getText().toString()));
        long i = adapter.updateData(product);
        if( i< 0)
        {
            Message.message(this," Update Unsuccessful");
        } else
        {
            Message.message(this," Update Successful");
        }
    }

    public void getProductByProductId(View view){
        int pid = Integer.parseInt(id.getText().toString().trim());
        Product p = adapter.findProductById(pid);
        if (p != null){
            name.setText(p.getName());
            qty.setText(String.valueOf(p.getQty()));
        }else {
            Toast.makeText(this, "No Data Exixt", Toast.LENGTH_SHORT).show();
        }
    }








    public void delete(View view){
        int pid = Integer.parseInt(id.getText().toString().trim());
        adapter.deleteProduct(pid);
        Toast.makeText(this, "Delete Successfully", Toast.LENGTH_SHORT).show();
    }

    public void getProductlist(){
        listView = (ListView) findViewById(R.id.listview);
        List<Product> list = adapter.getList();
        ProductAdapter productAdapter = new ProductAdapter(this,list);
        listView.setAdapter(productAdapter);
    }
}
