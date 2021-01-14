package com.stratone.accounting.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stratone.accounting.CashFlowFragment;
import com.stratone.accounting.R;
import com.stratone.accounting.model.CashFlow;
import com.stratone.accounting.model.CashFlowDetail;
import com.stratone.accounting.model.CashFlowHeader;
import com.stratone.accounting.model.CashFlowModel;
import com.stratone.accounting.model.Output;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class CashFlowAdapter extends BaseExpandableListAdapter{
    private Context context;
    private List<CashFlowHeader> expandableListTitle;
    private HashMap<CashFlowHeader, List<CashFlowDetail>> expandableListDetail;

    public CashFlowAdapter(Context context, List<CashFlowHeader> expandableListTitle,
                                       HashMap<CashFlowHeader, List<CashFlowDetail>> expandableListDetail) {
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
        final CashFlowDetail expandedListText = (CashFlowDetail) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_row_cash_flow_detail, null);
        }

        TextView textViewCashFlowId = (TextView) convertView.findViewById(R.id.item_cash_flow_id_1);
        TextView textViewCashFlowEg = (TextView) convertView.findViewById(R.id.item_cash_flow_eg_1);
        TextView textViewAmtCashFlow = (TextView) convertView.findViewById(R.id.amount_cash_flow_1);

        textViewCashFlowId.setText(expandedListText.getName());
        textViewCashFlowEg.setText(expandedListText.getName2());
        textViewAmtCashFlow.setText(expandedListText.getValue());

        if(expandedListText.getValue().contains("("))
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
        CashFlowHeader listTitle = (CashFlowHeader) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_row_cash_flow, null);
        }

        TextView textViewCashFlowId = (TextView) convertView.findViewById(R.id.item_cash_flow_id);
        TextView textViewCashFlowEg = (TextView) convertView.findViewById(R.id.item_cash_flow_eg);
        TextView textViewAmtCashFlow = (TextView) convertView.findViewById(R.id.amount_cash_flow);

        if(listTitle.getSum().contains("("))
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

        textViewCashFlowId.setText(listTitle.getGroup());
        textViewCashFlowEg.setText(listTitle.getGroup2());
        textViewAmtCashFlow.setText(listTitle.getSum().replace("(","").replace(")",""));

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
