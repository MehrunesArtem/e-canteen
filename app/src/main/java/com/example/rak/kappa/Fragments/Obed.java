package com.example.rak.kappa.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.rak.kappa.DBMenu;
import com.example.rak.kappa.Dish;
import com.example.rak.kappa.R;
import com.example.rak.kappa.myListAdapter;

import java.util.ArrayList;

public class Obed extends ListFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList<Dish> dishArrayList;
    myListAdapter dishAdapter;

    private OnFragmentInteractionListener mListener;
    public ArrayList<Dish> filterbytime(ArrayList<Dish> a, int time)
    {
        ArrayList<Dish> b = new ArrayList<Dish>();
        for (int i = 0; i <a.size() ; i++) {
            if(a.get(i).getTime() ==time) b.add(a.get(i));
        }
        return b;
    }
    public Obed() {
    }

    public static Obed newInstance(String param1, String param2) {
        Obed fragment = new Obed();
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
        DBMenu dbMenu = new DBMenu(getActivity());





        dishArrayList = filterbytime(dbMenu.selectAll(), 1);
       dishAdapter = new myListAdapter(getActivity(), dishArrayList);
        setListAdapter(dishAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_obed, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);




       Order.add(dishArrayList.get(position));
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
