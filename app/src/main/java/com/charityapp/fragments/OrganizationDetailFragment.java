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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.charityapp.DatabaseHelper;
import com.charityapp.OrganizationDetailPojo;
import com.charityapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrganizationDetailFragment extends Fragment {

    TextView tvName, tvAdd, tvDescription, tvCategory;
    Button btnDonate;
    OrganizationDetailPojo organizationDetailPojo;

    private DatabaseHelper databaseHelper;


    public OrganizationDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_organization_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        databaseHelper = new DatabaseHelper(getActivity());

        organizationDetailPojo = new OrganizationDetailPojo();

        tvName = view.findViewById(R.id.orgDetail_tvName);
        tvAdd = view.findViewById(R.id.orgDetail_tvLocation);
        tvCategory = view.findViewById(R.id.orgDetail_tvCategory);
        tvDescription = view.findViewById(R.id.orgDetail_tvDescription);
        btnDonate = view.findViewById(R.id.orgDetail_btnDonate);

        organizationDetailPojo = databaseHelper.getOrganizationData();

        tvName.setText(organizationDetailPojo.getName());
        tvAdd.setText(organizationDetailPojo.getLocation());
        tvCategory.setText(organizationDetailPojo.getCategory());
        tvDescription.setText(organizationDetailPojo.getDiscription());

        btnDonate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(), "Donate Successfully.", Toast.LENGTH_SHORT).show();

//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_frame, new BarcodeFragment()).commit();
            }
        });
    }
}
