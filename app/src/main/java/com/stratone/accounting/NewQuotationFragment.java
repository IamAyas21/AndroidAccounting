package com.stratone.accounting;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewQuotationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewQuotationFragment extends Fragment {
    private Spinner spinnerItemQuotation;
    private EditText editTextUnitPrice;
    private EditText editTextQuantity;
    private EditText editTextAmount;

    private BigDecimal unitPrice = new BigDecimal(0);
    private BigDecimal quantity = new BigDecimal(0);
    private BigDecimal amount = new BigDecimal(0);

    private QuotationFragment quotationFragment;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewQuotationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewQuotationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewQuotationFragment newInstance(String param1, String param2) {
        NewQuotationFragment fragment = new NewQuotationFragment();
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
                quotationFragment = new QuotationFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,quotationFragment )
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_new_quotation, container, false);
        spinnerItemQuotation = rootView.findViewById(R.id.spinner_new_quotation);
        editTextUnitPrice = rootView.findViewById(R.id.unit_price_new_quotation);
        editTextAmount = rootView.findViewById(R.id.amount_new_quotation);
        editTextQuantity = rootView.findViewById(R.id.qty_new_quotation);

        editTextQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!editTextQuantity.getText().toString().equals(""))
                {
                    quantity = new BigDecimal(editTextQuantity.getText().toString());
                }

                if(!editTextUnitPrice.getText().toString().equals(""))
                {
                    unitPrice = new BigDecimal(editTextUnitPrice.getText().toString().replace(",",""));
                }

                amount = quantity.multiply(unitPrice);
                editTextAmount.setText(amount.toString());
               /* editTextUnitPrice.addTextChangedListener(new NumberTextWatcher(editTextUnitPrice));
                editTextAmount.addTextChangedListener(new NumberTextWatcher(editTextAmount));*/
            }
        });

        editTextUnitPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!editTextQuantity.getText().toString().equals(""))
                {
                    quantity = new BigDecimal(editTextQuantity.getText().toString());
                }

                if(!editTextUnitPrice.getText().toString().equals(""))
                {
                    unitPrice = new BigDecimal(editTextUnitPrice.getText().toString().replace(",",""));
                }

                amount = quantity.multiply(unitPrice);
                editTextAmount.setText(amount.toString());
               /* editTextUnitPrice.addTextChangedListener(new NumberTextWatcher(editTextUnitPrice));
                editTextAmount.addTextChangedListener(new NumberTextWatcher(editTextAmount));*/
            }
        });

        ArrayList<String> itemList = new ArrayList<>();
        itemList.add("Aquatic Timing System");
        itemList.add("Athletic Timing System");
        itemList.add("Scoreboard Display");
        itemList.add("CCTV");
        itemList.add("Access Control");

        spinnerItemQuotation.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item,itemList));
        spinnerItemQuotation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0)
                {
                    Toast.makeText(getActivity(),"Please Select Item",Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return rootView;
    }
}