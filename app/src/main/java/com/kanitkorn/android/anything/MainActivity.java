package com.kanitkorn.android.anything;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ToggleButton toggleButton1, toggleButton2;
    private EditText editText;

    Spinner spinner;

    String[] celebrities = {
            "Chris Hemsworth",
            "Jennifer Lawrence",
            "Jessica Alba",
            "Brad Pitt",
            "Tom Cruise",
            "Johnny Depp",
            "Megan Fox",
            "Paul Walker",
            "Vin Diesel"
    };

    List<String> listAutoCompleteRuntime;
    AutoCompleteTextView autoCompleteTextViewRuntime;

    String str[] = {"Arun", "Mathev", "Vishnu", "Vishal", "Arjun", "Arul", "Balaji", "Babu", "Boopathy", "Godwin", "Nagaraj"};
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnRadioButton();

        addListenerOnToggleButton();

        addListenerOnEditText();

        addListenerOnSpinner();

        initAutoCompleteStatic();
        initAutoCompleteDynamic();
        initAutoCompleteRuntime();

    }

    public void addListenerOnRadioButton() {
        /* Initialize Radio Group and attach click handler */
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.clearCheck();

        /* Attach CheckedChangeListener to radio group */
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                if (null != radioButton && checkedId > -1) {
                    Toast.makeText(MainActivity.this, radioButton.getText(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void onClear(View v) {
        /* Clears all selected radio buttons to default */
        radioGroup.clearCheck();
    }

    public void onSubmit(View v) {
        RadioButton rb = (RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
        Toast.makeText(MainActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();
    }

    public void addListenerOnSpinner() {
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, celebrities);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                int position = spinner.getSelectedItemPosition();
                Toast.makeText(getApplicationContext(), "You have selected " + celebrities[+position], Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
    }

    public void initAutoCompleteStatic() {
        AutoCompleteTextView autoCompleteTextViewStatic = (AutoCompleteTextView)
                findViewById(R.id.autoCompleteTextView1);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, str);

        autoCompleteTextViewStatic.setThreshold(1);
        autoCompleteTextViewStatic.setAdapter(arrayAdapter);
    }

    public void initAutoCompleteDynamic() {

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        AutoCompleteTextView autoCompleteTextView = new AutoCompleteTextView(MainActivity.this);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        autoCompleteTextView.setLayoutParams(params);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, str);

        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(arrayAdapter);

        linearLayout.addView(autoCompleteTextView);
    }

    public void initAutoCompleteRuntime() {

        listAutoCompleteRuntime = new ArrayList<>();
        listAutoCompleteRuntime.add("Item 1");
        listAutoCompleteRuntime.add("Item 2");
        listAutoCompleteRuntime.add("Item 3");

        autoCompleteTextViewRuntime = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView2);
        final EditText editText1 = (EditText) findViewById(R.id.editText1);
        Button button1 = (Button) findViewById(R.id.button1);

        add();

        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                listAutoCompleteRuntime.add(editText1.getText().toString());
                editText1.setText(null);

                add();

                Toast.makeText(getBaseContext(), "Item Added",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void add() {

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, listAutoCompleteRuntime);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        autoCompleteTextViewRuntime.setThreshold(1);
        autoCompleteTextViewRuntime.setAdapter(arrayAdapter);
    }

    public void addListenerOnToggleButton() {
        toggleButton1 = (ToggleButton) findViewById(R.id.toggleButton1);
        toggleButton2 = (ToggleButton) findViewById(R.id.toggleButton2);
        Button btnDisplay = (Button) findViewById(R.id.btnToggleButtonDisplay);

        btnDisplay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String toggleButtonResult = "";
                toggleButtonResult += "toggleButton1 : " + toggleButton1.getText();
                toggleButtonResult += "\ntoggleButton2 : " + toggleButton2.getText();

                Toast.makeText(MainActivity.this, toggleButtonResult,
                        Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void addListenerOnEditText() {
        editText = (EditText) findViewById(R.id.editText);
        Button btnDisplay = (Button) findViewById(R.id.btnEditTextDisplay);

        btnDisplay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String str = editText.getText().toString();
                Toast msg = Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG);
                msg.show();
            }
        });
    }
}
