package com.example.skyjar.dormitoryapp.CustomAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.skyjar.dormitoryapp.Entities.ProblemReport;
import com.example.skyjar.dormitoryapp.R;

import java.util.List;

public class ReportAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ProblemReport> reportList;

    public ReportAdapter(Context context, int layout, List<ProblemReport> reportList) {
        this.context = context;
        this.layout = layout;
        this.reportList = reportList;
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

    public List<ProblemReport> getReportList() {
        return reportList;
    }

    public void setReportList(List<ProblemReport> reportList) {
        this.reportList = reportList;
    }

    @Override
    public int getCount() {
        return reportList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView txtType;
        TextView txtDate;
        TextView txtStatus;
        Button btnDetail;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder.txtStatus = convertView.findViewById(R.id.txtStatus);
            holder.txtType = convertView.findViewById(R.id.txtType);
            holder.txtDate = convertView.findViewById(R.id.txtDate);
            holder.btnDetail = convertView.findViewById(R.id.btnBillDetail);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ProblemReport report = reportList.get(position);

        holder.txtType.setText(report.getProblemType());
        android.text.format.DateFormat df = new android.text.format.DateFormat();
        holder.txtDate.setText("Ngày " + df.format("dd/MM/yyyy", report.getCreatedDate()).toString());
        holder.txtStatus.setText(report.isStatus()? "Đang chờ xử lý":"Hoàn tất");

        return convertView;
    }
}
