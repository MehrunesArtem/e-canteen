package com.example.rak.kappa.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rak.kappa.Dish;
import com.example.rak.kappa.R;
import com.example.rak.kappa.myListAdapter;

import java.util.ArrayList;

public class Order extends ListFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    myListAdapter myListAdapter;
    Button button;
    static ArrayList<Dish> orderList=new ArrayList<Dish>();
    private OnFragmentInteractionListener mListener;
TextView textView;
    public Order() {
    }

    public static Order newInstance(String param1, String param2) {
        Order fragment = new Order();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }




     myListAdapter = new myListAdapter(getActivity(), orderList);
        setListAdapter(myListAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_order, container, false);
        textView = (TextView)view.findViewById(R.id.total);
        textView.setText("Всего:"+myListAdapter.getTotalPrice()+" Руб.");
        button =(Button)view.findViewById(R.id.OrderButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);

        }
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l,v,position,id);
        orderList.remove(position);
        update();
    }
    public static void add(Dish dish)
    {
        orderList.add(dish);

    }
    public void update()
    {

    myListAdapter.notifyDataSetChanged();
        textView.setText("Всего:"+myListAdapter.getTotalPrice()+" Руб.");
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
