package com.example.skyjar.dormitoryapp.CustomAdapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.skyjar.dormitoryapp.Entities.Apartment;
import com.example.skyjar.dormitoryapp.R;

import java.util.List;

public class ApartmentReportAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Apartment> apartmentList;
    private class ViewHolder {
        Spinner spnApartment;
        Spinner spnRoom;
        Spinner spnService;
        Spinner spnServiceItem;
        EditText edtDescription;
        Button btnSendReport;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder.spnApartment = convertView.findViewById(R.id.spnApartment);
            holder.spnRoom = convertView.findViewById(R.id.spnRoom);
            holder.spnService = convertView.findViewById(R.id.spnService);
            holder.spnServiceItem = convertView.findViewById(R.id.spnServiceItem);
            holder.edtDescription = convertView.findViewById(R.id.edtDescription);
            holder.btnSendReport = convertView.findViewById(R.id.btnSendReport);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


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

    public List<Apartment> getApartmentList() {
        return apartmentList;
    }

    public void setApartmentList(List<Apartment> apartmentList) {
        this.apartmentList = apartmentList;
    }

    public ApartmentReportAdapter(Context context, int layout, List<Apartment> apartmentList) {

        this.context = context;
        this.layout = layout;
        this.apartmentList = apartmentList;
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
