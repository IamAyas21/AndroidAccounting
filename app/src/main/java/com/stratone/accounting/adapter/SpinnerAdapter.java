package com.stratone.accounting.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.stratone.accounting.CashFlowFragment;
import com.stratone.accounting.LedgerFragment;
import com.stratone.accounting.R;
import com.stratone.accounting.model.StateModel;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class SpinnerAdapter extends ArrayAdapter<StateModel> {
    private String value;
    private Context mContext;
    private ArrayList<StateModel> listState;
    private SpinnerAdapter myAdapter;
    private boolean isFromView = false;

    public SpinnerAdapter(Context context, int resource, List<StateModel> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.listState = (ArrayList<StateModel>) objects;
        this.myAdapter = this;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(final int position, View convertView,
                              ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(mContext);
            convertView = layoutInflator.inflate(R.layout.spinner_item, null);
            holder = new ViewHolder();
            holder.mValueView = (TextView) convertView
                    .findViewById(R.id.value);
            holder.mTextView = (TextView) convertView
                    .findViewById(R.id.text);
            holder.mCheckBox = (CheckBox) convertView
                    .findViewById(R.id.checkbox);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTextView.setText(listState.get(position).getTitle());
        holder.mValueView.setText(listState.get(position).getValue());

        // To check weather checked event fire from getview() or user input
        isFromView = true;
        holder.mCheckBox.setChecked(listState.get(position).isSelected());
        isFromView = false;

        if ((position == 0)) {
            holder.mCheckBox.setVisibility(View.INVISIBLE);
        } else {
            holder.mCheckBox.setVisibility(View.VISIBLE);
        }
        holder.mCheckBox.setTag(position);

        final View finalConvertView = convertView;
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int getPosition = (Integer) buttonView.getTag();

                if (!isFromView) {
                    listState.get(position).setSelected(isChecked);
                    if(holder.mValueView.getText().toString().isEmpty())
                    {
                        holder.mValueView.setText("'" +listState.get(position).getValue()+"'");
                    }
                    else{
                       value = (String) holder.mValueView.getText();
                        holder.mValueView.setText(value+",'" +listState.get(position).getValue()+"'");
                    }

                    LedgerFragment.newInstance(holder.mValueView.getText().toString());
                }
            }
        });
        return convertView;
    }

    private class ViewHolder {
        private TextView mValueView;
        private TextView mTextView;
        private CheckBox mCheckBox;
    }
}
