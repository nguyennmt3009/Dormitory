package com.example.skyjar.dormitoryapp.CustomAdapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.skyjar.dormitoryapp.Entities.BillDetail;
import com.example.skyjar.dormitoryapp.R;

import java.util.List;

public class BillDetailAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<BillDetail> billDetails;

    public BillDetailAdapter(Context context, int layout, List<BillDetail> billDetails) {
        this.context = context;
        this.layout = layout;
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
        TextView txtMonth;
        TextView txtRoom;
        TextView txtAmount;
        TextView txtApartment;
        TextView txtStatus;
        TextView txtDate;
        Button btnDetail;
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
            holder.btnDetail = convertView.findViewById(R.id.btnBillDetail);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        BillDetail bill = billDetails.get(position);

        android.text.format.DateFormat df = new android.text.format.DateFormat();
        holder.txtDate.setText(df.format("dd/MM/yyyy", bill.getCreatedDate()).toString());
        if(bill.isStatus()) {
            holder.txtStatus.setText("Đã thanh toán");
        } else {
            holder.txtStatus.setText("Chưa thanh toán");
        }
        holder.txtRoom.setText(bill.getRoom());
        holder.txtAmount.setText("Số tiền: " + bill.getAmount() + " đồng");
        holder.txtApartment.setText(bill.getApartment());
        holder.txtMonth.setText(bill.getMonth());

        return convertView;
    }
}
