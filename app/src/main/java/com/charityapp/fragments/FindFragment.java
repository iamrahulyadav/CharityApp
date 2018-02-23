package com.charityapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.charityapp.DatabaseHelper;
import com.charityapp.FindAdapter;
import com.charityapp.OrganizationDetailPojo;
import com.charityapp.R;
import com.charityapp.activities.HomeActivity;

import java.util.ArrayList;

public class FindFragment extends Fragment {

    RecyclerView recycleListView;
    EditText etSearch;
    ArrayList<OrganizationDetailPojo> array;
    ArrayList<OrganizationDetailPojo> searchedArraylist;
    FindAdapter findAdapter;

    private DatabaseHelper databaseHelper;

    ArrayList<OrganizationDetailPojo> organizationDetailPojos;

    FloatingActionButton fab;
    OrganizationDetailPojo organizationDetailPojo;

    public FindFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_find, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        databaseHelper = new DatabaseHelper(getActivity());

        recycleListView = view.findViewById(R.id.find_rlMain);
        etSearch = view.findViewById(R.id.find_etSearch);

        fab = view.findViewById(R.id.fab);

        array = new ArrayList<OrganizationDetailPojo>();

        organizationDetailPojos = databaseHelper.getAllOrganization();

        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Click action
                HomeActivity.toolbar.setVisibility(View.GONE);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_frame, new AddOrganizationFragment()).commit();
            }
        });
//        organizationDetailPojo = new OrganizationDetailPojo();
//        organizationDetailPojo.setName("Organization 1");
//        organizationDetailPojo.setLocation("Location 1");
//        organizationDetailPojo.setAmount("Amount 1");
//        organizationDetailPojo.setDiscription("Description 1 Description Description Description Description Description Description");
//        array.add(organizationDetailPojo);
//
//        organizationDetailPojo = new OrganizationDetailPojo();
//        organizationDetailPojo.setName("Organization 2");
//        organizationDetailPojo.setLocation("Location 2");
//        organizationDetailPojo.setAmount("Amount 2");
//        organizationDetailPojo.setDiscription("Description 2 Description Description Description Description Description Description");
//        array.add(organizationDetailPojo);
//
//        organizationDetailPojo = new OrganizationDetailPojo();
//        organizationDetailPojo.setName("Organization 3");
//        organizationDetailPojo.setLocation("Location 3");
//        organizationDetailPojo.setAmount("Amount 3");
//        organizationDetailPojo.setDiscription("Description 3 Description Description Description Description Description Description");
//        array.add(organizationDetailPojo);
//
//        organizationDetailPojo = new OrganizationDetailPojo();
//        organizationDetailPojo.setName("Organization 4");
//        organizationDetailPojo.setLocation("Location 4");
//        organizationDetailPojo.setAmount("Amount 4");
//        organizationDetailPojo.setDiscription("Description 4 Description Description Description Description Description Description");
//        array.add(organizationDetailPojo);
//
//        organizationDetailPojo = new OrganizationDetailPojo();
//        organizationDetailPojo.setName("Organization 5");
//        organizationDetailPojo.setLocation("Location 5");
//        organizationDetailPojo.setAmount("Amount 5");
//        organizationDetailPojo.setDiscription("Description 5 Description Description Description Description Description Description");
//        array.add(organizationDetailPojo);
//
//        organizationDetailPojo = new OrganizationDetailPojo();
//        organizationDetailPojo.setName("Organization 6");
//        organizationDetailPojo.setLocation("Location 6");
//        organizationDetailPojo.setAmount("Amount 6");
//        organizationDetailPojo.setDiscription("Description 6 Description Description Description Description Description Description");
//        array.add(organizationDetailPojo);
//
//        organizationDetailPojo = new OrganizationDetailPojo();
//        organizationDetailPojo.setName("Organization 7");
//        organizationDetailPojo.setLocation("Location 7");
//        organizationDetailPojo.setAmount("Amount 7");
//        organizationDetailPojo.setDiscription("Description 7 Description Description Description Description Description Description");
//        array.add(organizationDetailPojo);
//
//        organizationDetailPojo = new OrganizationDetailPojo();
//        organizationDetailPojo.setName("Organization 8");
//        organizationDetailPojo.setLocation("Location 8");
//        organizationDetailPojo.setAmount("Amount 8");
//        organizationDetailPojo.setDiscription("Description 8 Description Description Description Description Description Description");
//        array.add(organizationDetailPojo);
//
//        organizationDetailPojo = new OrganizationDetailPojo();
//        organizationDetailPojo.setName("Organization 9");
//        organizationDetailPojo.setLocation("Location 9");
//        organizationDetailPojo.setAmount("Amount 9");
//        organizationDetailPojo.setDiscription("Description 9 Description Description Description Description Description Description");
//        array.add(organizationDetailPojo);

        if(organizationDetailPojos.size() > 0){
            recycleListView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

            findAdapter = new FindAdapter(getActivity(), organizationDetailPojos);
            recycleListView.setAdapter(findAdapter);
        }
        else {
            Toast.makeText(getActivity(), "No Organization Found.", Toast.LENGTH_SHORT).show();
        }

        etSearch.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if (array != null)
                {
                    String searchFor = etSearch.getText().toString();
                    searchedArraylist = new ArrayList<>();
                    for (int j = 1; j < array.size(); j++)
                    {
                        if (array.get(j).getName().toLowerCase().contains(searchFor.toLowerCase())) {
                            searchedArraylist.add(array.get(j));
                        }
                    }

                    findAdapter = new FindAdapter(getActivity(), searchedArraylist);
                    recycleListView.setAdapter(findAdapter);

                    findAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });

    }
}
