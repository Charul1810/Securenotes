package com.example.android.securenotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import static com.example.android.securenotes.R.id.fab1;

public class update_note extends AppCompatActivity {
    EditText note_id,title,note;
    ListView listView;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        note_id = (EditText) findViewById(R.id.note_id);
        title = (EditText) findViewById(R.id.title);
        note = (EditText) findViewById(R.id.note);
        listView = (ListView) findViewById(R.id.list_item);
        db = new DatabaseHandler(this);

        Bundle b = getIntent().getExtras();
        note_id.setText(b.getString("id", "1"));
        title.setText(b.getString("title"));
        note.setText(b.getString("note"));

        FloatingActionButton fab2 = (FloatingActionButton) findViewById(fab1);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (title.getText().toString().trim().length() > 0 && note.getText().toString().trim().length() > 0) {
                    update();
                    finish();
                }
                else {

                }
            }
        });
    }

    public void update(){

        db.updateNote(new note(Integer.parseInt(note_id.getText().toString()), title.getText().toString(), note.getText().toString()));
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        if (title.getText().toString().trim().length() > 0 && note.getText().toString().trim().length() > 0) {
           update();
//                    db.addNote(new note(title.getText().toString(), note.getText().toString(), formattedDate));

            //Toast.makeText(getApplicationContext(), formattedDate, Toast.LENGTH_LONG).show();


//                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
        else {

            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
        super.onBackPressed();
    }
}
