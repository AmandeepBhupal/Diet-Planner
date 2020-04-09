package com.example.mobapplicationdev;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SignUpInfo extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    EditText etAge,etHeight;
    RadioGroup rg;
    RadioButton rb;
    float cals,prot,carb,fat;
    int minteger = 1;
    Button btnMinus, btnPlus;
    Button btnCalc;
    Button BtnDone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_info);

        firebaseAuth = FirebaseAuth.getInstance();
        rg = findViewById(R.id.radioGroup);
        etAge = findViewById(R.id.UserAge);
        etHeight = findViewById(R.id.userHeight);
        BtnDone = findViewById(R.id.buttonOK);


        btnCalc = findViewById(R.id.checkGoals);
        btnMinus = findViewById(R.id.decrease);
        btnPlus = findViewById(R.id.increase);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String currentWeight = ((EditText)(findViewById(R.id.editTextWeight))).getText().toString();
                String targetWeight = ((EditText)(findViewById(R.id.editTextWeightTarget))).getText().toString();

                TextView calories = findViewById(R.id.textResultCalc);

                if(minteger == 0 || Integer.parseInt(currentWeight) == Integer.parseInt(targetWeight)){
                    cals = 2000;
                    prot = (float) (cals*(0.2)/4);
                    carb = (float) (cals*(0.55)/4);
                    fat = (float) (cals*(0.25)/9);

                    calories.setText(cals+"");

                }
                else if(minteger == 1 && Integer.parseInt(currentWeight) > Integer.parseInt(targetWeight)){
                    cals = 1700;
                    prot = (float) (cals*(0.2)/4);
                    carb = (float) (cals*(0.55)/4);
                    fat = (float) (cals*(0.25)/9);

                    calories.setText(cals+"");

                }
                else if(minteger == 2 && Integer.parseInt(currentWeight) > Integer.parseInt(targetWeight)){
                    cals = 1500;
                    prot = (float) (cals*(0.2)/4);
                    carb = (float) (cals*(0.55)/4);
                    fat = (float) (cals*(0.25)/9);

                    calories.setText(cals+"");

                }
                else if(minteger == 1 && Integer.parseInt(currentWeight) < Integer.parseInt(targetWeight)){
                    cals = 2300;
                    prot = (float) (cals*(0.2)/4);
                    carb = (float) (cals*(0.55)/4);
                    fat = (float) (cals*(0.25)/9);

                    calories.setText(cals+"");

                }
                else if(minteger == 2 && Integer.parseInt(currentWeight) < Integer.parseInt(targetWeight)){
                    cals = 2500;
                    prot = (float) (cals*(0.2)/4);
                    carb = (float) (cals*(0.55)/4);
                    fat = (float) (cals*(0.25)/9);

                    calories.setText(cals+"");

                }
            }
        });


        BtnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_id = firebaseAuth.getCurrentUser().getUid();
                DatabaseReference current_userdb = FirebaseDatabase.getInstance().getReference().child("users").child(user_id).child("userinfo");
                FirebaseDb fb = new FirebaseDb();
                Intent in = getIntent();
                Bundle b = in.getExtras();
                String fname = b.getString("firstname");
                String lname = b.getString("lastname");
                fb.firstname = fname;
                fb.lastname = lname;
                fb.age = etAge.getText().toString();
                fb.height = etHeight.getText().toString();
                fb.gender = rb.getText().toString();
                fb.targetCalorie = Math.round(cals);
                fb.targetProtein = Math.round(prot);
                fb.targetCarbs = Math.round(carb);
                fb.targetFat = Math.round(fat);
                fb.targetWater = 10;
                current_userdb.setValue(fb);


                DateFormat dateformatter = new SimpleDateFormat("MMddyyyy");
                dateformatter.setLenient(false);
                Date today = new Date();
                final String datetoday = dateformatter.format(today);

                DatabaseReference current_health_userdb = FirebaseDatabase.getInstance().getReference().child("users").child(user_id).child("userhealthinfo").child(datetoday);
                Map newUserHealthinfo = new HashMap();
                newUserHealthinfo.put("fat",0);
                newUserHealthinfo.put("calories",0);
                newUserHealthinfo.put("carbs",0);
                newUserHealthinfo.put("water",0);
                newUserHealthinfo.put("protien",0);
                current_health_userdb.setValue(newUserHealthinfo);

                Intent i = new Intent(SignUpInfo.this,MainActivity.class);

                startActivity(i);
            }
        });

    }

    public void increaseInteger(View view) {
        minteger = minteger + 1;
        display(minteger);
        if(minteger == 2){
            btnPlus.setClickable(false);
        }
        if(minteger > 0){
            btnMinus.setClickable(true);
        }
    }
    public void decreaseInteger(View view) {
        minteger = minteger - 1;
        if(minteger == 0){
            btnMinus.setClickable(false);
        }
        if(minteger < 3){
            btnPlus.setClickable(true);
        }
        display(minteger);
    }
    private void display(int number) {
        TextView displayInteger = findViewById(
                R.id.integer_number);
        displayInteger.setText("" + number);
    }

    public void rbClick(View v){
        int radioButtonid = rg.getCheckedRadioButtonId();
        rb = findViewById(radioButtonid);
        Toast.makeText(this, rb.getText(), Toast.LENGTH_SHORT).show();
    }

}
