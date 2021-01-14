package com.stratone.accounting;

import android.app.DatePickerDialog;
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
import android.widget.Toast;

import com.stratone.accounting.adapter.CashFlowAdapter;
import com.stratone.accounting.adapter.CashFlowDetailAdapter;
import com.stratone.accounting.adapter.CashFlowTotalAdapter;
import com.stratone.accounting.adapter.ProfitClass3Adapter;
import com.stratone.accounting.adapter.ProfitLossClass2Adapter;
import com.stratone.accounting.model.CashFlowDetail;
import com.stratone.accounting.model.CashFlowHeader;
import com.stratone.accounting.model.ProfitLossClass1;
import com.stratone.accounting.model.ProfitLossClass2;
import com.stratone.accounting.model.ProfitLossClass3;
import com.stratone.accounting.response.ResponseCashFlow;
import com.stratone.accounting.response.ResponseCashFlowTotal;
import com.stratone.accounting.response.ResponseProfitLossClass2;
import com.stratone.accounting.rest.ApiClient;
import com.stratone.accounting.rest.ApiInterface;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfitLossClass2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfitLossClass2Fragment extends Fragment {
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<ProfitLossClass3> expandableListTitle;
    List<ProfitLossClass2> cashFlowDetails;
    HashMap<ProfitLossClass3, List<ProfitLossClass2>> expandableListDetail;

    private ApiInterface apiService;
    private EditText startDate;
    private EditText endDate;
    private Button btnGo;

    final Calendar myCalendar = Calendar.getInstance();
    private DatePickerDialog startDatePicker;
    private DatePickerDialog endDatePicker;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfitLossClass2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfitLossClass2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfitLossClass2Fragment newInstance(String param1, String param2) {
        ProfitLossClass2Fragment fragment = new ProfitLossClass2Fragment();
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
                ProfitLossClass1Fragment profitLossClass1Fragment = new ProfitLossClass1Fragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,profitLossClass1Fragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profit_loss_class2, container, false);
        startDate = (EditText)rootView.findViewById(R.id.start_date);
        endDate = (EditText)rootView.findViewById(R.id.end_date);
        expandableListView = (ExpandableListView)rootView.findViewById(R.id.listview_class2);
        btnGo = (Button)rootView.findViewById(R.id.btn_go);

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

        final String classId = getArguments().getString("classId");
        final String sDate = getArguments().getString("startDate");
        final String eDate = getArguments().getString("endDate");

        if(startDate.getText().toString().equals("") && endDate.getText().toString().equals(""))
        {
            /*SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String date = dateFormat.format(myCalendar.getTime());*/
            startDate.setText(sDate);
            endDate.setText(eDate);
        }

        apiService = ApiClient.getClient().create(ApiInterface.class);
        ListClass2(classId,"",startDate.getText().toString(),endDate.getText().toString());

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiService = ApiClient.getClient().create(ApiInterface.class);
                ListClass2(classId,"",startDate.getText().toString(),endDate.getText().toString());
            }
        });

        return rootView;
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

    private void ListClass2(String classId, String userId, String startDate, String endDate) {
        apiService.ProfitLossClass2(userId, startDate, endDate, classId).enqueue(new Callback<ResponseProfitLossClass2>() {
            @Override
            public void onResponse(Call<ResponseProfitLossClass2> call, Response<ResponseProfitLossClass2> response) {
                if(response.body().getStatus().equals("success"))
                {
                    expandableListTitle = response.body().getData();
                    expandableListDetail = ProfitLossClass2Adapter.getData(response.body().getData());
                    expandableListAdapter = new ProfitClass3Adapter(getActivity(), expandableListTitle, expandableListDetail);
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
                else {
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseProfitLossClass2> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}