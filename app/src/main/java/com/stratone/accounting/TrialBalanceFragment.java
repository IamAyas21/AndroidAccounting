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
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.stratone.accounting.adapter.CashFlowAdapter;
import com.stratone.accounting.adapter.CashFlowDetailAdapter;
import com.stratone.accounting.adapter.CashFlowTotalAdapter;
import com.stratone.accounting.adapter.OutputDashboardAdapter;
import com.stratone.accounting.adapter.TrialBalanceAdapter;
import com.stratone.accounting.model.TotalTrialBalance;
import com.stratone.accounting.model.TrialBalance;
import com.stratone.accounting.response.ResponseCashFlow;
import com.stratone.accounting.response.ResponseCashFlowTotal;
import com.stratone.accounting.response.ResponseTrialBalance;
import com.stratone.accounting.rest.ApiClient;
import com.stratone.accounting.rest.ApiInterface;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrialBalanceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrialBalanceFragment extends Fragment {
    private List<TrialBalance> listTrialBalance;
    private TotalTrialBalance totalTrialBalance;
    private TrialBalanceAdapter trialBalanceAdapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView listViewTrialBalance;
    private ApiInterface apiService;
    private EditText startDate;
    private EditText endDate;
    private Button btnGo;
    private TextView initialTotalDebit;
    private TextView initialTotalCredit;
    private TextView mutationTotalDebit;
    private TextView mutationTotalCredit;
    private TextView endingTotalDebit;
    private TextView endingTotalCredit;

    final Calendar myCalendar = Calendar.getInstance();
    private DatePickerDialog startDatePicker;
    private DatePickerDialog endDatePicker;

    public TrialBalanceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TrialBalanceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TrialBalanceFragment newInstance(String param1, String param2) {
        TrialBalanceFragment fragment = new TrialBalanceFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_trial_balance, container, false);
        startDate = (EditText)rootView.findViewById(R.id.start_date);
        endDate = (EditText)rootView.findViewById(R.id.end_date);
        btnGo = (Button)rootView.findViewById(R.id.btn_go);
        initialTotalDebit = (TextView)rootView.findViewById(R.id.tv_total_debit_initial);
        initialTotalCredit = (TextView)rootView.findViewById(R.id.tv_total_credit_initial);
        mutationTotalDebit = (TextView)rootView.findViewById(R.id.tv_total_debit_mutation);
        mutationTotalCredit = (TextView)rootView.findViewById(R.id.tv_total_credit_mutation);
        endingTotalDebit = (TextView)rootView.findViewById(R.id.tv_total_debit_ending);
        endingTotalCredit = (TextView)rootView.findViewById(R.id.tv_total_credit_ending);
        listViewTrialBalance = (ListView)rootView.findViewById(R.id.listview_trial_balance);

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
        ListTrialBalance("",startDate.getText().toString(),endDate.getText().toString());

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiService = ApiClient.getClient().create(ApiInterface.class);
                ListTrialBalance("",startDate.getText().toString(),endDate.getText().toString());
            }
        });
        return rootView;
    }

    private void ListTrialBalance(String userId, String startDate, String endDate) {
        apiService.TrialBalance(userId, startDate, endDate).enqueue(new Callback<ResponseTrialBalance>() {
            @Override
            public void onResponse(Call<ResponseTrialBalance> call, Response<ResponseTrialBalance> response) {
                if(response.body().getStatus().equals("success"))
                {
                    listTrialBalance = response.body().getData();
                    trialBalanceAdapter = new TrialBalanceAdapter(getActivity() ,R.layout.list_row_trial_balance,listTrialBalance);
                    listViewTrialBalance.setAdapter(trialBalanceAdapter);

                    totalTrialBalance = response.body().getTotal();
                    initialTotalDebit.setText(totalTrialBalance.getInitialDebet());
                    initialTotalCredit.setText(totalTrialBalance.getInitialCredit());
                    mutationTotalDebit.setText(totalTrialBalance.getMutationDebet());
                    mutationTotalCredit.setText(totalTrialBalance.getMutationCredit());
                    endingTotalDebit.setText(totalTrialBalance.getEndingDebet());
                    endingTotalCredit.setText(totalTrialBalance.getEndingCredit());
                }
                else {
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseTrialBalance> call, Throwable t) {
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