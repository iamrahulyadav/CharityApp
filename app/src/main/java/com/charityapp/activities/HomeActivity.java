package com.charityapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.charityapp.R;
import com.charityapp.fragments.BarcodeFragment;
import com.charityapp.fragments.FindFragment;
import com.charityapp.fragments.ProfileFragment;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout llBarcode, llFind, llProfile;
    public static TextView tvBarcode, tvFind, tvProfile, tvTitle;
    public static ImageView ivBarcode, ivFind, ivProfile, ivLogout;
    public static Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = findViewById(R.id.toolbar);
        tvTitle = findViewById(R.id.toolbar_tvTitle);
        setSupportActionBar(toolbar);

        llBarcode = findViewById(R.id.home_llBarcode);
        llFind = findViewById(R.id.home_llFind);
        llProfile = findViewById(R.id.home_llProfile);
//        btnDone = findViewById(R.id.regiCategory_tvDone);

        tvBarcode = findViewById(R.id.home_tvBarcode);
        tvFind = findViewById(R.id.home_tvFind);
        tvProfile = findViewById(R.id.home_tvProfile);

        ivBarcode = findViewById(R.id.home_ivBarcode);
        ivFind = findViewById(R.id.home_ivFind);
        ivProfile = findViewById(R.id.home_ivProfile);
        ivLogout = findViewById(R.id.home_ivLogout);

        llBarcode.setOnClickListener(this);
        llFind.setOnClickListener(this);
        llProfile.setOnClickListener(this);
        ivLogout.setOnClickListener(this);

        ivBarcode.setImageResource(R.drawable.blue_barcode);
        ivFind.setImageResource(R.drawable.gray_search);
        ivProfile.setImageResource(R.drawable.gray_user);

        tvBarcode.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        tvFind.setTextColor(getResources().getColor(R.color.gray));
        tvProfile.setTextColor(getResources().getColor(R.color.gray));

        tvTitle.setText("Scan Code");
        getSupportFragmentManager().beginTransaction().replace(R.id.container_frame, new BarcodeFragment()).commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.home_llBarcode:

                toolbar.setVisibility(View.VISIBLE);
                tvTitle.setText("Scan Code");

                ivBarcode.setImageResource(R.drawable.blue_barcode);
                ivFind.setImageResource(R.drawable.gray_search);
                ivProfile.setImageResource(R.drawable.gray_user);

                tvBarcode.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                tvFind.setTextColor(getResources().getColor(R.color.gray));
                tvProfile.setTextColor(getResources().getColor(R.color.gray));

                getSupportFragmentManager().beginTransaction().replace(R.id.container_frame, new BarcodeFragment()).commit();
                break;

            case R.id.home_llFind:

                toolbar.setVisibility(View.VISIBLE);
                tvTitle.setText("Organization");

                ivBarcode.setImageResource(R.drawable.gray_barcode);
                ivFind.setImageResource(R.drawable.blue_search);
                ivProfile.setImageResource(R.drawable.gray_user);

                tvBarcode.setTextColor(getResources().getColor(R.color.gray));
                tvFind.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                tvProfile.setTextColor(getResources().getColor(R.color.gray));

                getSupportFragmentManager().beginTransaction().replace(R.id.container_frame, new FindFragment()).commit();
                break;

            case R.id.home_llProfile:

                toolbar.setVisibility(View.VISIBLE);
                tvTitle.setText("Profile");

                ivBarcode.setImageResource(R.drawable.gray_barcode);
                ivFind.setImageResource(R.drawable.gray_search);
                ivProfile.setImageResource(R.drawable.blue_user);

                tvBarcode.setTextColor(getResources().getColor(R.color.gray));
                tvFind.setTextColor(getResources().getColor(R.color.gray));
                tvProfile.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                getSupportFragmentManager().beginTransaction().replace(R.id.container_frame, new ProfileFragment()).commit();
                break;

            case R.id.home_ivLogout:

                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);

                break;

        }
    }

    @Override
    public void onBackPressed() {

    }
}
