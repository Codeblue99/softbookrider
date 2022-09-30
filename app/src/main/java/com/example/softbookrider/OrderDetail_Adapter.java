package com.example.softbookrider;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.List;

public class OrderDetail_Adapter extends ArrayAdapter<OrderDetail> {
    Context context;
    List<OrderDetail> arrayorder;

    public OrderDetail_Adapter(@NonNull Context context, List<OrderDetail> arrayorder ) {
        super(context,R.layout.custom_orderdetail,arrayorder);
        this.context=context;
        this.arrayorder=arrayorder;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_orderdetail,null,true);

        TextView deladdress=(TextView) view.findViewById(R.id.deladdress);
        TextView oproduct=(TextView) view.findViewById(R.id.oproduct);
        TextView oqty=(TextView) view.findViewById(R.id.oqty);
        TextView ostore=(TextView) view.findViewById(R.id.ostore);
        TextView storeaddress=(TextView) view.findViewById(R.id.storeaddress);
        TextView maplocation=(TextView) view.findViewById(R.id.maplocation);

        maplocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),BookStoreLocationActivity.class);
                intent.putExtra("latitude",arrayorder.get(position).getBookstore_latitude());
                intent.putExtra("longitude",arrayorder.get(position).getBookstore_longitude());
                intent.putExtra("store",arrayorder.get(position).getBookstore_name());

                intent.putExtra("latitude2",arrayorder.get(position).getUser_latitude());
                intent.putExtra("longitude2",arrayorder.get(position).getUser_longitude());
                intent.putExtra("store2",arrayorder.get(position).getEl_address1());
                getContext().startActivity(intent);
            }
        });





        deladdress.setText("Delivery Address : "+arrayorder.get(position).getEl_address1() );
        oproduct.setText("Product  Name : "+ arrayorder.get(position).getProduct_name() );
        oqty.setText("Order Qty : "+arrayorder.get(position).getOrder_qty() );
        ostore.setText("BookStore Name : "+arrayorder.get(position).getBookstore_name() );
        storeaddress.setText("Store Address : "+arrayorder.get(position).getBookstore_address() );



        return view;
    }
}

