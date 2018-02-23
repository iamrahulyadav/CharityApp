package com.charityapp;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.charityapp.activities.HomeActivity;
import com.charityapp.fragments.AddOrganizationFragment;
import com.charityapp.fragments.OrganizationDetailFragment;

import java.util.ArrayList;

/**
 * Created by archirayan on 6/12/17.
 */

public class FindAdapter extends RecyclerView.Adapter<FindAdapter.myViewHolder>
{

    Context context;
    ArrayList<OrganizationDetailPojo> organizationDetailPojos;

    public FindAdapter(Context context, ArrayList<OrganizationDetailPojo> organizationDetailPojos) {
        this.context = context;
        this.organizationDetailPojos = organizationDetailPojos;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dashboard_item_layout, parent, false);

        return new myViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final myViewHolder holder, final int position) {
        holder.tvName.setText(organizationDetailPojos.get(position).getName());
        holder.tvAmount.setText(organizationDetailPojos.get(position).getDiscription());

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeActivity.toolbar.setVisibility(View.GONE);
                ((HomeActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.container_frame, new OrganizationDetailFragment()).commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return organizationDetailPojos.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder
    {

        TextView tvName, tvAmount, tvFloorNm, tvFlatNm;
        CardView card;
//        RelativeLayout layot_data;
//        // ImageView imgDotIndicator;

        public myViewHolder(final View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.name);
            tvAmount = (TextView) itemView.findViewById(R.id.amount);
            card = (CardView) itemView.findViewById(R.id.organization_card);
//            tvFlatNm = (TextView) itemView.findViewById(R.id.FlatNm);
//            rbSelect = (RadioButton) itemView.findViewById(R.id.radio_btn);
//            layot_data= (RelativeLayout) itemView.findViewById(R.id.layot_data);
            //   imgDotIndicator = (ImageView) itemView.findViewById(R.id.pageDotIndicatorImg);
        }
    }

}