package com.kanitkorn.android.anything;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private ToggleButton toggleButton1, toggleButton2;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnToggleButton();
        addListenerOnEditText();
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
