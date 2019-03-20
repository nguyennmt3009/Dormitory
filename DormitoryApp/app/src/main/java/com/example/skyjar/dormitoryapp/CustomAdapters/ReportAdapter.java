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

import com.example.skyjar.dormitoryapp.Entities.ProblemReport;
import com.example.skyjar.dormitoryapp.R;

import java.util.ArrayList;
import java.util.List;

public class ReportAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private int layout;
    private List<ProblemReport> problemReports;

    public ReportAdapter(Context context, int layout, List<ProblemReport> problemReports) {
        this.context = context;
        this.layout = layout;
        this.problemReports = problemReports;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                List<String> arrayListNames = (List<String>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();
                List<ProblemReport> FilteredArrayNames = new ArrayList<>();

                // perform your search here using the searchConstraint String.

                constraint = constraint.toString().toLowerCase();
                for (int i = 0; i < problemReports.size(); i++) {
                    String dataNames = problemReports.get(i).getStatusName();
                    if (dataNames.toLowerCase().equalsIgnoreCase(constraint.toString()))  {
                        FilteredArrayNames.add(problemReports.get(i));
                    }
                }

                results.count = FilteredArrayNames.size();
                results.values = FilteredArrayNames;
                Log.e("VALUES", results.values.toString());

                return results;
            }
        };

        return filter;
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

        switch (problemReport.getStatus()) {
            case 1:
                holder.txtStatus.setText(problemReport.getStatusName());
                holder.txtStatus.setTextColor(Color.parseColor("#f39c12"));
                break;
            case 2:
                holder.txtStatus.setText(problemReport.getStatusName());
                holder.txtStatus.setTextColor(Color.parseColor("#f39c12"));
                break;
            case 3:
                holder.txtStatus.setText(problemReport.getStatusName());
                holder.txtStatus.setTextColor(Color.parseColor("#00a65a"));
                break;
            case 7:
                holder.txtStatus.setText(problemReport.getStatusName());
                holder.txtStatus.setTextColor(Color.parseColor("#00a65a"));
                break;
            case 4:
                holder.txtStatus.setText(problemReport.getStatusName());
                holder.txtStatus.setTextColor(Color.parseColor("#dd4b39"));
                break;
            case 6:
                holder.txtStatus.setText(problemReport.getStatusName());
                holder.txtStatus.setTextColor(Color.parseColor("#dd4b39"));
                break;
            case 5:
                holder.txtStatus.setText(problemReport.getStatusName());
                holder.txtStatus.setTextColor(Color.parseColor("#b5b5b5"));
                break;
            default:
                    break;
        }

        holder.txtApartment.setText(problemReport.getApartmentName());
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
