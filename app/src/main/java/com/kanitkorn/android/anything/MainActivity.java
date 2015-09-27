package com.kanitkorn.android.anything;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private ToggleButton toggleButton1, toggleButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnButton();
    }

    public void addListenerOnButton() {

        toggleButton1 = (ToggleButton) findViewById(R.id.toggleButton1);
        toggleButton2 = (ToggleButton) findViewById(R.id.toggleButton2);
        Button btnDisplay = (Button) findViewById(R.id.btnDisplay);

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
}
