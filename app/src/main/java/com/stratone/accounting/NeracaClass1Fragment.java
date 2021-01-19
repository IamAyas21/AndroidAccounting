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
import android.widget.ListView;
import android.widget.Toast;

import com.stratone.accounting.adapter.NeracaClass1Adapter;
import com.stratone.accounting.adapter.ProfitLossClass1Adapter;
import com.stratone.accounting.model.NeracaClass1;
import com.stratone.accounting.model.ProfitLossClass1;
import com.stratone.accounting.response.ResponseNeracaClass1;
import com.stratone.accounting.response.ResponseProfitLossClass1;
import com.stratone.accounting.rest.ApiClient;
import com.stratone.accounting.rest.ApiInterface;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NeracaClass1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NeracaClass1Fragment extends Fragment {
    private String sDate;
    private String eDate;

    private ListView listView;
    private ApiInterface apiService;
    private EditText startDate;
    private EditText endDate;
    private Button btnGo;
    List<NeracaClass1> neracaClass1s;
    NeracaClass1Adapter neracaClass1Adapter;

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

    public NeracaClass1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NeracaClass1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NeracaClass1Fragment newInstance(String param1, String param2) {
        NeracaClass1Fragment fragment = new NeracaClass1Fragment();
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
        View rootView = inflater.inflate(R.layout.fragment_neraca_class1, container, false);
        listView = rootView.findViewById(R.id.listView_class1);
        startDate = (EditText)rootView.findViewById(R.id.start_date_class1);
        endDate = (EditText)rootView.findViewById(R.id.end_date_class1);
        btnGo = (Button)rootView.findViewById(R.id.btn_go_class1);

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

        if(getArguments() != null)
        {
            sDate = getArguments().getString("startDate");
            eDate = getArguments().getString("endDate");
        }

        if(startDate.getText().toString().equals("") && endDate.getText().toString().equals(""))
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String date = dateFormat.format(myCalendar.getTime());

            if(sDate == null && eDate == null)
            {
                sDate = date;
                eDate = date;
            }

            startDate.setText(sDate);
            endDate.setText(eDate);
        }

        apiService = ApiClient.getClient().create(ApiInterface.class);
        ListClass1("",startDate.getText().toString(),endDate.getText().toString());

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiService = ApiClient.getClient().create(ApiInterface.class);
                ListClass1("",startDate.getText().toString(),endDate.getText().toString());
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

    private void ListClass1(String userId, final String startDate, final String endDate) {
        apiService.NeracaClass1(userId, startDate, endDate).enqueue(new Callback<ResponseNeracaClass1>() {
            @Override
            public void onResponse(Call<ResponseNeracaClass1> call, Response<ResponseNeracaClass1> response) {
                if(response.body().getStatus().equals("success"))
                {
                    neracaClass1s = response.body().getData();

                    neracaClass1Adapter = new NeracaClass1Adapter(getActivity(),R.layout.list_row_class1,neracaClass1s, startDate, endDate);
                    listView.setAdapter(neracaClass1Adapter);
                }
                else {
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseNeracaClass1> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}