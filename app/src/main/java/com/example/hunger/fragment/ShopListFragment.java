package com.example.hunger.fragment;


import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.example.hunger.R;
import com.example.hunger.activity.MainHomeActivity;
import com.example.hunger.bean.PeopleInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ShopListFragment extends Fragment implements TextView.OnEditorActionListener{

    public ListView l;
    public View v;
    public int position;
    public long id;
    private ListView news_list;
    private List<Map<String, Object>> mData;


    public void onClick(View v) {

    }

    public interface imageListener{
        void onListItemClick(ListView l, View v, int position, long id);
        MainHomeActivity.MyAdapter SetAdapter();
        //void showInfo(int position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_friend, container, false);

        ListView news_list = (ListView)view.findViewById(R.id.listView1);

        ShopListFragment.imageListener activity = (ShopListFragment.imageListener)getActivity();

        MainHomeActivity.MyAdapter  adapter=activity.SetAdapter();
                news_list.setAdapter(adapter);

        return view;
    }

    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        return false;
    }

}
