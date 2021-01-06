package com.example.androidassignment.adapters;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidassignment.R;
import com.example.androidassignment.activities.AlbumsActivity;
import com.example.androidassignment.models.Address;
import com.example.androidassignment.models.Company;
import com.example.androidassignment.models.GeoCodes;
import com.example.androidassignment.models.User;


import org.json.JSONObject;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    ArrayList<User> userArrayList;
    Context context;

    public UserListAdapter(Context context, ArrayList<User> users) {
        this.context = context;
        this.userArrayList = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserListAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_adapter_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(userArrayList.get(position).getName());
        holder.userNameTextView.setText(userArrayList.get(position).getUsername());
        holder.email.setText(userArrayList.get(position).getEmail());
        holder.phn.setText(userArrayList.get(position).getPhone());
        holder.web.setText(userArrayList.get(position).getWebsite());
        String address = userArrayList.get(position).getAddress();
        String companyDetails = userArrayList.get(position).getCompany();
        setUpAddressAndCompanyDetails(holder, address, companyDetails);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AlbumsActivity.class);
                intent.putExtra(User.ID, userArrayList.get(position).getId());
                intent.putExtra(User.NAME, userArrayList.get(position).getName());
                context.startActivity(intent);
            }
        });
    }

    private void setUpAddressAndCompanyDetails(@NonNull ViewHolder holder, String address, String companyDetails) {
        if (!TextUtils.isEmpty(address)) {
            try {
                JSONObject object = new JSONObject(address);
                String geoCodes = "";

                if (object != null) {
                    Address address1 = new Address();
                    if (object.has(Address.STREET)) {
                        address1.setStreet(object.getString(Address.STREET));
                    }
                    if (object.has(Address.SUITE)) {
                        address1.setSuite(object.getString(Address.SUITE));
                    }
                    if (object.has(Address.CITY)) {
                        address1.setCity(object.getString(Address.CITY));
                    }
                    if (object.has(Address.ZIP_CODE)) {
                        address1.setZipcode(object.getString(Address.ZIP_CODE));
                    }
                    if (object.has(Address.GEO)) {
                        address1.setGeo(object.getString(Address.GEO));
                    }
                    holder.address.setText(address1.getStreet() + "\n" + address1.getSuite() + "\n" + address1.getCity() + "\n" + address1.getZipcode());
                    geoCodes = address1.getGeo();
                }

                JSONObject geoObject = new JSONObject(geoCodes);
                if (geoObject != null) {
                    GeoCodes codes = new GeoCodes();
                    if (geoObject.has(GeoCodes.LAT)) {
                        codes.setLat(geoObject.getString(GeoCodes.LAT));
                    }
                    if (geoObject.has(GeoCodes.LNG)) {
                        codes.setLng(geoObject.getString(GeoCodes.LNG));
                    }
                    holder.latLng.setText(codes.getLat() + ".ltd  " + codes.getLng() + ".lng");
                }

                JSONObject companyObject = new JSONObject(companyDetails);
                if (companyObject != null) {
                    Company company = new Company();
                    if (companyObject.has(Company.NAME)) {
                        company.setName(companyObject.getString(Company.NAME));
                        holder.companyName.setText(company.getName());
                    } else {
                        holder.companyName.setVisibility(View.GONE);
                    }
                    if (companyObject.has(Company.CATCH_PHRASE)) {
                        company.setCatchPhrase(companyObject.getString(Company.CATCH_PHRASE));
                        holder.phrase.setText(company.getCatchPhrase());
                    } else {
                        holder.phrase.setVisibility(View.GONE);
                    }
                    if (companyObject.has(Company.BS)) {
                        company.setBs(companyObject.getString(Company.BS));
                        holder.bs.setText(company.getBs());
                    } else {
                        holder.bs.setVisibility(View.GONE);
                    }

                }

            } catch (Exception e) {
            }
        }
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView parent;
        TextView userNameTextView, name, email, address, latLng, companyName, phrase, bs, web, phn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            userNameTextView = itemView.findViewById(R.id.user_name);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            address = itemView.findViewById(R.id.address);
            latLng = itemView.findViewById(R.id.lat_lng);
            companyName = itemView.findViewById(R.id.company);
            phrase = itemView.findViewById(R.id.phrase);
            bs = itemView.findViewById(R.id.bm);
            web = itemView.findViewById(R.id.web);
            phn = itemView.findViewById(R.id.phone);
        }
    }
}
