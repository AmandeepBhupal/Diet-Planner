package com.example.mobapplicationdev;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.mobapplicationdev.FirebaseHealthdb;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class HistoryFragment extends Fragment {
    @Nullable
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseCoordinates1;
    int minteger = 0;
    GraphView graph;
    Button btnMinus, btnPlus;
    ArrayList<String> stringsForDates = new ArrayList<String>();
    HashMap<String,Integer> calMap = new HashMap<String,Integer>();
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, null);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RadioGroup history;
        RadioButton month;
        RadioButton week;
        RadioButton day;
        graph = (GraphView) getView().findViewById(R.id.graph);

        firebaseAuth = FirebaseAuth.getInstance();

        String user_id = firebaseAuth.getCurrentUser().getUid();

        DateFormat dateformatter = new SimpleDateFormat("MMddyyyy");
        dateformatter.setLenient(false);

        for(int i = 7; i >=0 ; i--) {
            Date today = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(today);
            cal.add(Calendar.DATE, -i);
            Date dateBefore7Days = cal.getTime();
            final String date7 = dateformatter.format(dateBefore7Days);
            stringsForDates.add(date7);
        }



        databaseCoordinates1 = FirebaseDatabase.getInstance().getReference().child("users").child(user_id).child("userhealthinfo");
        databaseCoordinates1.addValueEventListener(new ValueEventListener() {
            FirebaseHealthdb details;


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int num = 0;
                for(String dateForProcessing: stringsForDates) {
                    details = dataSnapshot.child(dateForProcessing).getValue(FirebaseHealthdb.class);
                    // Do something with the retrieved data or Bruce Wayne
                    int cal;
                    if (details != null) {
                        cal = details.calories;
                    } else {
                        cal = 0;
                    }
                    calMap.put(dateForProcessing,cal);
                }

                TreeMap<String, Integer> sorted = new TreeMap<>();
                // Copy all data from hashMap into TreeMap
                sorted.putAll(calMap);

                DataPoint[] dp = new DataPoint[8];
                int no = 0;
                for(String dates:sorted.keySet()){
                    dp[no] = new DataPoint(no+1,sorted.get(dates));
                    no++;
                }


                LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dp);
                graph.addSeries(series);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("UserListActivity", "Error occured");
                // Do something about the error
            }});




    }


}