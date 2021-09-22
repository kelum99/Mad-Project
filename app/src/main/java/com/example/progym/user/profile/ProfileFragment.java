package com.example.progym.user.profile;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.progym.R;

public class ProfileFragment extends Fragment {

    TextView height_txt, age_text, weight_text;
    RadioButton rdbtn_little,rdbtn_light,rdbtn_mod,rdbtn_active,rdbtn_extra;
    float height, weight;
    int age;
    int set_weight = 50, set_age = 18;
    RelativeLayout weight_plus, weight_minus, age_plus, age_minus;
    boolean isMale, isFemale;
    boolean male_click = true, female_click = true, malecheck = true, femalecheck = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        height_txt = (TextView) view.findViewById(R.id.height_txt);

        CardView cd_female = view.findViewById(R.id.cv_female);
        CardView cd_male = view.findViewById(R.id.cv_male);

        TextView male_text = view.findViewById(R.id.male);
        TextView female_text = view.findViewById(R.id.female);

        weight_plus = view.findViewById(R.id.weight_plus);
        weight_minus = view.findViewById(R.id.weight_minus);
        age_plus = view.findViewById(R.id.age_plus);
        age_minus = view.findViewById(R.id.age_minus);

        rdbtn_little = view.findViewById(R.id.rdbtn_little);
        rdbtn_light = view.findViewById(R.id.rdbtn_light);
        rdbtn_mod = view.findViewById(R.id.rdbtn_mod);
        rdbtn_active = view.findViewById(R.id.rdbtn_active);
        rdbtn_extra = view.findViewById(R.id.rdbtn_extra);



        cd_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (malecheck) {

                    if (male_click) {

                        male_text.setTextColor(Color.parseColor("#06d6a0"));
                        male_click = false;
                        femalecheck = false;
                        isMale = true;
                        isFemale=false;


                    } else {

                        male_text.setTextColor(Color.parseColor("#000000"));
                        male_click = true;
                        femalecheck = true;
                        isMale = false;
                        isFemale=false;
                    }
                }
            }
        });

        cd_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (femalecheck) {

                    if (female_click) {

                        female_text.setTextColor(Color.parseColor("#06d6a0"));
                        female_click = false;
                        malecheck = false;
                        isFemale = true;
                        isMale=false;

                    } else {

                        female_text.setTextColor(Color.parseColor("#000000"));
                        female_click = true;
                        malecheck = true;
                        isFemale=false;
                        isMale=false;
                    }
                }
            }
        });

        ChangeSeekbar(view);
        ChangeWeight(view);
        ChangeAge(view);

        Button saveandview = view.findViewById(R.id.btn_profile);
        saveandview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calculations();
            }
        });

        return view;


    }

    private void ChangeSeekbar( View view) {

        SeekBar Seekbar = view.findViewById(R.id.Seekbar);
        Seekbar.setMax(30);
        Seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String ht = progress + getResources().getString(R.string.height_txt);
                height_txt.setText(ht);
                height = (float)(progress)/10;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void ChangeAge(View view) {

        age_text = view.findViewById(R.id.age_text);

        age_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_age++;
                age_text.setText(String.valueOf(set_age));
            }
        });

        age_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_age--;
                age_text.setText(String.valueOf(set_age));
            }
        });

        age = Integer.parseInt(age_text.getText().toString());
    }

    private void ChangeWeight(View view) {

        weight_text = view.findViewById(R.id.weight_text);

        weight_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_weight++;
                weight_text.setText(String.valueOf(set_weight));
            }
        });

        weight_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_weight--;
                weight_text.setText(String.valueOf(set_weight));
            }
        });

        weight = Float.parseFloat(weight_text.getText().toString());

    }

    private void Calculations(){
        float BMI = weight/(height * height);
        float BMR;
        float calBurned;
        float points;

        if(isMale){
            BMR = (float) (88.362 + (13.397 * weight)+(4.799 * (height*100)) - (5.677 * age));
        }
        else if(isFemale){
            BMR = (float) (447.593 + (9.247 * weight)+(3.098 * (height*100)) - (4.330 * age));
        }
        else{
            Toast.makeText(getActivity(),"Please Select Gender", Toast.LENGTH_SHORT).show();
            BMR = 0;
        }

        if(rdbtn_little.isChecked()){
            points = (float) 1.2;
        }
        else if(rdbtn_light.isChecked()){
            points = (float) 1.37;
        }
        else if(rdbtn_mod.isChecked()){
            points = (float) 1.55;
        }
        else if(rdbtn_active.isChecked()){
            points = (float) 1.725;
        }
        else if(rdbtn_extra.isChecked()){
            points = (float) 1.9;
        }
        else{
            Toast.makeText(getActivity(), "Please Select Workout Time", Toast.LENGTH_SHORT).show();
            points = 0;
        }

        calBurned = BMR * points;

        Intent intent = new Intent(getActivity(), ProfileResults.class);
        intent.putExtra("BMI",BMI);
        intent.putExtra("BMR",new Float(BMR).toString());
        intent.putExtra("Cal",new Float(calBurned).toString());
        startActivity(intent);


    }



}