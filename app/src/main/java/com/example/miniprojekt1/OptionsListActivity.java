package com.example.miniprojekt1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;

public class OptionsListActivity extends AppCompatActivity {
    private static final String myIntent = "com.example.product.added";

    private static final String MY_PREFS_NAME = "MySharedPreferences";
    private static final String DARK_BACKGROUND_TEXT = "dark_background";
    private static final String DARK_BACKGROUND_TEXT_CHECKED = "dark_background_checked";
    private static final String FONT_SIZE_TEXT = "font_size";

    private Switch darkBackgroundSwitch;
    private Spinner fontSizeSpinner;
    private String[] FONT_SIZE_OPTIONS = new String[]{"13", "20", "25"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_list);
                createFontSizeOptions(0);

        Button goBackFromProductList = findViewById(R.id.go_back_from_options_list);
        goBackFromProductList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack(view);
            }
        });

        fontSizeSpinner = findViewById(R.id.font_size_dropdown);
        fontSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                saveFontSizeToSharedPreferences(FONT_SIZE_OPTIONS[position]);
                createFontSizeOptions(position);
                loadSharedPreferencesData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        darkBackgroundSwitch = (Switch) findViewById(R.id.dark_background_switch);
        darkBackgroundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveDarkBackgroundToSharedPreferences(isChecked);
                loadSharedPreferencesData();
            }
        });
        loadSharedPreferencesData();
    }

    private void loadSharedPreferencesData() {
        SharedPreferences sharedPreferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.options_layout);
        cl.setBackgroundColor(sharedPreferences.getInt(DARK_BACKGROUND_TEXT, MODE_PRIVATE));

        String stringFontSize = sharedPreferences.getString(FONT_SIZE_TEXT, FONT_SIZE_OPTIONS[0]);
        int fontSize = Integer.parseInt(stringFontSize);
        darkBackgroundSwitch = (Switch) findViewById(R.id.dark_background_switch);
        darkBackgroundSwitch.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
        darkBackgroundSwitch.setChecked(sharedPreferences.getBoolean(DARK_BACKGROUND_TEXT_CHECKED, false));
        Button goBackFromOptionsListButton = (Button) findViewById(R.id.go_back_from_options_list);
        goBackFromOptionsListButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
        fontSizeSpinner = (Spinner) findViewById(R.id.font_size_dropdown);
        for (int i=0; i<FONT_SIZE_OPTIONS.length; i++) {
            if(FONT_SIZE_OPTIONS[i].equalsIgnoreCase(stringFontSize)) {
                createFontSizeOptions(i);
            }
        }
    }

    private void saveDarkBackgroundToSharedPreferences(boolean isChecked) {
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        if (isChecked) {
            editor.putInt(DARK_BACKGROUND_TEXT, Color.parseColor("#00574B"));
            editor.putBoolean(DARK_BACKGROUND_TEXT_CHECKED, true);
        } else {
            editor.putInt(DARK_BACKGROUND_TEXT, Color.parseColor("#008577"));
            editor.putBoolean(DARK_BACKGROUND_TEXT_CHECKED, false);
        }

        editor.apply();
    }

    private void saveFontSizeToSharedPreferences(String fontSize) {
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString(FONT_SIZE_TEXT, fontSize);

        editor.apply();
    }

    private void createFontSizeOptions(int position) {
        Spinner dropdown = findViewById(R.id.font_size_dropdown);
        String temp = FONT_SIZE_OPTIONS[0];
        FONT_SIZE_OPTIONS[0] = FONT_SIZE_OPTIONS[position];
        FONT_SIZE_OPTIONS[position] = temp;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, FONT_SIZE_OPTIONS);
        dropdown.setAdapter(adapter);
    }

    private void goBack(View view) {
        finish();
    }
}
