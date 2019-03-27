package com.example.skyjar.dormitoryapp.CustomAdapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.skyjar.dormitoryapp.Entities.Transaction;
import com.example.skyjar.dormitoryapp.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TransactionAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Transaction.Trans> transactions;

    public TransactionAdapter() {
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

    public List<Transaction.Trans> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction.Trans> transactions) {
        this.transactions = transactions;
    }

    public TransactionAdapter(Context context, int layout, List<Transaction.Trans> transactions) {

        this.context = context;
        this.layout = layout;
        this.transactions = transactions;
    }

    @Override
    public int getCount() {
        return transactions.size();
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
        TextView txtTransName;
        TextView txtDate;
        TextView txtAmount;
    }

    private Date getDate(String mess, String pattern)
    {
        try
        {
            DateFormat df = new SimpleDateFormat(pattern);
            df.setLenient(false);

            return df.parse(mess);
        } catch (Exception e)
        {
        }
        return new Date(mess);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new TransactionAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder.txtTransName = convertView.findViewById(R.id.txtTransName);
            holder.txtDate = convertView.findViewById(R.id.txtDate);
            holder.txtAmount = convertView.findViewById(R.id.txtAmount);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Transaction.Trans transaction = transactions.get(position);

        if (!transaction.isIs_debit()) {
            holder.txtTransName.setText("Thanh toán hóa đơn: " + transaction.getBillId());
            holder.txtAmount.setText("-" + transaction.getAmount());
            holder.txtAmount.setTextColor(Color.rgb(255, 33, 33));
        } else {
            holder.txtTransName.setText("Nạp tiền vào tài khoản");
            holder.txtAmount.setText("+" + transaction.getAmount());
            holder.txtAmount.setTextColor(Color.rgb(49, 183, 34));
        }
        holder.txtDate.setText("Ngày: " + transaction.getDate());

        return convertView;
    }
}
