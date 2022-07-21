package com.example.wifitest;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class wifiAdapter extends RecyclerView.Adapter<wifiAdapter.MyViewHolder>

{

    private List<ScanResult> items;
    private Context mContext;

    public wifiAdapter(List<ScanResult> items)
    {

        this.items=items;
    }

    @NonNull
    @Override
    public wifiAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.wifi_item , parent, false);

        mContext = parent.getContext();

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        holder.setItem(items.get(position));
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView tvWifiName;
        public MyViewHolder(View itemView)
        {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION)
                    {
                        // click event
                        // ssid 저장
                        String ssid = items.get(pos).SSID;

                        // pw 입력 다이얼로그를 호출한다.
                        // 입력된 pw을 저장한다.
                        wifiDialog customDialog = new wifiDialog(mContext);
                        customDialog.callFunction(ssid);
                    }
                }
            });

            tvWifiName=itemView.findViewById(R.id.tv_wifiName);
        }

        public void setItem(ScanResult item)

        {
            tvWifiName.setText(item.SSID);

        }

    }

}