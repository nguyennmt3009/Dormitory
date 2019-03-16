package com.example.skyjar.dormitoryapp.CustomAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.skyjar.dormitoryapp.Entities.BillDetail;
import com.example.skyjar.dormitoryapp.R;

import java.util.List;

public class BillDetailAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<BillDetail> billDetails;


    public BillDetailAdapter(Context context, List<BillDetail> billDetails) {
        this.context = context;
        this.billDetails = billDetails;
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

    public List<BillDetail> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(List<BillDetail> billDetails) {
        this.billDetails = billDetails;
    }

    @Override
    public int getCount() {
        return billDetails.size();
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
        TextView txtServiceName;
        TextView txtQuantity;
        TextView txtPrice;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_service, null);

            holder.txtPrice = convertView.findViewById(R.id.txtServicePrice);
            holder.txtQuantity = convertView.findViewById(R.id.txtServiceQuantity);
            holder.txtServiceName = convertView.findViewById(R.id.txtServiceName);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        BillDetail service = billDetails.get(position);

        holder.txtServiceName.setText(service.getName());
        holder.txtQuantity.setText(service.getQuantity() + "");
        holder.txtPrice.setText(service.getPrice() + "");

        return convertView;
    }
}
