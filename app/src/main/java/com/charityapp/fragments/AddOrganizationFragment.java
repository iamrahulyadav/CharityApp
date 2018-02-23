package com.charityapp.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.charityapp.DatabaseHelper;
import com.charityapp.OrganizationDetailPojo;
import com.charityapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddOrganizationFragment extends Fragment {

    EditText etName, etAdd, etDescription;
    Spinner spCategory;
    Button btnAdd;
    OrganizationDetailPojo organizationDetailPojo;
    private DatabaseHelper databaseHelper;

    ArrayList<String> arr;
    ArrayAdapter<String> arrayAdapter;

    public AddOrganizationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_add_organization, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        databaseHelper = new DatabaseHelper(getActivity());

        organizationDetailPojo = new OrganizationDetailPojo();

        arr = new ArrayList<>();
        arr.add("Orphanage");
        arr.add("Cancer Institute");
        arr.add("ABC");
        arr.add("Orphanage");
        arr.add("Cancer Institute");
        arr.add("ABC");

        etName = view.findViewById(R.id.newOrg_etName);
        etAdd = view.findViewById(R.id.newOrg_etLocation);
        spCategory = view.findViewById(R.id.newOrg_spCategory);
        etDescription = view.findViewById(R.id.newOrg_etDescription);
//        etAmt = view.findViewById(R.id.newOrg_etAmount);
        btnAdd = view.findViewById(R.id.newOrg_btnAdd);

        arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arr);
        spCategory.setAdapter(arrayAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                organizationDetailPojo.setName(etName.getText().toString());
                organizationDetailPojo.setDiscription(etDescription.getText().toString());
                organizationDetailPojo.setCategory(spCategory.getSelectedItem().toString());
                organizationDetailPojo.setLocation(etAdd.getText().toString());

                databaseHelper.addOrganizationData(organizationDetailPojo);

                Toast.makeText(getActivity(), "Organization Added Successfully.", Toast.LENGTH_SHORT).show();

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_frame, new FindFragment()).commit();
            }
        });
    }
}
