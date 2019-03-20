package com.example.skyjar.dormitoryapp.CustomAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.skyjar.dormitoryapp.Entities.Contract;
import com.example.skyjar.dormitoryapp.R;

import java.util.List;

public class ContractDetailAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Contract> contracts;

    public Context getContext() {
        return context;
    }

    public List<Contract> getContract() {
        return contracts;
    }

    public void setContract(List<Contract> contracts) {
        this.contracts = contracts;
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

    public ContractDetailAdapter(Context context, int layout, List<Contract> contracts) {

        this.context = context;
        this.layout = layout;
        this.contracts = contracts;
    }

    private class ViewHolder {
        TextView txtContractId;
        TextView txtBrandName;
        TextView txtApartment;
        TextView txtRoom;
        TextView txtOwner;
        TextView txtListName;
        TextView txtDate;
        TextView txtDueDate;
        TextView txtDeposit;
        TextView txtMonthlyFee;
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
            holder.txtContractId = convertView.findViewById(R.id.txtContractId);
            holder.txtBrandName = convertView.findViewById(R.id.txtBrandName);
            holder.txtListName = convertView.findViewById(R.id.txtListName);
            holder.txtDate = convertView.findViewById(R.id.txtDate);
            holder.txtDeposit = convertView.findViewById(R.id.txtDeposit);
            holder.txtMonthlyFee = convertView.findViewById(R.id.txtMonthlyFee);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Contract contract = contracts.get(position);

        holder.txtRoom.setText(contract.getRoomName());
        holder.txtDueDate.setText(contract.getCreateDate());
        holder.txtOwner.setText(contract.getOwnerName());
        holder.txtApartment.setText(contract.getApartmentName());
        holder.txtContractId.setText(contract.getContractId());
        holder.txtBrandName.setText(contract.getBrandName());
        List<String> strArr = contract.getListCustomerName();
        String str = "";
        for (String s : strArr)
            str += ("\t" + s + "\n");
        holder.txtListName.setText(str);
        holder.txtDate.setText(contract.getCreateDate());
        holder.txtDeposit.setText(contract.getDeposit());
        holder.txtMonthlyFee.setText(contract.getMonthlyFee());

        return convertView;
    }

    @Override
    public int getCount() {
        return 0;
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
