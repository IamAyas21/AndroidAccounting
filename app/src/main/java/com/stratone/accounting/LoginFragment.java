package com.stratone.accounting;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.stratone.accounting.adapter.LedgerAdapter;
import com.stratone.accounting.adapter.LedgerDetailAdapter;
import com.stratone.accounting.response.ResponseAccount;
import com.stratone.accounting.response.ResponseLedger;
import com.stratone.accounting.rest.ApiClient;
import com.stratone.accounting.rest.ApiInterface;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    private ApiInterface apiService;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText et_email, et_password;
    private Button btn_login;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        et_email = rootView.findViewById(R.id.et_email);
        et_password = rootView.findViewById(R.id.et_password);
        btn_login = rootView.findViewById(R.id.btn_login);

        apiService = ApiClient.getClient().create(ApiInterface.class);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(!validateUserName() | !validatePassword())
                {
                    return;
                }
                Login(et_email.getText().toString(),et_password.getText().toString());
            }
        });
        return rootView;
    }

    private void Login(String username, String password)
    {
        apiService.Login(username, password).enqueue(new Callback<ResponseAccount>() {
            @Override
            public void onResponse(Call<ResponseAccount> call, Response<ResponseAccount> response) {
                if(response.body().getStatus().equals("success"))
                {
                    startActivity(new Intent(getActivity(), HomeActivity.class));
                }
                else {
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseAccount> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private Boolean validateUserName()
    {
        String val = et_email.getText().toString();
        if(val.isEmpty())
        {
            et_email.setError("Username cannot be empty");
            return  false;
        }
        else {
            et_email.setError(null);
            return true;
        }
    }

    private Boolean validatePassword()
    {
        String val = et_password.getText().toString();
        if(val.isEmpty())
        {
            et_password.setError("Password cannot be empty");
            return  false;
        }
        else {
            et_password.setError(null);
            return true;
        }
    }
}