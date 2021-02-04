package com.stratone.accounting.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.stratone.accounting.R;
import com.stratone.accounting.model.CashFlowDetail;
import com.stratone.accounting.model.CashFlowHeader;
import com.stratone.accounting.model.LedgerAccountModel;
import com.stratone.accounting.model.LedgerModel;

import java.util.HashMap;
import java.util.List;

public class LedgerAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<LedgerAccountModel> expandableListTitle;
    private HashMap<LedgerAccountModel, List<LedgerModel>> expandableListDetail;

    public LedgerAdapter(Context context, List<LedgerAccountModel> expandableListTitle,
                           HashMap<LedgerAccountModel, List<LedgerModel>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final LedgerModel expandedListText = (LedgerModel) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_row_ledger_detail, null);
        }

        TextView dateLedger = (TextView) convertView.findViewById(R.id.tv_date_ledger);
        TextView voucherLedger = (TextView) convertView.findViewById(R.id.tv_voucher_ledger);
        TextView descLedger = (TextView) convertView.findViewById(R.id.tv_desc_ledger);
        TextView debetLedger = (TextView) convertView.findViewById(R.id.tv_debet_ledger);
        TextView creditLedger = (TextView) convertView.findViewById(R.id.tv_credit_ledger);
        TextView balanceLedger = (TextView) convertView.findViewById(R.id.tv_balance_ledger);

        dateLedger.setText(expandedListText.getDate());
        voucherLedger.setText(expandedListText.getVoucher());
        descLedger.setText(expandedListText.getDesc());
        debetLedger.setText(expandedListText.getDebet().replace("(","").replace(")",""));
        creditLedger.setText(expandedListText.getCredit().replace("(","").replace(")",""));
        balanceLedger.setText(expandedListText.getBalance().replace("(","").replace(")",""));

        if(expandedListText.getDesc().contains("Saldo Awal"))
        {
            dateLedger.setVisibility(View.GONE);
            voucherLedger.setVisibility(View.GONE);
            debetLedger.setVisibility(View.GONE);
            creditLedger.setVisibility(View.GONE);
            balanceLedger.setTextColor(convertView.getResources().getColor(R.color.design_default_color_error));
        }

        if(expandedListText != null){
            if(expandedListText.getDebet().contains("("))
            {
                debetLedger.setTextColor(convertView.getResources().getColor(R.color.design_default_color_error));
            }
            else{
                debetLedger.setTextColor(convertView.getResources().getColor(R.color.colorPrimary));
            }

            if(expandedListText.getCredit().contains("("))
            {
                creditLedger.setTextColor(convertView.getResources().getColor(R.color.design_default_color_error));
            }
            else{
                creditLedger.setTextColor(convertView.getResources().getColor(R.color.colorPrimary));
            }
        }

        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        LedgerAccountModel listTitle = (LedgerAccountModel) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_row_ledger, null);
        }

        TextView headerAcc = (TextView) convertView.findViewById(R.id.tv_header_acc);
        TextView headerDebet = (TextView) convertView.findViewById(R.id.tv_header_debet);
        TextView headerCredit = (TextView) convertView.findViewById(R.id.tv_header_credit);
        TextView headerBalance = (TextView) convertView.findViewById(R.id.tv_hader_balance);
        TextView headerCount = (TextView) convertView.findViewById(R.id.tv_count_rows);

        if(listTitle.getDebetMutation() != null)
        {
            headerDebet.setText(listTitle.getDebetMutation().replace("(","").replace(")",""));
            if(listTitle.getDebetMutation().contains("("))
            {
                headerDebet.setTextColor(convertView.getResources().getColor(R.color.design_default_color_error));
            }
            else{
                headerDebet.setTextColor(convertView.getResources().getColor(R.color.colorPrimary));
            }
        }

        if(listTitle.getCreditMutation() != null)
        {
            headerCredit.setText(listTitle.getCreditMutation().replace("(","").replace(")",""));
            if(listTitle.getCreditMutation().contains("("))
            {
                headerCredit.setTextColor(convertView.getResources().getColor(R.color.design_default_color_error));
            }
            else{
                headerCredit.setTextColor(convertView.getResources().getColor(R.color.colorPrimary));
            }
        }

        if(listTitle.getEndingBalance() != null)
        {
            headerBalance.setText(listTitle.getEndingBalance().replace("(","").replace(")",""));
            if(listTitle.getEndingBalance().contains("("))
            {
                headerBalance.setTextColor(convertView.getResources().getColor(R.color.design_default_color_error));
            }
            else{
                headerBalance.setTextColor(convertView.getResources().getColor(R.color.colorPrimary));
            }
        }

        headerAcc.setText(listTitle.getName()+"("+listTitle.getNo()+")");
        headerCount.setText(String.valueOf(listTitle.getMutations().size()) + " Records");

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}
