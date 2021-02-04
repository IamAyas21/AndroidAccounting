package com.stratone.accounting.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stratone.accounting.CashFlowFragment;
import com.stratone.accounting.LedgerFragment;
import com.stratone.accounting.NeracaClass1Fragment;
import com.stratone.accounting.ProfitLossClass1Fragment;
import com.stratone.accounting.R;
import com.stratone.accounting.RebFragment;
import com.stratone.accounting.TrialBalanceFragment;
import com.stratone.accounting.model.Output;
import com.stratone.accounting.model.ProfitLossClass1;
import com.stratone.accounting.model.Quotation;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class OutputDashboardAdapter extends ArrayAdapter<Output> {
    private Context mContext;
    private int mResource;

    public OutputDashboardAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Output> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource,parent,false);
        ImageView itemImage = convertView.findViewById(R.id.image_output_dashboard);
        TextView itemMenu = convertView.findViewById(R.id.item_output_dashboard);
        LinearLayout llItemMenu = convertView.findViewById(R.id.ll_item_menu);

        itemImage.setImageResource(getItem(position).getImageMenuOutput());
        itemMenu.setText(getItem(position).getItemMenuOutput());

        llItemMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getItem(position).getItemMenuOutput().equals("Cash Flow"))
                {
                    CashFlowFragment cashFlowFragment = new CashFlowFragment();
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,cashFlowFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();

                }
                else if(getItem(position).getItemMenuOutput().equals("Retained Earning"))
                {
                    RebFragment rebFragment = new RebFragment();
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,rebFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();

                }
                else if(getItem(position).getItemMenuOutput().equals("Income Statement"))
                {
                    ProfitLossClass1Fragment profitLossClass1Fragment = new ProfitLossClass1Fragment();
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,profitLossClass1Fragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();

                }
                else if(getItem(position).getItemMenuOutput().equals("Financial Position"))
                {
                    NeracaClass1Fragment neracaClass1Fragment = new NeracaClass1Fragment();
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,neracaClass1Fragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();

                }
                else if(getItem(position).getItemMenuOutput().equals("Trial Balance"))
                {
                    TrialBalanceFragment trialBalanceFragment = new TrialBalanceFragment();
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,trialBalanceFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();

                }
                else if(getItem(position).getItemMenuOutput().equals("General Ledger"))
                {
                    LedgerFragment ledgerFragment = new LedgerFragment();
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,ledgerFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();

                }
            }
        });
        return convertView;
    }
}
