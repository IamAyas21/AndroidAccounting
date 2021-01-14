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
import android.widget.TextView;
import android.widget.Toast;

import com.stratone.accounting.adapter.CashFlowAdapter;
import com.stratone.accounting.adapter.CashFlowDetailAdapter;
import com.stratone.accounting.response.ResponseCashFlow;
import com.stratone.accounting.response.ResponseReb;
import com.stratone.accounting.rest.ApiClient;
import com.stratone.accounting.rest.ApiInterface;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RebFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RebFragment extends Fragment {
    private ApiInterface apiService;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String stDate;
    private String edDate;

    private String mParam1;
    private String mParam2;

    private EditText startDate;
    private EditText endDate;
    private Button btnGo;

    final Calendar myCalendar = Calendar.getInstance();
    private DatePickerDialog startDatePicker;
    private DatePickerDialog endDatePicker;

    private TextView titleSaldoId, titleSaldoEg, modalSahamAmt
            , tambahanModal, saldoLabaAwal, jmlEkuitasAwal, jmlLabaThnJln
            , dividen, jmlAkhir, endTitleSaldoId, endTitleSaldoEg;
    public RebFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RebFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RebFragment newInstance(String param1, String param2) {
        RebFragment fragment = new RebFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_reb, container, false);

        startDate = (EditText)rootView.findViewById(R.id.start_date_reb);
        endDate = (EditText)rootView.findViewById(R.id.end_date_reb);
        btnGo = (Button)rootView.findViewById(R.id.btn_go_reb);

        titleSaldoId = (TextView) rootView.findViewById(R.id.tv_title_reb_id);
        titleSaldoEg = (TextView)rootView.findViewById(R.id.tv_title_reb_eg);
        modalSahamAmt = (TextView) rootView.findViewById(R.id.tv_modal_saham_amt);
        tambahanModal = (TextView) rootView.findViewById(R.id.tv_tambahan_modal_amt);
        saldoLabaAwal = (TextView) rootView.findViewById(R.id.tv_saldo_laba_awal_amt);
        jmlEkuitasAwal = (TextView) rootView.findViewById(R.id.tv_jumlah_ekuitas_awal_amt);
        jmlLabaThnJln = (TextView) rootView.findViewById(R.id.tv_jumlah_laba_tahun_berjalan_amt);
        dividen = (TextView) rootView.findViewById(R.id.tv_dividen_amt);
        jmlAkhir = (TextView) rootView.findViewById(R.id.tv_saldo_per_amt);
        endTitleSaldoId = (TextView) rootView.findViewById(R.id.tv_saldo_per_id);
        endTitleSaldoEg = (TextView) rootView.findViewById(R.id.tv_saldo_per_eg);

        updateStartDate();
        updateEndDate();

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiService = ApiClient.getClient().create(ApiInterface.class);
                GetREB("",startDate.getText().toString(),endDate.getText().toString());
            }
        });

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
        GetREB("",startDate.getText().toString(),endDate.getText().toString());
        return rootView;
    }

    private void GetREB(String userId, String startDate, String endDate) {
        apiService.Reb(userId, startDate, endDate).enqueue(new Callback<ResponseReb>() {
            @Override
            public void onResponse(Call<ResponseReb> call, Response<ResponseReb> response) {
                if(response.body().getStatus().equals("success"))
                {
                    titleSaldoId.setText("Saldo Per "+stDate);
                    titleSaldoEg.setText("Balance Per "+edDate);
                    modalSahamAmt.setText(response.body().getData().getModalSaham());
                    tambahanModal.setText(response.body().getData().getTambahSaham());
                    saldoLabaAwal.setText(response.body().getData().getLastProfit());
                    jmlEkuitasAwal.setText(response.body().getData().getJumlahAwal());
                    jmlLabaThnJln.setText(response.body().getData().getBalance());
                    dividen.setText(response.body().getData().getDeviden());
                    jmlAkhir.setText(response.body().getData().getJumlahAkhir());
                    endTitleSaldoId.setText("Saldo Per "+stDate);
                    endTitleSaldoEg.setText("Balance Per "+edDate);
                }
                else {
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseReb> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateStartDate() {
        String myFormat = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        startDate.setText(sdf.format(myCalendar.getTime()));

        myFormat = "dd MMM yyyy";
        sdf = new SimpleDateFormat(myFormat, Locale.US);
        stDate = sdf.format(myCalendar.getTime());
    }

    private void updateEndDate() {
        String myFormat = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        endDate.setText(sdf.format(myCalendar.getTime()));

        myFormat = "dd MMM yyyy";
        sdf = new SimpleDateFormat(myFormat, Locale.US);
        edDate = sdf.format(myCalendar.getTime());
    }

}