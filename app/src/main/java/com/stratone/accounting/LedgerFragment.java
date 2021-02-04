package com.stratone.accounting;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.stratone.accounting.adapter.CashFlowAdapter;
import com.stratone.accounting.adapter.CashFlowDetailAdapter;
import com.stratone.accounting.adapter.CashFlowTotalAdapter;
import com.stratone.accounting.adapter.LedgerAdapter;
import com.stratone.accounting.adapter.LedgerDetailAdapter;
import com.stratone.accounting.adapter.SpinnerAdapter;
import com.stratone.accounting.model.CashFlowDetail;
import com.stratone.accounting.model.CashFlowHeader;
import com.stratone.accounting.model.LedgerAccountModel;
import com.stratone.accounting.model.LedgerDataModel;
import com.stratone.accounting.model.LedgerModel;
import com.stratone.accounting.model.StateModel;
import com.stratone.accounting.response.ResponseCOA;
import com.stratone.accounting.response.ResponseCashFlow;
import com.stratone.accounting.response.ResponseCashFlowTotal;
import com.stratone.accounting.response.ResponseLedger;
import com.stratone.accounting.rest.ApiClient;
import com.stratone.accounting.rest.ApiInterface;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LedgerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LedgerFragment extends Fragment {
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    LedgerDataModel ledgerDataModel;
    List<LedgerAccountModel> expandableListTitle;
    List<LedgerModel> ledgerModels;
    HashMap<LedgerAccountModel, List<LedgerModel>> expandableListDetail;
    ArrayList<Integer> mItems = new ArrayList<>();
    ArrayList<StateModel> arrStateModel;
    String[] listItems;
    
    private ApiInterface apiService;
    private EditText startDate;
    private EditText endDate;
    private Button btnGo;
    private Spinner spinner;
    private String value;
    private Button filterAcc;

    final Calendar myCalendar = Calendar.getInstance();
    private DatePickerDialog startDatePicker;
    private DatePickerDialog endDatePicker;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "spinnerValue";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private boolean[] checkedItems;

    public LedgerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     */
    // TODO: Rename and change types and number of parameters
    public static LedgerFragment newInstance(String value) {
        LedgerFragment fragment = new LedgerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, value);
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
                OuputDashboardFragment ouputDashboardFragment = new OuputDashboardFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,ouputDashboardFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_ledger, container, false);
        startDate = (EditText)rootView.findViewById(R.id.start_date);
        endDate = (EditText)rootView.findViewById(R.id.end_date);
        expandableListView = (ExpandableListView)rootView.findViewById(R.id.expandable);
        btnGo = (Button)rootView.findViewById(R.id.btn_go);
        filterAcc = (Button)rootView.findViewById(R.id.filter_acc);
        /*spinner = (Spinner) rootView.findViewById(R.id.spinner);*/

        value = "";
        startDatePicker = new DatePickerDialog(getActivity(), R.style.DatePickerDialog,
                new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        updateStartDate();
                    }
                }, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDatePicker.show();
            }
        });

        endDatePicker = new DatePickerDialog(getActivity(), R.style.DatePickerDialog,
                new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        updateEndDate();
                    }
                }, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));

        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endDatePicker.show();
            }
        });

        if(startDate.getText().toString().equals("") && endDate.getText().toString().equals(""))
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String date = dateFormat.format(myCalendar.getTime());
            startDate.setText(date);
            endDate.setText(date);
        }

        apiService = ApiClient.getClient().create(ApiInterface.class);
        arrStateModel = ListCOA("");

        ListLedger("",startDate.getText().toString(),endDate.getText().toString(),"");

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*apiService = ApiClient.getClient().create(ApiInterface.class);*/

                ListLedger("",startDate.getText().toString(),endDate.getText().toString(),value);
            }
        });

        filterAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                mBuilder.setTitle("Choose Account");
                mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if(isChecked)
                        {
                            if(position == 0)
                            {
                                ListView list = ((AlertDialog) dialog).getListView();
                                for (int i = 0; i < list.getCount(); i++) {
                                    mItems.add(i);
                                    list.setItemChecked(i, true);
                                    checkedItems[i] = true;
                                }
                            }
                            else if(!mItems.contains(position))
                            {
                                mItems.add(position);
                            }
                        }
                        else
                        {
                            ListView list = ((AlertDialog) dialog).getListView();
                            if(position == 0)
                            {
                                for(int i = 0;i < mItems.size();i++)
                                {
                                    list.setItemChecked(i, false);
                                    checkedItems[i] = false;
                                }
                                mItems.clear();
                            }
                            else{
                                list.setItemChecked(0, false);
                                checkedItems[0] = false;
                                mItems.remove(0);

                                for(int i = 0;i < mItems.size();i++)
                                {
                                    if(position == mItems.get(i))
                                    {
                                        mItems.remove(i);
                                    }
                                }
                            }
                        }
                    }
                });
                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        value = "";
                        for(int i=0;i<mItems.size();i++)
                        {
                            value = value+ "'"+listItems[mItems.get(i)]+"'";
                            if(i != mItems.size()-1)
                            {
                                value = value + ",";
                            }
                        }
                    }
                });
                mBuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                mBuilder.setNeutralButton("Clear all", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for(int i=0;i<checkedItems.length;i++)
                        {
                            checkedItems[i]= false;
                            mItems.clear();
                        }
                    }
                });
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });
        return rootView;
    }

    private ArrayList<StateModel> ListCOA(String userId)
    {
        final ArrayList<StateModel> listVOs = new ArrayList<>();
        apiService.COA(userId).enqueue(new Callback<ResponseCOA>() {
            @Override
            public void onResponse(Call<ResponseCOA> call, Response<ResponseCOA> response) {
                if(response.body().getStatus().equals("success"))
                {
                    listItems = new String[response.body().getData().size()];
                    checkedItems = new boolean[listItems.length];
                    for (int i = 0; i < response.body().getData().size(); i++)
                    {
                        StateModel stateVO = new StateModel();

                        listItems[i] = response.body().getData().get(i).getName();
                        stateVO.setTitle(response.body().getData().get(i).getName());
                        stateVO.setValue(response.body().getData().get(i).getAcc());
                        stateVO.setSelected(false);
                        listVOs.add(stateVO);
                    }

                    /*SpinnerAdapter myAdapter = new SpinnerAdapter(getActivity(), 0,
                            listVOs);
                    spinner.setAdapter(myAdapter);*/
                }
                else {
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCOA> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        return listVOs;
    }
    private void ListLedger(String userId, String startDate, String endDate, String accNo) {
        String accountNo = "";

        if(!accNo.isEmpty())
        {
            String[] arrValue = accNo.replace("'","").split(",");
            for(int i = 0; i < arrValue.length;i++)
            {
                for(int k = 0;k<arrStateModel.size();k++)
                {
                    if(arrStateModel.get(k).getTitle().trim().equals(arrValue[i].trim()))
                    {
                        if(accountNo.isEmpty())
                        {
                            accountNo = "'"+arrStateModel.get(k).getValue()+"'";
                        }
                        else {
                            accountNo += ",'"+arrStateModel.get(k).getValue()+"'";
                        }
                    }
                }
            }
        }

        apiService.GeneralLedger(userId, startDate, endDate, accountNo).enqueue(new Callback<ResponseLedger>() {
            @Override
            public void onResponse(Call<ResponseLedger> call, Response<ResponseLedger> response) {
                if(response.body().getStatus().equals("success"))
                {
                    ledgerDataModel = response.body().getData();

                    if(ledgerDataModel.getAccounts() != null)
                    {
                        expandableListTitle = ledgerDataModel.getAccounts();
                        expandableListDetail = LedgerDetailAdapter.getData(expandableListTitle);
                        expandableListAdapter = new LedgerAdapter(getActivity(), expandableListTitle, expandableListDetail);
                        expandableListView.setAdapter(expandableListAdapter);
                        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

                            @Override
                            public void onGroupExpand(int groupPosition) {

                            }
                        });

                        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

                            @Override
                            public void onGroupCollapse(int groupPosition) {

                            }
                        });

                        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                            @Override
                            public boolean onChildClick(ExpandableListView parent, View v,
                                                        int groupPosition, int childPosition, long id) {
                                return false;
                            }
                        });
                    }
                }
                else {
                    //Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLedger> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateStartDate() {
        String myFormat = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        startDate.setText(sdf.format(myCalendar.getTime()));
    }

    private void updateEndDate() {
        String myFormat = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        endDate.setText(sdf.format(myCalendar.getTime()));
    }
}