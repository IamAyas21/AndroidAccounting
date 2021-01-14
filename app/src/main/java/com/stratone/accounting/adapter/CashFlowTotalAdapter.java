package com.stratone.accounting.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stratone.accounting.R;
import com.stratone.accounting.model.CashFlowDetail;
import com.stratone.accounting.model.ProfitLossClass1;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CashFlowTotalAdapter extends ArrayAdapter<CashFlowDetail> {
    private Context mContext;
    private int mResource;

    public CashFlowTotalAdapter(@NonNull Context context, int resource, @NonNull List<CashFlowDetail> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource,parent,false);

        TextView titleId = convertView.findViewById(R.id.item_cash_flow_id_1);
        TextView titleEg = convertView.findViewById(R.id.item_cash_flow_eg_1);
        TextView titleAmt = convertView.findViewById(R.id.amount_cash_flow_1);

        titleId.setText(getItem(position).getName());
        titleEg.setText(getItem(position).getName2());
        titleAmt.setText(getItem(position).getValue());
        return convertView;
    }
}
