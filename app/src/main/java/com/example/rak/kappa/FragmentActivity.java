package com.example.rak.kappa;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;


import com.example.rak.kappa.Fragments.Obed;
import com.example.rak.kappa.Fragments.Order;
import com.example.rak.kappa.Fragments.Uzhin;
import com.example.rak.kappa.Fragments.Zavtrak;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

public class FragmentActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static Zavtrak zavtrak;
    static Obed obed;
    static Uzhin uzhin;

    static Order order;
    FragmentTransaction fragmentTransaction;

    String OrderFragment;

    public void setTabFragManage(String t){
        OrderFragment = t;
    }

    public String getTabFragManage(){
        return OrderFragment;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmentactivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DBMenu mDBConnector;
        Context mContext;
        ListView mListView;
        SimpleCursorAdapter scAdapter;
        Cursor cursor;
        myListAdapter myAdapter;

        DBMenu dbMenu = new DBMenu(this);
        dbMenu.deleteAll();
        JSONObject jo = new JSONObject();
        String res_b = "";
        try {
            res_b = Conn.getResponce(jo, "http://test.egeshki.ru/canteen/breakfast/get_breakfast");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        JSONArray ja_b = new JSONArray();
        try {
            ja_b = new JSONArray(res_b);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < ja_b.length(); ++i) {
            JSONObject jjo = new JSONObject();
            try {
                jjo = ja_b.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String name = "";
            try {
                name = jjo.getString("dish_name");
            } catch (JSONException e) {
                e.printStackTrace();
            }


            int dish_id = 0;
            try {
                dish_id = jjo.getInt("dish_id");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            double price = 0.0;
            try {
                price = jjo.getDouble("price");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            dbMenu.insert(dish_id, name, price, "", "", 0);
        }

        String res_l = "";
        try {
            res_l = Conn.getResponce(jo, "http://test.egeshki.ru/canteen/lunch/get_lunch");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        JSONArray ja_l = new JSONArray();
        try {
            ja_l = new JSONArray(res_l);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < ja_l.length(); ++i) {
            JSONObject jjo = new JSONObject();
            try {
                jjo = ja_l.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String name = "";
            try {
                name = jjo.getString("dish_name");
            } catch (JSONException e) {
                e.printStackTrace();
            }


            int dish_id = 0;
            try {
                dish_id = jjo.getInt("dish_id");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            double price = 0.0;
            try {
                price = jjo.getDouble("price");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            dbMenu.insert(dish_id, name, price, "", "", 1);
        }

        String res_d = "";
        try {
            res_d = Conn.getResponce(jo, "http://test.egeshki.ru/canteen/dinner/get_dinner");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        JSONArray ja_d = new JSONArray();
        try {
            ja_d = new JSONArray(res_d);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < ja_d.length(); ++i) {
            JSONObject jjo = new JSONObject();
            try {
                jjo = ja_d.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String name = "";
            try {
                name = jjo.getString("dish_name");
            } catch (JSONException e) {
                e.printStackTrace();
            }


            int dish_id = 0;
            try {
                dish_id = jjo.getInt("dish_id");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            double price = 0.0;
            try {
                price = jjo.getDouble("price");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            dbMenu.insert(dish_id, name, price, "", "", 2);
        }

        zavtrak = new Zavtrak();
        obed = new Obed();
        uzhin = new Uzhin();
        order = new Order();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        Class fragmentClass = null;


        if (id == R.id.nav_zavtrak) {
            fragmentClass = zavtrak.getClass();
        } else if (id == R.id.nav_obed) {
            fragmentClass=obed.getClass();
        } else if (id == R.id.nav_uzhin) {
            fragmentClass=uzhin.getClass();
        } else if (id == R.id.nav_prosmotr) {
        fragmentClass=order.getClass();
        } else if (id == R.id.nav_otmena) {

        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public static Order getOrder()
    {return order;
    }




}
