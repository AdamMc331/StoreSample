package com.adammcneilly.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create our adapter
        final StoreAdapter storeAdapter = new StoreAdapter(this, getSampleStores());

        // Get Listview
        ListView listView = (ListView) findViewById(R.id.store_list_view);

        // Set adapter
        listView.setAdapter(storeAdapter);

        // Set click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Store store = (Store) storeAdapter.getItem(position);
                Intent intent = new Intent(MainActivity.this, StoreDetailActivity.class);
                intent.putExtra(StoreDetailActivity.ARG_STORE_ID, store.getId());
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storeAdapter.add(new Store(5, "Adam", "111-111-1111", "11111 Telegraph"));
            }
        });
    }

    @Override
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private List<Store> getSampleStores() {
        List<Store> stores = new ArrayList<>();

        stores.add(new Store(1, "Qdoba", "123-123-1234", "12345 Walton"));
        stores.add(new Store(2, "Macys", "123-222-5554", "12345 University"));
        stores.add(new Store(3, "Blah", "123-hah-1234", "12345 Adams"));

        return stores;
    }
}
