package com.example.user.mvvpapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditNoteActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "com.example.user.mvvpapp.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.user.mvvpapp.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.example.user.mvvpapp.EXTRA_PRIORITY";
    public static final String EXTRA_ID = "com.example.user.mvvpapp.EXTRA_ID";

    private EditText editTextTitle;
    private EditText editTextDeskription;
    private NumberPicker numberPickerPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDeskription = findViewById(R.id.edit_text_desription);
        numberPickerPriority = findViewById(R.id.number_picker_priority);

        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);
        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle(getString(R.string.edit_note));
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextDeskription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberPickerPriority.setValue(intent.getIntExtra(EXTRA_TITLE, 1));
        } else {
            setTitle(getString(R.string.add_note));
        }
    }

    private void saveNote() {
        String title = editTextTitle.getText().toString();
        String desription = editTextDeskription.getText().toString();
        int priority = numberPickerPriority.getValue();

        if (title.trim().isEmpty() || desription.trim().isEmpty()) {
            Toast.makeText(this, "Please Fill All Text", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_DESCRIPTION, desription);
        intent.putExtra(EXTRA_PRIORITY, priority);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if(id != -1){
            intent.putExtra(EXTRA_ID,id);
        }

        setResult(RESULT_OK, intent);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
