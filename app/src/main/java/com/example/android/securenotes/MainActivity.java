package com.example.android.securenotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.securenotes.R.id.con;
import static com.example.android.securenotes.R.id.fab;

public class MainActivity extends AppCompatActivity {
    EditText note_id, title, note;
    CardView cardView;
    ListView listView;
    List<note> mylist;
    DatabaseHandler db;
    AppAdapter adapter;
    private CoordinatorLayout cdl;
    private boolean isOpen = false;
    TextView view_id;
    TextView view_title;
    TextView view_note;
    TextView view_time;
    TextView txtid,txttitle,txtnote;
    ActionMode actionMode;
    List<note> noteList = new ArrayList<note>();
    int count=0;
    GridView grid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        note_id = (EditText) findViewById(R.id.note_id);
        title = (EditText) findViewById(R.id.title);
        note = (EditText) findViewById(R.id.note);
        listView = (ListView) findViewById(R.id.list_item);
        registerForContextMenu(listView);
        db = new DatabaseHandler(this);
        cdl = (CoordinatorLayout) findViewById(R.id.actioncontainer);
        view_id = (TextView) findViewById(R.id.view_id);
        view_title = (TextView) findViewById(R.id.view_title);
        view_note = (TextView) findViewById(R.id.view_note);
        view_time=(TextView) findViewById(R.id.view_time);
        grid = (GridView) findViewById(R.id.grid);

        load();

        FloatingActionButton fab1 = (FloatingActionButton) findViewById(fab);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), add_new_note.class));
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent in = new Intent(getApplicationContext(), ViewNote.class);
                in.putExtra("id", mylist.get(i).get_id() + "");
                in.putExtra("title", mylist.get(i).get_title().toString());
                in.putExtra("note", mylist.get(i).get_note().toString());
                //in.putExtra("time",mylist.get(i).getDateTime().toString());
                startActivity(in);

            }
        });
//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                    registerForContextMenu(listView);
//                    return true;
//            }
//        });
 }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //menu.setHeaderTitle("Select the action");
        menu.add(0,v.getId(),0,"Edit");
        menu.add(0,v.getId(),0,"Delete");
        super.onCreateContextMenu(menu, v, menuInfo);

//        if (v.getId()==R.id.list_view) {
//            MenuInflater inflater = getMenuInflater();
//            inflater.inflate(R.menu.contextual_menu, menu);
//        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AppAdapter info = (AppAdapter) item.getMenuInfo();
        //int index=info.getItemId();

        if (item.getTitle()=="Edit")
        {
            startActivity(new Intent(getApplicationContext(),add_new_note.class));
        }
        else if(item.getTitle()=="Delete")
        {

            //db.deleteNote(new note(Integer.parseInt(note_id.getText().toString()), title.getText().toString(), note.getText().toString()));
            db.deleteNote(new note(Integer.parseInt(note_id.getText().toString()), title.getText().toString(), note.getText().toString()));

            load();
            Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
       // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(),"Settings selected",Toast.LENGTH_SHORT).show();
            return true;
        }

        if(id==R.id.grid)
        {
            listView.setVisibility(View.GONE);
            grid.setVisibility(View.VISIBLE);

        }

        if(id == con) {
            Toast.makeText(getApplicationContext(), "Delete option selected", Toast.LENGTH_SHORT).show();
            //  db.deleteNote(new note(Integer.parseInt(note_id.getText().toString()), title.getText().toString(), note.getText().toString()));
            return true;
        }


        return super.onOptionsItemSelected(item);
    }



    public void load() {

        List<note> list = db.getAllNotes();
        mylist = list;
        adapter = new AppAdapter();
        listView.setAdapter(adapter);
//        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
//        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
//            @Override
//            public void onItemCheckedStateChanged(android.view.ActionMode actionMode, int i, long l, boolean b) {
//                if (b){
//
//                    noteList.add(mylist.get(i));
//                    count++;
//                    actionMode.setTitle(count + " Selected");
//                }
//                else {
//
//                    noteList.remove(mylist.get(i));
//                    count--;
//                }
//            }
//
//            @Override
//            public boolean onCreateActionMode(android.view.ActionMode actionMode, Menu menu) {
//                MenuInflater menuInflater=getMenuInflater();
//                menuInflater.inflate(contextual_menu,menu);
//                return true;
//            }
//
//            @Override
//            public boolean onPrepareActionMode(android.view.ActionMode actionMode, Menu menu) {
//                return false;
//            }
//
//            @Override
//            public boolean onActionItemClicked(android.view.ActionMode actionMode, MenuItem menuItem) {
//
//                if (menuItem.getItemId()==R.id.con){
//                    Toast.makeText(getApplicationContext(),"Delete Selected",Toast.LENGTH_SHORT).show();
//
//                    for (Object item : noteList)
//                    {
//                        String Item=item.toString();
//                        noteList.remove(Item);
//
//                    }
//                  // db.deleteNote(new note(Integer.parseInt(note_id.getText().toString()), title.getText().toString(), note.getText().toString()));
//                    adapter.notifyDataSetChanged();
//                    actionMode.finish();
//                    return true;
//
//                }
//
//                return true;
//                }
//
//            @Override
//            public void onDestroyActionMode(android.view.ActionMode actionMode) {
//                    count=0;
//                    noteList.clear();
//            }
//        });

        Toast.makeText(getApplicationContext(), mylist.size() + "", Toast.LENGTH_LONG).show();

    }




    class AppAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mylist.size();
        }

        @Override
        public String getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(getApplicationContext(),
                        R.layout.list_item, null);
                new ViewHolder(convertView);
            }

//            if (convertView == null)
//            {
//                convertView = View.inflate(getApplicationContext(),
//                        R.layout.grid_item,null);
//                new ViewHolder(convertView);
//            }


            final ViewHolder holder = (ViewHolder) convertView.getTag();

            holder.tv_id.setText(mylist.get(position).get_id() + "");
            holder.tv_name.setText(mylist.get(position).get_title());
            holder.tv_num.setText(mylist.get(position).get_note());
            //holder.tv_time.setText(mylist.get(position).getDateTime().toString());

            //  This code is working but temporary disables to try something

//            holder.row.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    Intent i = new Intent(getApplicationContext(), ViewNote.class);
//                    i.putExtra("id", mylist.get(position).get_id() + "");
//                    i.putExtra("title", mylist.get(position).get_title().toString());
//                    i.putExtra("note", mylist.get(position).get_note().toString());
//                    startActivity(i);
//                }
//            });


//            holder.row.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View view) {
//
//                    txtid.setText(mylist.get(position).get_id() + "");
//                    txttitle.setText(mylist.get(position).get_title());
//                    txtnote.setText(mylist.get(position).get_note());
//                                        return true;
//                }
//            });




            return convertView;

        }


        class ViewHolder {
            TextView tv_id;
            TextView tv_name;
            TextView tv_num;
            TextView tv_time;
            LinearLayout row;


            public ViewHolder(View view) {
                tv_id = (TextView) view.findViewById(R.id.note_id);
                tv_name = (TextView) view.findViewById(R.id.title);
                tv_num = (TextView) view.findViewById(R.id.note);
                //tv_time=(TextView) view.findViewById(R.id.c_time);
                row = (LinearLayout) view.findViewById(R.id.row);
                view.setTag(this);
            }
        }
    }
}
