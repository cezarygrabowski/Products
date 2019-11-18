package com.example.miniprojekt1.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.miniprojekt1.R;
import com.example.miniprojekt1.database.MyDB;
import com.example.miniprojekt1.database.Product;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<ListItem> list = new ArrayList<ListItem>();
    private Context context;

    public MyAdapter(ArrayList<ListItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_element_view, null);
        }

        //Populate values
        TextView productPidText = (TextView) view.findViewById(R.id.product_pid);
        productPidText.setText("Pid produktu: ".concat(Integer.toString(list.get(position).getPid())));
        TextView productNameText = (TextView) view.findViewById(R.id.product_name);
        productNameText.setText("Nazwa produktu: ".concat(list.get(position).getName()));
        TextView productAmountText = (TextView) view.findViewById(R.id.product_amount);
        productAmountText.setText("Cena: ".concat((Integer.toString(list.get(position).getAmount()))));
        TextView productPriceText = (TextView) view.findViewById(R.id.product_price);
        productPriceText.setText("Ilosc: ".concat((Integer.toString(list.get(position).getPrice()))));
        TextView productIsSoldText = (TextView) view.findViewById(R.id.product_is_sold);
        String soldText = "niezakupiony";
        if (list.get(position).isSold()) {
            soldText = "zakupiony";
        }
        productIsSoldText.setText("Stan: ".concat(soldText));

        //Handle buttons and add onClickListeners
        Button deleteBtn = (Button) view.findViewById(R.id.delete_product_btn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDB db = MyDB.getDatabase(context);
                ListItem item = list.get(position);
                Product product = db.productDAO().loadById(item.getPid());
                db.productDAO().delete(product);
                list.remove(position); //or some other task
                notifyDataSetChanged();
            }
        });

        return view;
    }
}