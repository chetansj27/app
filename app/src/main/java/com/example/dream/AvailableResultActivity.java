package com.example.dream;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AvailableResultActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference recyclerData;
    Double Latitude, Longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_result);
        recyclerView = findViewById(R.id.recyclerView);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading!!!😊");
        progressDialog.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                progressDialog.dismiss();

                recyclerData = FirebaseDatabase.getInstance().getReference().child("customers_data");
                recyclerData.keepSynced(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(AvailableResultActivity.this));
                FirebaseRecyclerOptions<AvailableVolCard> options = new FirebaseRecyclerOptions.Builder<AvailableVolCard>()
                        .setQuery(recyclerData, AvailableVolCard.class)
                        .build();
                FirebaseRecyclerAdapter<AvailableVolCard, DataViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<AvailableVolCard, DataViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull DataViewHolder holder, int i, @NonNull AvailableVolCard model) {
                        holder.name.setText(model.getName());

                    }

                    @NonNull
                    @Override
                    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.available_vol_card, viewGroup, false);
                        return new DataViewHolder(view);
                    }
                };
                recyclerView.setAdapter(firebaseRecyclerAdapter);
                firebaseRecyclerAdapter.startListening();


            }
        }, 3000);


    }

    public static class DataViewHolder extends RecyclerView.ViewHolder {
        TextView name, address, days, fromTime, toTime;
        Button call, details;


        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameTv);
            address = itemView.findViewById(R.id.addressTv);
            days = itemView.findViewById(R.id.daysTv);
            fromTime = itemView.findViewById(R.id.fromTv);
            toTime = itemView.findViewById(R.id.toTv);
            call = itemView.findViewById(R.id.callButton);
            details = itemView.findViewById(R.id.detailsButton);


        }
    }
}
