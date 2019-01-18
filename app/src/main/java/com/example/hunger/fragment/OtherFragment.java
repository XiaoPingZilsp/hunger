package com.example.hunger.fragment;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hunger.R;
import com.example.hunger.bean.PeopleInfo;

/*public class OtherFragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_fragment);
    }
}*/


public class OtherFragment extends Fragment implements TextView.OnEditorActionListener{
    private Button btn_upload,btn_collect,btn_comment;



    public interface OtherFragmentListener {
        void onUpload();
        void onCollect();
        void onComment();

    }

    public OtherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_other, container, false);


        btn_upload = (Button)view.findViewById(R.id.btn_upload);
        btn_collect = (Button)view.findViewById(R.id.btn_collect);
        btn_comment = (Button)view.findViewById(R.id.btn_comment);


      btn_upload .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               OtherFragment.OtherFragmentListener activity0 = (OtherFragment.OtherFragmentListener)getActivity();
                activity0.onUpload();
            }
        });






    btn_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               OtherFragment.OtherFragmentListener activity0 = (OtherFragment.OtherFragmentListener)getActivity();
                activity0.onCollect();
            }
        });





      btn_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OtherFragment.OtherFragmentListener activity0 = (OtherFragment.OtherFragmentListener)getActivity();
                activity0.onComment();
            }
        });




        return view;
    }




    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        return false;
    }

}
