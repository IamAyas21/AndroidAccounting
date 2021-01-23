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
import com.stratone.accounting.NeracaClass1Fragment;
import com.stratone.accounting.ProfitLossClass1Fragment;
import com.stratone.accounting.R;
import com.stratone.accounting.RebFragment;
import com.stratone.accounting.model.Output;
import com.stratone.accounting.model.TrialBalance;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class TrialBalanceAdapter extends ArrayAdapter<TrialBalance> {
    private Context mContext;
    private int mResource;

    public TrialBalanceAdapter(@NonNull Context context, int resource, @NonNull List<TrialBalance> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource,parent,false);
        TextView accNo = convertView.findViewById(R.id.tv_acc_no);
        TextView accName = convertView.findViewById(R.id.tv_acc_name);
        TextView initialDebit = convertView.findViewById(R.id.tv_debit_initial);
        TextView initialCredit = convertView.findViewById(R.id.tv_credit_initial);
        TextView mutationDebit = convertView.findViewById(R.id.tv_debit_mutation);
        TextView mutationCredit = convertView.findViewById(R.id.tv_credit_mutation);
        TextView endingDebit = convertView.findViewById(R.id.tv_debit_ending);
        TextView endingCredit = convertView.findViewById(R.id.tv_credit_ending);

        accNo.setText(getItem(position).getNo());
        accName.setText(getItem(position).getName());
        initialDebit.setText(getItem(position).getInitialDebet());
        initialCredit.setText(getItem(position).getInitialCredit());
        mutationDebit.setText(getItem(position).getMutationDebet());
        mutationCredit.setText(getItem(position).getMutationCredit());
        endingDebit.setText(getItem(position).getEndingDebet());
        endingCredit.setText(getItem(position).getEndingCredit());

        return convertView;
    }
}
