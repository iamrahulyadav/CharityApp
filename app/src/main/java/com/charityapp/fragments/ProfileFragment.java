package com.charityapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.charityapp.DatabaseHelper;
import com.charityapp.R;
import com.charityapp.RegistrationDataPojo;
import com.charityapp.activities.HomeActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */

public class ProfileFragment extends Fragment {

    private DatabaseHelper databaseHelper;
    EditText etName, etEmail, etMobile;
    Button btnAdd;

    RegistrationDataPojo registrationDataPojo;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        databaseHelper = new DatabaseHelper(getActivity());

        etName = view.findViewById(R.id.profile_etName);
        etEmail = view.findViewById(R.id.profile_etEmail);
        etMobile = view.findViewById(R.id.profile_etContact);
        btnAdd = view.findViewById(R.id.newOrg_btnAdd);

        registrationDataPojo = databaseHelper.getRegistrationData();

        etName.setText(registrationDataPojo.getName());
        etEmail.setText(registrationDataPojo.getEmail());
        etMobile.setText(registrationDataPojo.getContact());

        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(databaseHelper.updateProfile(etName.getText().toString(), etEmail.getText().toString(), etMobile.getText().toString())){
                    Toast.makeText(getActivity(), "Data Updated.", Toast.LENGTH_SHORT).show();

                    HomeActivity.ivBarcode.setImageResource(R.drawable.blue_barcode);
                    HomeActivity.ivFind.setImageResource(R.drawable.gray_search);
                    HomeActivity.ivProfile.setImageResource(R.drawable.gray_user);

                    HomeActivity.tvBarcode.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    HomeActivity.tvFind.setTextColor(getResources().getColor(R.color.gray));
                    HomeActivity.tvProfile.setTextColor(getResources().getColor(R.color.gray));

                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_frame, new BarcodeFragment()).commit();
                }
                else {
                    Toast.makeText(getActivity(), "Unable to Update data.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
