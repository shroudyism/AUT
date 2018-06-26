package com.example.aemcb.atry;


import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static android.app.PendingIntent.getActivity;
import static java.util.Collections.*;

public class MainActivity extends AppCompatActivity {



    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.rv);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final UsageStatsManager usageStatsManager = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);


        final long currentTime = System.currentTimeMillis();

        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -5);
        final long beginTime = cal.getTimeInMillis();

        final List<UsageStats> queryUsageStats = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_YEARLY, beginTime, currentTime);

       /* ArrayList<ListItem> li = new ArrayList<>();



        try {
            li = convert(queryUsageStats);

        } catch (PackageManager.NameNotFoundException e) {
            Log.d("try/catch","error");
        }*/



        adapter = new MyAdapter(queryUsageStats, this);

        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

        recyclerView.setAdapter(adapter);


    }

  /*  private ArrayList<ListItem> convert(List<UsageStats> queryUsageStats) throws PackageManager.NameNotFoundException {


        ArrayList<ListItem> li = new ArrayList<ListItem>();


        for (UsageStats app : queryUsageStats) {

            ListItem l=new ListItem();

             l.setTitle(app.getPackageName());
             l.setUsage(((int) (app.getTotalTimeInForeground() / 1000)));


            li.add(l);

        }

        return li;
    }*/

}
