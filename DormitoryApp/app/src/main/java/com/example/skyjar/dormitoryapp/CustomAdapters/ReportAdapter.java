package com.example.skyjar.dormitoryapp.CustomAdapters;


import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.skyjar.dormitoryapp.Entities.Apartment;
import com.example.skyjar.dormitoryapp.Entities.ProblemReport;
import com.example.skyjar.dormitoryapp.Entities.ReportStatus;
import com.example.skyjar.dormitoryapp.R;

import java.util.ArrayList;
import java.util.List;

public class ReportAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ProblemReport> problemReports;

    public ReportAdapter(Context context, int layout, List<ProblemReport> problemReports) {
        this.context = context;
        this.layout = layout;
        this.problemReports = problemReports;
    }

    private class ViewHolder {
        TextView txtApartment;
        TextView txtRoom;
        TextView txtCreateDate;
        TextView txtStatus;
        TextView txtDesciption;
        TextView txtServiceItem;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder.txtRoom = convertView.findViewById(R.id.txtRoom);
            holder.txtCreateDate = convertView.findViewById(R.id.txtCreateDate);
            holder.txtStatus = convertView.findViewById(R.id.txtStatus);
            holder.txtApartment = convertView.findViewById(R.id.txtApartment);
            holder.txtDesciption = convertView.findViewById(R.id.txtDescription);
            holder.txtServiceItem = convertView.findViewById(R.id.txtServiceItem);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ProblemReport problemReport = problemReports.get(position);

        holder.txtRoom.setText("Phòng " + problemReport.getRoomName());
        holder.txtCreateDate.setText("Ngày tạo: " + problemReport.getCreateDate());
        int statusId = problemReport.getStatus().getId();
        String statusName = problemReport.getStatus().getName();

        switch (statusId) {
            case 1:
                holder.txtStatus.setText(statusName);
                holder.txtStatus.setTextColor(Color.parseColor("#f39c12"));
                break;
            case 2:
                holder.txtStatus.setText(statusName);
                holder.txtStatus.setTextColor(Color.parseColor("#f39c12"));
                break;
            case 3:
                holder.txtStatus.setText(statusName);
                holder.txtStatus.setTextColor(Color.parseColor("#00a65a"));
                break;
            case 7:
                holder.txtStatus.setText(statusName);
                holder.txtStatus.setTextColor(Color.parseColor("#00a65a"));
                break;
            case 4:
                holder.txtStatus.setText(statusName);
                holder.txtStatus.setTextColor(Color.parseColor("#dd4b39"));
                break;
            case 6:
                holder.txtStatus.setText(statusName);
                holder.txtStatus.setTextColor(Color.parseColor("#dd4b39"));
                break;
            case 5:
                holder.txtStatus.setText(statusName);
                holder.txtStatus.setTextColor(Color.parseColor("#b5b5b5"));
                break;
            default:
                    break;
        }

        holder.txtApartment.setText(problemReport.getApartment().getName());
        holder.txtServiceItem.setText("Về dịch vụ: " + problemReport.getServiceItemName());
        if (problemReport.getDescription().length() > 30)
            holder.txtDesciption.setText("Hiện tượng: " + problemReport.getDescription().substring(0, 25).toString() + "...");
        else
            holder.txtDesciption.setText("Hiện tượng: " + problemReport.getDescription());


        return convertView;
    }

    public Context getContext() {

        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public List<ProblemReport> getProblemReports() {
        return problemReports;
    }

    public void setProblemReports(List<ProblemReport> problemReports) {
        this.problemReports = problemReports;
    }

    @Override
    public int getCount() {
        return problemReports.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
