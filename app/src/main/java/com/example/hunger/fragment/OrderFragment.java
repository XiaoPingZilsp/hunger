package com.example.hunger.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.DialogFragment;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hunger.R;
import com.example.hunger.activity.MainHomeActivity;
import com.example.hunger.bean.Order;
import com.example.hunger.bean.PeopleInfo;


public class OrderFragment extends Fragment implements TextView.OnEditorActionListener {

    public interface OrderFragmentListener {
        MainHomeActivity.Adapter_order SetAdapter_order();
        Order[] showOrderInfo();
    }


    public OrderFragment() {
        // Required empty public constructor
    }

    private ListView listView2;
    private TextView order_info;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.fragment_order, container, false);

        listView2 = (ListView)view.findViewById(R.id.listView2);
        TextView order_info=(TextView)view.findViewById(R.id.order_info);

        OrderFragment.OrderFragmentListener activity = (OrderFragment.OrderFragmentListener)getActivity();

       Order[] orders=activity.showOrderInfo();
       if (orders!=null) {
        
           MainHomeActivity.Adapter_order adapter=activity.SetAdapter_order();
           listView2.setAdapter(adapter);


       }else {
           order_info.setText("没有订单！");
       }

        return view;
    }

    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        return false;
    }

}
