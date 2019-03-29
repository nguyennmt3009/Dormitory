package com.example.skyjar.dormitoryapp.CustomAdapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.skyjar.dormitoryapp.Entities.Bill;
import com.example.skyjar.dormitoryapp.R;

import java.util.List;

public class BillAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Bill> bills;

    public BillAdapter(Context context, int layout, List<Bill> bills) {
        this.context = context;
        this.layout = layout;
        this.bills = bills;
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

    public List<Bill> getBillDetails() {
        return bills;
    }

    public void setBillDetails(List<Bill> billDetails) {
        this.bills = billDetails;
    }

    @Override
    public int getCount() {
        return bills.size();
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
        TextView txtMonth;
        TextView txtRoom;
        TextView txtAmount;
        TextView txtApartment;
        TextView txtStatus;
        TextView txtDate;
        LinearLayout llBillDetail;
//        Button btnDetail;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder.txtMonth = convertView.findViewById(R.id.txtMonth);
            holder.txtAmount = convertView.findViewById(R.id.txtAmount);
            holder.txtRoom = convertView.findViewById(R.id.txtRoom);
            holder.txtApartment = convertView.findViewById(R.id.txtApartment);
            holder.txtStatus = convertView.findViewById(R.id.txtStatus);
            holder.txtDate = convertView.findViewById(R.id.txtDate);
//            holder.btnDetail = convertView.findViewById(R.id.btnBillDetail);
            holder.llBillDetail = convertView.findViewById(R.id.llBillDetail);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Bill bill = bills.get(position);

        holder.txtDate.setText(bill.getCreatedDate());
        holder.txtStatus.setText(bill.getStatus());
        if(bill.getStatus().contains("Đã"))
            holder.txtStatus.setTextColor(Color.rgb(49, 183, 34));
        else
            holder.txtStatus.setTextColor(Color.rgb(255, 33, 33));
        holder.txtRoom.setText(bill.getRoomName());
        holder.txtAmount.setText(String.format("%,.0f",bill.getAmount()) + "");
        holder.txtApartment.setText(bill.getApartmentName());
        holder.txtMonth.setText(bill.getMonth());
//        holder.btnDetail.setId(bill.getId());
        holder.llBillDetail.setId(bill.getId());

        return convertView;
    }
}
