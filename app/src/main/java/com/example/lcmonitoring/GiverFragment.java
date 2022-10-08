package com.example.lcmonitoring;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GiverFragment extends Fragment {
    FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private DatabaseReference logReference;
    public Object loginUser;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    Button workDone_button;
    TextView id,name,designation,CUGNumber,substation,substation_feeder,reason,date_time;


    public GiverFragment() {
        // Required empty public constructor
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_giver, null);
        View root = inflater.inflate(R.layout.fragment_giver, container, false);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        Log.d("MyActivity", "onCreateView: firebaseUser ::" + firebaseUser.getUid());

        databaseReference.child("User").child("2aRl9xV5JjWWeoJKkSNCEM0bGNv2").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    loginUser = task.getResult().getValue();

                    Log.d("firebase", String.valueOf(loginUser));
                    Log.d("firebase", "cugnumber :: " + loginUser.cugnumber);

//                    fetchRelatedTakersData(loginUser.cugnumber);
                }
            }
        });

//        String userID = user.getUid();
//        Toast.makeText(getActivity(), "UserID : "+userID, Toast.LENGTH_SHORT).show();
//        Intent intent = getIntent();

        id = (TextView) root.findViewById(R.id.giver_taker_id);
        name = (TextView) root.findViewById(R.id.giver_taker_name);
        designation = (TextView) root.findViewById(R.id.giver_taker_designation);
        CUGNumber = (TextView) root.findViewById(R.id.giver_taker_CUGNumber);
        substation = (TextView) root.findViewById(R.id.giver_taker_substation);
        substation_feeder = (TextView) root.findViewById(R.id.giver_taker_substation_feeder);
        reason = (TextView) root.findViewById(R.id.giver_taker_reason);
        date_time = (TextView) root.findViewById(R.id.giver_taker_date_time);

//        logReference = databaseReference.child("Taker").orderByChild("cugnumber").equalTo("9714562025");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Taker userDetail = snapshot.getValue(Taker.class);
                String ssFeeder = userDetail.substationFeeder;
                String Reason = userDetail.Reason;
                String Other = userDetail.otherDetail;
                String Name = userDetail.substationName;
//                String CUG = userDetail.CUGNUmber;
                Toast.makeText(getActivity(), userDetail.CUGNUmber, Toast.LENGTH_LONG).show();
                substation.setText(Name);
                CUGNumber.setText(userDetail.CUGNUmber);
                substation_feeder.setText(ssFeeder);
                reason.setText(Reason);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        return inflater.inflate(R.layout.fragment_giver, container, false);

    }

    public void fetchRelatedTakersData(String cugNumber) {

        //        logReference = databaseReference.child("Taker").orderByChild("cugnumber").equalTo("9714562025");

        databaseReference.child("Taker").orderByChild("cugnumber").equalTo(cugNumber).addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    loginUser = task.getResult().getValue();

                    Log.d("firebase", String.valueOf(loginUser));
                    Log.d("firebase", "cugnumber :: " + loginUser.getClass());

                    fetchRelatedTakersData(loginUser.cugnumber);
                }
            }
        });
    }

}