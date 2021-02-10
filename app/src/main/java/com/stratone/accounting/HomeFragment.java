package com.stratone.accounting;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.stratone.accounting.model.ChartBankBalance;
import com.stratone.accounting.model.ChartCashFlow;
import com.stratone.accounting.model.ChartProfitLoss;
import com.stratone.accounting.response.ResponseChartBankBalance;
import com.stratone.accounting.response.ResponseChartCashFlow;
import com.stratone.accounting.response.ResponseChartProfitLoss;
import com.stratone.accounting.rest.ApiClient;
import com.stratone.accounting.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private LineChart chartCashFlow;
    private LineData dataCashFlow;

    private LineChart chartProfitLoss;
    private LineData dataProfitLoss;

    private LineChart chartBankBalance;
    private LineData dataBankBalance;

    private List<ChartCashFlow> chartCashFlowList;
    private List<ChartProfitLoss> chartProfitLosses;
    private List<ChartBankBalance> chartBankBalances;

    private InputFragment inputFragment;
    private OuputDashboardFragment outputDashboardFragment;

    private ApiInterface apiService;

    private RelativeLayout rl_input;
    private RelativeLayout rl_output;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        chartCashFlow = rootView.findViewById(R.id.chart_cash_flow);
        chartProfitLoss = rootView.findViewById(R.id.chart_profit_loss);
        chartBankBalance = rootView.findViewById(R.id.chart_bank_balance);

        apiService = ApiClient.getClient().create(ApiInterface.class);
        ChartCashFlow("123","2019");
        ChartProfitLoss("123","2019");
        ChartBankBalance("123","2019");

        rl_input = rootView.findViewById(R.id.rl_input);
        rl_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputFragment = new InputFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,inputFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
            }
        });

        rl_output = rootView.findViewById(R.id.rl_output);
        rl_output.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outputDashboardFragment = new OuputDashboardFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,outputDashboardFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
            }
        });

        return rootView;
    }

    private void ChartCashFlow(String userId, String year)
    {
        apiService.ChartCashFlow(userId, year).enqueue(new Callback<ResponseChartCashFlow>() {
            @Override
            public void onResponse(Call<ResponseChartCashFlow> call, Response<ResponseChartCashFlow> response) {
                if(response.body().getStatus().equals("success"))
                {
                    chartCashFlowList = response.body().getData();
                    List<String> stringList = getXCashFlowValues(chartCashFlowList);
                    List<ILineDataSet> lineDataSets = getLineDataCashFlowValues(chartCashFlowList);
                    dataCashFlow = new LineData(stringList,lineDataSets);
                    chartCashFlow.setData(dataCashFlow);
                    chartCashFlow.setDescription("Chart of Cash Flow");
                    chartCashFlow.animateY(3000);
                    chartCashFlow.invalidate();

                }
                else {
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseChartCashFlow> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ChartProfitLoss(String userId, String year)
    {
        apiService.ChartProfitLoss(userId, year).enqueue(new Callback<ResponseChartProfitLoss>() {
            @Override
            public void onResponse(Call<ResponseChartProfitLoss> call, Response<ResponseChartProfitLoss> response) {
                if(response.body().getStatus().equals("success"))
                {
                    chartProfitLosses = response.body().getData();
                    List<String> stringList = getXProfitLossValues(chartProfitLosses);
                    List<ILineDataSet> lineDataSets = getLineDataProfitLossValues(chartProfitLosses);
                    dataProfitLoss = new LineData(stringList,lineDataSets);
                    chartProfitLoss.setData(dataProfitLoss);
                    chartProfitLoss.setDescription("Chart of Income Statement");
                    chartProfitLoss.animateY(3000);
                    chartProfitLoss.invalidate();

                }
                else {
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseChartProfitLoss> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ChartBankBalance(String userId, String year)
    {
        apiService.ChartBankBalance(userId, year).enqueue(new Callback<ResponseChartBankBalance>() {
            @Override
            public void onResponse(Call<ResponseChartBankBalance> call, Response<ResponseChartBankBalance> response) {
                if(response.body().getStatus().equals("success"))
                {
                    chartBankBalances = response.body().getData();
                    List<String> stringList = getXBankBalanceValues(chartBankBalances);
                    List<ILineDataSet> lineDataSets = getLineDataBankBalanceValues(chartBankBalances);
                    dataBankBalance = new LineData(stringList,lineDataSets);
                    chartBankBalance.setData(dataBankBalance);
                    chartBankBalance.setDescription("Chart of Bank Balance");
                    chartBankBalance.animateY(3000);
                    chartBankBalance.invalidate();

                }
                else {
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseChartBankBalance> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<ILineDataSet> getLineDataCashFlowValues(List<ChartCashFlow> chartCashFlowList)
    {
        List<ILineDataSet> lineDataSets = null;
        ArrayList<Entry> cashInArrayList = new ArrayList<>();
        ArrayList<Entry> cashOutArrayList = new ArrayList<>();

        for(int i = 0;i < chartCashFlowList.size();i++)
        {
            cashInArrayList.add(new Entry(Float.parseFloat(chartCashFlowList.get(i).getR()),i));
        }

        for(int i = 0;i < chartCashFlowList.size();i++)
        {
            cashOutArrayList.add(new Entry(Float.parseFloat(chartCashFlowList.get(i).getD()),i));
        }

        LineDataSet lineDataSet = new LineDataSet(cashInArrayList,"Cash In");
        lineDataSet.setColor(Color.RED);
        lineDataSet.setCircleColor(Color.YELLOW);
        lineDataSet.setCircleRadius(7);

        LineDataSet lineDataSet2 = new LineDataSet(cashOutArrayList,"Cash Out");
        lineDataSet2.setColor(Color.GREEN);
        lineDataSet2.setCircleColor(Color.BLUE);

        lineDataSets = new ArrayList<>();
        lineDataSets.add(lineDataSet);
        lineDataSets.add(lineDataSet2);

        return lineDataSets;
    }

    private List<String> getXCashFlowValues(List<ChartCashFlow> chartCashFlowList)
    {
        ArrayList<String> xValues = new ArrayList<>();
        for(int i = 0;i < chartCashFlowList.size();i++)
        {
            xValues.add(chartCashFlowList.get(i).getMonth());
        }

        return xValues;
    }

    private List<ILineDataSet> getLineDataProfitLossValues(List<ChartProfitLoss> chartProfitLosses)
    {
        List<ILineDataSet> lineDataSets = null;
        ArrayList<Entry> cashInArrayList = new ArrayList<>();
        ArrayList<Entry> cashOutArrayList = new ArrayList<>();

        for(int i = 0;i < chartProfitLosses.size();i++)
        {
            cashInArrayList.add(new Entry(Float.parseFloat(chartProfitLosses.get(i).getIncome()),i));
        }

        for(int i = 0;i < chartProfitLosses.size();i++)
        {
            cashOutArrayList.add(new Entry(Float.parseFloat(chartProfitLosses.get(i).getCost()),i));
        }

        LineDataSet lineDataSet = new LineDataSet(cashInArrayList,"Income");
        lineDataSet.setColor(Color.RED);
        lineDataSet.setCircleColor(Color.YELLOW);
        lineDataSet.setCircleRadius(7);

        LineDataSet lineDataSet2 = new LineDataSet(cashOutArrayList,"Cost");
        lineDataSet2.setColor(Color.GREEN);
        lineDataSet2.setCircleColor(Color.BLUE);

        lineDataSets = new ArrayList<>();
        lineDataSets.add(lineDataSet);
        lineDataSets.add(lineDataSet2);

        return lineDataSets;
    }

    private List<String> getXProfitLossValues(List<ChartProfitLoss> chartProfitLosses)
    {
        ArrayList<String> xValues = new ArrayList<>();
        for(int i = 0;i < chartProfitLosses.size();i++)
        {
            xValues.add(chartProfitLosses.get(i).getMonth());
        }

        return xValues;
    }

    private List<ILineDataSet> getLineDataBankBalanceValues(List<ChartBankBalance> chartBankBalances)
    {
        List<ILineDataSet> lineDataSets = null;
        ArrayList<Entry> cashInArrayList = new ArrayList<>();

        for(int i = 0;i < chartBankBalances.size();i++)
        {
            cashInArrayList.add(new Entry(Float.parseFloat(chartBankBalances.get(i).getTotal()),i));
        }

        LineDataSet lineDataSet = new LineDataSet(cashInArrayList,"Balance");
        lineDataSet.setColor(Color.RED);
        lineDataSet.setCircleColor(Color.YELLOW);
        lineDataSet.setCircleRadius(7);

        lineDataSets = new ArrayList<>();
        lineDataSets.add(lineDataSet);

        return lineDataSets;
    }

    private List<String> getXBankBalanceValues(List<ChartBankBalance> chartBankBalances)
    {
        ArrayList<String> xValues = new ArrayList<>();
        for(int i = 0;i < chartBankBalances.size();i++)
        {
            xValues.add(chartBankBalances.get(i).getMonth());
        }

        return xValues;
    }
}