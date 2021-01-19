package com.stratone.accounting.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stratone.accounting.NeracaClass2Fragment;
import com.stratone.accounting.ProfitLossClass2Fragment;
import com.stratone.accounting.R;
import com.stratone.accounting.model.NeracaClass1;
import com.stratone.accounting.model.ProfitLossClass1;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class NeracaClass1Adapter extends ArrayAdapter<NeracaClass1> {
    private Context mContext;
    private int mResource;
    private String sDate;
    private String eDate;
    public NeracaClass1Adapter(@NonNull Context context, int resource, @NonNull List<NeracaClass1> objects,
                               String startDate, String endDate) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
        this.sDate = startDate;
        this.eDate = endDate;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource,parent,false);

        LinearLayout linearLayout = convertView.findViewById(R.id.ll_class1);
        TextView titleId = convertView.findViewById(R.id.item_class1_id);
        TextView titleEg = convertView.findViewById(R.id.item_class1_eg);
        TextView titleAmt = convertView.findViewById(R.id.amount_class1);

        if(getItem(position).getAmount().contains("("))
        {
            titleId.setTextColor(convertView.getResources().getColor(R.color.design_default_color_error));
            titleEg.setTextColor(convertView.getResources().getColor(R.color.design_default_color_error));
            titleAmt.setTextColor(convertView.getResources().getColor(R.color.design_default_color_error));
        }
        else{
            titleId.setTextColor(convertView.getResources().getColor(R.color.colorPrimary));
            titleEg.setTextColor(convertView.getResources().getColor(R.color.colorPrimary));
            titleAmt.setTextColor(convertView.getResources().getColor(R.color.colorPrimary));
        }

        titleId.setText(getItem(position).getTitleId());
        titleEg.setText(getItem(position).getTitleEg());
        titleAmt.setText(getItem(position).getAmount().replace("(","").replace(")",""));

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putString("aClassId", getItem(position).getClassId());
                bundle.putString("startDate", sDate);
                bundle.putString("endDate", eDate);
                bundle.putString("page", "1");
                NeracaClass2Fragment neracaClass2Fragment = new NeracaClass2Fragment();
                neracaClass2Fragment.setArguments(bundle);
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,neracaClass2Fragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
            }
        });
        return convertView;
    }
}
