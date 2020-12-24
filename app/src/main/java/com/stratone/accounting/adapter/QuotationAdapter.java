package com.stratone.accounting.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.stratone.accounting.R;
import com.stratone.accounting.model.Quotation;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class QuotationAdapter extends ArrayAdapter<Quotation> {
    private Context mContext;
    private int mResource;

    public QuotationAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Quotation> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource,parent,false);
        TextView itemQuotation = convertView.findViewById(R.id.item_quotation);
        TextView itemDescription = convertView.findViewById(R.id.item_description);
        itemQuotation.setText(getItem(position).getItemQuotation());
        itemDescription.setText(getItem(position).getDescription());
        return convertView;
    }
}
