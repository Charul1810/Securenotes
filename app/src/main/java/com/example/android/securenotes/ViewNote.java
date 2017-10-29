package com.example.android.securenotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import static com.example.android.securenotes.R.id.fab;


public class ViewNote extends AppCompatActivity {
    TextView view_id;
    TextView view_title;
    TextView view_note;
    TextView view_time;
    List<note> mylist;
    //private static final DateFormat DATETIME_FORMAT = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);


        view_id = (TextView) findViewById(R.id.view_id);
        view_title = (TextView) findViewById(R.id.view_title);
        view_note = (TextView) findViewById(R.id.view_note);
        view_time= (TextView) findViewById(R.id.view_time);

        //time.setText(DATETIME_FORMAT.format(note.getCreatedAt()));


        Bundle b = getIntent().getExtras();
        view_id.setText(b.getString("id", "1"));
        view_title.setText(b.getString("title"));
        view_note.setText(b.getString("note"));
        view_time.setText(b.getString("time"));




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab3 = (FloatingActionButton) findViewById(fab);
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), update_note.class);
                i.putExtra("id",view_id.getText() + "");
                i.putExtra("title",view_title.getText().toString());
                i.putExtra("note",view_note.getText().toString());
                startActivity(i);
                finish();
                //startActivity(new Intent(getApplicationContext(), add_new_note.class));

            }
        });



    }

//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            Toast.makeText(getApplicationContext(),"Settings selected",Toast.LENGTH_SHORT).show();
//            return true;
//        }
//        if(id == R.id.con) {
//            Toast.makeText(getApplicationContext(), "Delete option selected", Toast.LENGTH_SHORT).show();
////            db.deleteNote(new note(Integer.parseInt(note_id.getText().toString()), title.getText().toString(), note.getText().toString()));
////            load();
//
//            return true;
//        }
//
//
//        return super.onOptionsItemSelected(item);
//    }


}
