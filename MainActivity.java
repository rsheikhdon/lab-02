package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;



public class MainActivity extends AppCompatActivity {
    ListView cityList;
    EditText cityInput;
    Button addCityBtn, confirmCityBtn, deleteCityBtn;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        cityInput = findViewById(R.id.cityInput);
        addCityBtn = findViewById(R.id.addCityBtn);
        confirmCityBtn = findViewById(R.id.confirmCityBtn);
        deleteCityBtn = findViewById(R.id.deleteCityBtn);



        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin",
                "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        cityList.setOnItemClickListener((parent, view, position, id) ->{
            selectedPosition = position;
        } );


        addCityBtn.setOnClickListener(v -> {
            cityInput.setVisibility(View.VISIBLE);
            confirmCityBtn.setVisibility(View.VISIBLE);
        });

        // Confirms the inputted city
        confirmCityBtn.setOnClickListener(v -> {
            String newCity = cityInput.getText().toString().trim();

            if (!newCity.isEmpty()) {
                dataList.add(newCity);
                cityAdapter.notifyDataSetChanged();

                cityInput.setText("");
                cityInput.setVisibility(View.GONE);
                confirmCityBtn.setVisibility(View.GONE);
            }
        });

        // Deletes the selected city
        deleteCityBtn.setOnClickListener(v -> {
            if (selectedPosition != -1) {
                dataList.remove(selectedPosition);
                cityAdapter.notifyDataSetChanged();
                selectedPosition = -1;
            }
        });


    }
}