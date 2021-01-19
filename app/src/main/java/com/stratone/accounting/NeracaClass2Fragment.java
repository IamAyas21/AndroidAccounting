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
import android.widget.Toast;

import com.stratone.accounting.adapter.NeracaClass2Adapter;
import com.stratone.accounting.adapter.NeracaClass3Adapter;
import com.stratone.accounting.adapter.ProfitClass3Adapter;
import com.stratone.accounting.adapter.ProfitLossClass2Adapter;
import com.stratone.accounting.model.NeracaClass2;
import com.stratone.accounting.model.NeracaClass3;
import com.stratone.accounting.model.ProfitLossClass2;
import com.stratone.accounting.model.ProfitLossClass3;
import com.stratone.accounting.response.ResponseNeracaClass2;
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
 * Use the {@link NeracaClass2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NeracaClass2Fragment extends Fragment {
    private String aClassId;
    private String bClassId;
    private String sDate;
    private String eDate;

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<NeracaClass3> expandableListTitle;
    List<NeracaClass2> cashFlowDetails;
    HashMap<NeracaClass3, List<NeracaClass2>> expandableListDetail;

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

    public NeracaClass2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NeracaClass2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NeracaClass2Fragment newInstance(String param1, String param2) {
        NeracaClass2Fragment fragment = new NeracaClass2Fragment();
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
                NeracaClass1Fragment neracaClass1Fragment = SessionFragment(aClassId,bClassId,sDate,eDate);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,neracaClass1Fragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_neraca_class2, container, false);

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

        aClassId = getArguments().getString("aClassId");
        sDate = getArguments().getString("startDate");
        eDate = getArguments().getString("endDate");

        if(startDate.getText().toString().equals("") && endDate.getText().toString().equals(""))
        {
            startDate.setText(sDate);
            endDate.setText(eDate);
        }

        apiService = ApiClient.getClient().create(ApiInterface.class);
        ListClass2(aClassId,"",startDate.getText().toString(),endDate.getText().toString());

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiService = ApiClient.getClient().create(ApiInterface.class);
                ListClass2(aClassId,"",startDate.getText().toString(),endDate.getText().toString());
                SessionFragment(aClassId,bClassId,startDate.getText().toString(),endDate.getText().toString());
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

    private void ListClass2(String classId, String userId, final String startDate, final String endDate) {
        apiService.NeracaClass2(userId, startDate, endDate, classId).enqueue(new Callback<ResponseNeracaClass2>() {
            @Override
            public void onResponse(Call<ResponseNeracaClass2> call, Response<ResponseNeracaClass2> response) {
                if(response.body().getStatus().equals("success"))
                {
                    expandableListTitle = response.body().getData();
                    expandableListDetail = NeracaClass2Adapter.getData(response.body().getData());
                    expandableListAdapter = new NeracaClass3Adapter(getActivity(), expandableListTitle, expandableListDetail, startDate, endDate,aClassId,"2");
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
            public void onFailure(Call<ResponseNeracaClass2> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private NeracaClass1Fragment SessionFragment(String aClassId, String bClassId, String startDate, String endDate)
    {
        NeracaClass1Fragment neracaClass1Fragment = new NeracaClass1Fragment();
        Bundle bundle=new Bundle();

        sDate = startDate;
        eDate = endDate;

        bundle.putString("aClassId", aClassId);
        bundle.putString("bClassId", bClassId);
        bundle.putString("startDate", startDate);
        bundle.putString("endDate", endDate);

        neracaClass1Fragment.setArguments(bundle);
        return neracaClass1Fragment;
    }
}