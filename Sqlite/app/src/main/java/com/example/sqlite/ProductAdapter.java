package com.example.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ProductAdapter  extends BaseAdapter {

    Context context;
    List<Product> productList;
    LayoutInflater layoutInflater;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
        this.layoutInflater = (LayoutInflater.from(context));
    }

    public ProductAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return productList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.list,null);
        TextView pid = convertView.findViewById(R.id.id);
        TextView name = convertView.findViewById(R.id.name);
        TextView qty = convertView.findViewById(R.id.qty);

        pid.setText(String.valueOf(productList.get(position).getId()));
        name.setText(productList.get(position).getName());
        qty.setText(String.valueOf(productList.get(position).getQty()));

        return convertView;
    }


}
