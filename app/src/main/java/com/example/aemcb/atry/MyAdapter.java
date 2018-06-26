package com.example.aemcb.atry;

import android.app.usage.UsageStats;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.*;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<UsageStats> li;
    private Context context;

    public MyAdapter(List<UsageStats> li, Context context) {
        this.li = li;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {


        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.listitems,parent,false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int i) {

        final PackageManager pm=context.getPackageManager();

        ApplicationInfo ai;
        try {
            ai = pm.getApplicationInfo( li.get(i).getPackageName(), 0);
        } catch (final PackageManager.NameNotFoundException e) {
            ai = null;
        }

        final String applicationName = (String) (ai != null ? pm.getApplicationLabel(ai) : "(unknown)");

        Drawable appIcon=context.getDrawable(R.drawable.ic_home_black_24dp);

        try {
             appIcon= context.getPackageManager()
                    .getApplicationIcon(li.get(i).getPackageName());
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        li.get(i).getPackageName().toString();

        holder.title.setText("  "+applicationName);

        holder.usage.setText((String.valueOf(((li.get(i).getTotalTimeInForeground()/1000)))));

        //holder.first_used.setText((DateFormat.format(" HH:mm:ss EEE  z yyyy",new Date(li.get(i).getFirstTimeStamp()))));

        //holder.last_used.setText(getDate(li.get(i).getLastTimeStamp()));

        holder.icon.setImageDrawable(appIcon);


    }

    private String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time);
        String date = DateFormat.format("dd-MM-yyyy", cal).toString();
        return date;
    }
    @Override
    public int getItemCount() {
        return li.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView usage;
        /*public TextView last;
        public TextView first_used;
        public TextView last_used; */
        public TextView title;
        public ImageView icon;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

           title = (TextView) itemView.findViewById(R.id.tv_title);
            usage = (TextView) itemView.findViewById(R.id.tv_usage);
           // last=(TextView) itemView.findViewById(R.id.last_used);
           // first_used=(TextView)itemView.findViewById(R.id.first_used);
            //last_used=itemView.findViewById(R.id.last_used);
            icon=itemView.findViewById(R.id.icon);
        }

    }
}
