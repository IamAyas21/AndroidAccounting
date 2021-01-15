package com.stratone.accounting.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stratone.accounting.ProfitLossClass2Fragment;
import com.stratone.accounting.ProfitLossClass3Fragment;
import com.stratone.accounting.R;
import com.stratone.accounting.model.CashFlowDetail;
import com.stratone.accounting.model.CashFlowHeader;
import com.stratone.accounting.model.ProfitLossClass2;
import com.stratone.accounting.model.ProfitLossClass3;

import java.util.HashMap;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class ProfitClass3Adapter extends BaseExpandableListAdapter{
    private Context context;
    private List<ProfitLossClass3> expandableListTitle;
    private HashMap<ProfitLossClass3, List<ProfitLossClass2>> expandableListDetail;
    private String sDate;
    private String eDate;
    private String aClassId;
    private String page;
    public ProfitClass3Adapter(Context context, List<ProfitLossClass3> expandableListTitle
                            , HashMap<ProfitLossClass3, List<ProfitLossClass2>> expandableListDetail
                            , String startDate, String endDate, String aClassId, String page) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
        this.sDate = startDate;
        this.eDate = endDate;
        this.aClassId = aClassId;
        this.page = page;
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
        final ProfitLossClass2 expandedListText = (ProfitLossClass2) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_row_cash_flow_detail, null);
        }

        LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.ll_title);
        TextView textViewCashFlowId = (TextView) convertView.findViewById(R.id.item_cash_flow_id_1);
        TextView textViewCashFlowEg = (TextView) convertView.findViewById(R.id.item_cash_flow_eg_1);
        TextView textViewAmtCashFlow = (TextView) convertView.findViewById(R.id.amount_cash_flow_1);

        textViewCashFlowId.setText(expandedListText.getTitleId());
        textViewCashFlowEg.setText(expandedListText.getTitleEg());
        textViewAmtCashFlow.setText(expandedListText.getAmount());
        textViewAmtCashFlow.setText(expandedListText.getAmount().replace("(","").replace(")",""));

        if(page == "2")
        {
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("aClassId", aClassId);
                    bundle.putString("bClassId", expandedListText.getClassId());
                    bundle.putString("startDate", sDate);
                    bundle.putString("endDate", eDate);
                    ProfitLossClass3Fragment profitLossClass3Fragment = new ProfitLossClass3Fragment();
                    profitLossClass3Fragment.setArguments(bundle);

                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,profitLossClass3Fragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
                }
            });
        }

        if(expandedListText.getAmount().contains("("))
        {
            textViewCashFlowId.setTextColor(convertView.getResources().getColor(R.color.design_default_color_error));
            textViewCashFlowEg.setTextColor(convertView.getResources().getColor(R.color.design_default_color_error));
            textViewAmtCashFlow.setTextColor(convertView.getResources().getColor(R.color.design_default_color_error));
        }
        else{
            textViewCashFlowId.setTextColor(convertView.getResources().getColor(R.color.colorPrimary));
            textViewCashFlowEg.setTextColor(convertView.getResources().getColor(R.color.colorPrimary));
            textViewAmtCashFlow.setTextColor(convertView.getResources().getColor(R.color.colorPrimary));
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
        ProfitLossClass3 listTitle = (ProfitLossClass3) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_row_cash_flow, null);
        }

        TextView textViewCashFlowId = (TextView) convertView.findViewById(R.id.item_cash_flow_id);
        TextView textViewCashFlowEg = (TextView) convertView.findViewById(R.id.item_cash_flow_eg);
        TextView textViewAmtCashFlow = (TextView) convertView.findViewById(R.id.amount_cash_flow);

        if(listTitle.getAmount() != null)
        {
            if(listTitle.getAmount().contains("("))
            {
                textViewCashFlowId.setTextColor(convertView.getResources().getColor(R.color.design_default_color_error));
                textViewCashFlowEg.setTextColor(convertView.getResources().getColor(R.color.design_default_color_error));
                textViewAmtCashFlow.setTextColor(convertView.getResources().getColor(R.color.design_default_color_error));
            }
            else{
                textViewCashFlowId.setTextColor(convertView.getResources().getColor(R.color.colorPrimary));
                textViewCashFlowEg.setTextColor(convertView.getResources().getColor(R.color.colorPrimary));
                textViewAmtCashFlow.setTextColor(convertView.getResources().getColor(R.color.colorPrimary));
            }

            textViewAmtCashFlow.setText(listTitle.getAmount().replace("(","").replace(")",""));
        }

        textViewCashFlowId.setText(listTitle.getTitleId());
        textViewCashFlowEg.setText(listTitle.getTitleEg());
        textViewAmtCashFlow.setVisibility(View.GONE);
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
