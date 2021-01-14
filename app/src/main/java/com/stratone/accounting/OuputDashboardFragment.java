package com.stratone.accounting;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.stratone.accounting.adapter.OutputDashboardAdapter;
import com.stratone.accounting.adapter.QuotationAdapter;
import com.stratone.accounting.model.Output;
import com.stratone.accounting.model.Quotation;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OuputDashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OuputDashboardFragment extends Fragment {
    private HomeFragment homeFragment;
    private ListView listView_output_dashboard;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OuputDashboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OuputDashboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OuputDashboardFragment newInstance(String param1, String param2) {
        OuputDashboardFragment fragment = new OuputDashboardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                homeFragment = new HomeFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,homeFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_ouput_dashboard, container, false);
        listView_output_dashboard = (ListView)rootView.findViewById(R.id.listView_output_dashboard);

        ArrayList<Output> outputs = new ArrayList<>();
        outputs.add(new Output("General Ledger",R.drawable.icon_general_ledger_hover));
        outputs.add(new Output("Trial Balance",R.drawable.icon_trial_balance_hover));
        outputs.add(new Output("Financial Position",R.drawable.icon_financial_position_hover));
        outputs.add(new Output("Income Statement",R.drawable.icon_income_statement_hover));
        outputs.add(new Output("Cost Of Production",R.drawable.icon_cost_of_production_hover));
        outputs.add(new Output("Retained Earning",R.drawable.icon_retained_earning_hover));
        outputs.add(new Output("Cash Flow",R.drawable.icon_cash_flow_hover));

        OutputDashboardAdapter adapter = new OutputDashboardAdapter(getActivity(),R.layout.list_row_output_dashboard,outputs);
        listView_output_dashboard.setAdapter(adapter);

        return rootView;
    }
}