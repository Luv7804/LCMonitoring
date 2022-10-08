package com.example.lcmonitoring;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class TakerFragment extends Fragment {
TextInputEditText taker_substationName,taker_substationFeeder,taker_Reason,taker_otherDetail,taker_CUGNumber;
Button takePermit_button;
FirebaseAuth mAuth;
    public TakerFragment() {
        // Required empty public constructor
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_taker, null);
        // Inflate the layout for this fragment
     takePermit_button =(Button) root.findViewById(R.id.takePermit_button);
     takePermit_button.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

//             Toast.makeText(getActivity(), "Button Clicked", Toast.LENGTH_SHORT).show();
             taker_substationName = (TextInputEditText)root.findViewById(R.id.substationName);
             taker_substationFeeder = (TextInputEditText)root.findViewById(R.id.substationFeeder);
             taker_Reason = (TextInputEditText)root.findViewById(R.id.Reason);
             taker_otherDetail = (TextInputEditText)root.findViewById(R.id.otherDetail);
             taker_CUGNumber = (TextInputEditText)root.findViewById(R.id.CUGNUmber);
             takePermit_button = (Button)root.findViewById(R.id.takePermit_button);
             String substationName = taker_substationName.getText().toString().trim();
             String substationFeeder = taker_substationFeeder.getText().toString().trim();
             String Reason = taker_Reason.getText().toString().trim();
             String otherDetail = taker_otherDetail.getText().toString().trim();
             String CUGNumber = taker_CUGNumber.getText().toString().trim();
             if(substationName.isEmpty()){
                 taker_substationName.setError("SubstationFeeder is Required !");
                 taker_substationName.requestFocus();
                 return;
             }if(substationName.length() < 3 || substationName.length() > 20){
                 taker_substationName.setError("Length Should Between 3 To 20");
                 taker_substationName.requestFocus();
                 return;
             }if(substationFeeder.isEmpty()){
                 taker_substationFeeder.setError("SubstationFeeder is Required !");
                 taker_substationFeeder.requestFocus();
                 return;
             }if(substationFeeder.length() < 3 || substationFeeder.length() > 20){
                 taker_substationFeeder.setError("Length Should Between 3 To 20");
                 taker_substationFeeder.requestFocus();
                 return;
             }
             if(Reason.isEmpty()){
                 taker_Reason.setError("Reason For LCP is Required !");
                 taker_Reason.requestFocus();
                 return;
             }if(CUGNumber.isEmpty()){
                 taker_CUGNumber.setError("Substation CUG Number is Required !");
                 taker_CUGNumber.requestFocus();
                 return;
             }if(CUGNumber.length() != 10){
                 taker_CUGNumber.setError("Enter 10 Digit Number !");
                 taker_CUGNumber.requestFocus();
                 return;
             }
//             FirebaseDatabase database = FirebaseDatabase.getInstance();
//             DatabaseReference myRef = database.getReference("Taker");

             Taker taker = new Taker(substationName,substationFeeder,Reason,otherDetail,CUGNumber);
//                   DatabaseReference realTimeData = database.getReference("User").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());
//                  myRef.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>()
             FirebaseDatabase.getInstance().getReference("Taker").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).setValue(taker).addOnCompleteListener(new OnCompleteListener<Void>(){
                 @Override
                 public void onComplete(@NonNull Task<Void> task) {
                     if(task.isSuccessful()) {
//                                        sendverificationcode(CUGNUmber);
                         Toast.makeText(getActivity(), "Data Stored successfully", Toast.LENGTH_LONG).show();
//                    progressBar.setVisibility(View.GONE);
                         Intent intent = new Intent(getActivity(), GiverFragment.class);
                                        intent.putExtra("CUGNumber",taker_CUGNumber.toString());
                         startActivity(intent);
                     }
                     else{
                         Toast.makeText(getActivity(), "Failed to Store! Try again!", Toast.LENGTH_LONG).show();
//                    progressBar.setVisibility(View.GONE);
                     }
                 }
             });
         }
     });
        takePermit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String CUGNumber=taker_CUGNumber.getText().toString();
                String msg = "Please Take Permit For Line Clearing";
                Intent intent=new Intent(getActivity(),PendingLCFragment.class);
                PendingIntent pi=PendingIntent.getActivity(getActivity(), 0, intent,0);
                SmsManager sms=SmsManager.getDefault();
                sms.sendTextMessage(CUGNumber, null, msg, pi,null);
                Toast.makeText(getActivity(), "Message Sent successfully!",
                        Toast.LENGTH_LONG).show();
            }
        });
        return root;

    }
}