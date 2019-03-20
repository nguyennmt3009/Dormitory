package com.example.skyjar.dormitoryapp.CustomAdapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.skyjar.dormitoryapp.Entities.Contract;
import com.example.skyjar.dormitoryapp.R;

import java.util.List;

public class ContractAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Contract> contractList;

    private class ViewHolder {
        TextView txtRoom;
        TextView txtOwner;
        TextView txtApartment;
        TextView txtDueDate;
        Button btnContractDetail;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder.txtRoom = convertView.findViewById(R.id.txtRoom);
            holder.txtDueDate = convertView.findViewById(R.id.txtDueDate);
            holder.txtOwner = convertView.findViewById(R.id.txtOwner);
            holder.txtApartment = convertView.findViewById(R.id.txtApartment);
            holder.btnContractDetail = convertView.findViewById(R.id.btnContractDetail);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Contract contract = contractList.get(position);

        holder.txtDueDate.setText("Thời hạn: " + contract.getDueDate());
        holder.txtOwner.setText("Đại diện: " + contract.getOwnerName());
        holder.txtApartment.setText(contract.getApartmentName());
        holder.txtRoom.setText(contract.getRoomName());
        holder.btnContractDetail.setId(contract.getContractId());

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

    public List<Contract> getContractList() {
        return contractList;
    }

    public void setContractList(List<Contract> contractList) {
        this.contractList = contractList;
    }

    public ContractAdapter(Context context, int layout, List<Contract> contractList) {

        this.context = context;
        this.layout = layout;
        this.contractList = contractList;
    }

    @Override
    public int getCount() {
        return contractList.size();
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
