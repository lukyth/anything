package com.kanitkorn.android.anything;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener {

    private static final String LOGCAT = null;

    private ToggleButton toggleButton1, toggleButton2;
    private EditText editText, editText1;
    private Button buttonPopupMenu;

    private RadioGroup radioGroup;

    private Spinner spinner;

    private List<String> listAutoCompleteRuntime;
    private AutoCompleteTextView autoCompleteTextViewRuntime;

    private String[] celebrities = {
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

    private String str[] = {"Arun", "Mathev", "Vishnu", "Vishal", "Arjun", "Arul", "Balaji", "Babu", "Boopathy", "Godwin", "Nagaraj"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.textViewDrag).setOnTouchListener(this);
        findViewById(R.id.pinkLayout).setOnDragListener(this);
        findViewById(R.id.yellowLayout).setOnDragListener(this);

        TextView textViewBackgroundImage = (TextView) findViewById(R.id.textViewBackgroundImage);
        textViewBackgroundImage.setBackgroundResource(R.drawable.text_bg);
        final float scale =  getBaseContext().getResources().getDisplayMetrics().density;
        textViewBackgroundImage.setLayoutParams(new LayoutParams((int) (120 * scale + 0.5f), (int) (40 * scale + 0.5f)));

        addListenerOnRadioButton();

        addListenerOnLongPressButton();

        addListenerOnPopupMenu();

        addListenerOnToggleButton();

        addListenerOnEditText();

        addListenerOnSpinner();

        initAutoCompleteStatic();
        initAutoCompleteDynamic();
        initAutoCompleteRuntime();

    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
            view.startDrag(null, shadowBuilder, view, 0);
            view.setVisibility(View.INVISIBLE);
            return true;
        } else {
            return false;
        }
    }

    public boolean onDrag(View layoutView, DragEvent dragEvent) {
        int action = dragEvent.getAction();
        switch (action) {
            case DragEvent.ACTION_DRAG_STARTED:
                Log.d(LOGCAT, "Drag event started");
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                Log.d(LOGCAT, "Drag event entered into " + layoutView.toString());
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                Log.d(LOGCAT, "Drag event exited from " + layoutView.toString());
                break;
            case DragEvent.ACTION_DROP:
                Log.d(LOGCAT, "Dropped");
                View view = (View) dragEvent.getLocalState();
                ViewGroup owner = (ViewGroup) view.getParent();
                owner.removeView(view);
                LinearLayout container = (LinearLayout) layoutView;
                container.addView(view);
                view.setVisibility(View.VISIBLE);
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                Log.d(LOGCAT, "Drag ended");
                break;
            default:
                break;
        }
        return true;
    }

    public void addListenerOnLongPressButton() {
        Button buttonLongPress = (Button) findViewById(R.id.buttonLongPress);
        buttonLongPress.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "You have pressed it long :)", Toast.LENGTH_LONG).show();
                return true;
            }
        });

        buttonLongPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "Not Long Enough :(", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void addListenerOnPopupMenu() {
        buttonPopupMenu = (Button) findViewById(R.id.buttonPopupMenu);
        buttonPopupMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the instance of PopupMenu
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, buttonPopupMenu);


                // Inflating the popup using XML file
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

                // Registering Popup
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(MainActivity.this, "You clicked : " + item.getTitle(), Toast.LENGTH_LONG).show();
                        return true;
                    }
                });

                // groupID, itemID, order, title
                popupMenu.getMenu().add(1, 4, 4, "Four");

                popupMenu.show();
            }
        });
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
        editText1 = (EditText) findViewById(R.id.editText1);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        switch (item.getItemId()) {
            case R.id.redMenu:
                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }
                linearLayout.setBackgroundColor(Color.RED);
                return true;
            case R.id.greenMenu:
                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }
                linearLayout.setBackgroundColor(Color.GREEN);
                return true;
            case R.id.blueMenu:
                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }
                linearLayout.setBackgroundColor(Color.BLUE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
