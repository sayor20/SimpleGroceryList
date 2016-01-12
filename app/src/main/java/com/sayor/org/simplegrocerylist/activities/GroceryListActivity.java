package com.sayor.org.simplegrocerylist.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.sayor.org.simplegrocerylist.R;
import com.sayor.org.simplegrocerylist.adapters.GroceryAdapter;
import com.sayor.org.simplegrocerylist.fragments.AddItemsFragment;
import com.sayor.org.simplegrocerylist.models.Grocery;
import com.sayor.org.simplegrocerylist.models.GroceryList;

import java.util.List;

public class GroceryListActivity extends AppCompatActivity implements AddItemsFragment.ListUpdateCallback{

    private FloatingActionButton fab;
    private RecyclerView rvGroceryList;
    private GroceryAdapter groceryAdapter;
    List<Grocery> groceries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        GroceryList groceryList = GroceryList.get(this);
        groceries = groceryList.getmGroceries();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
                AddItemsFragment df = new AddItemsFragment();
                df.show(manager, "add_dialog");
            }
        });

        rvGroceryList = (RecyclerView) findViewById(R.id.rvGroceryList);
        rvGroceryList.setLayoutManager(new LinearLayoutManager(this));
        rvGroceryList.setHasFixedSize(true);
        rvGroceryList.setItemAnimator(new DefaultItemAnimator());
        rvGroceryList.getItemAnimator().setAddDuration(1000);
        updateUI();
    }

    public void onResume(){
        super.onResume();
        updateUI();
    }
    private void updateUI() {
        if (groceryAdapter == null) {
            groceryAdapter = new GroceryAdapter(groceries);
            rvGroceryList.setAdapter(groceryAdapter);
        } else {
            groceryAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_grocery_list, menu);
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

    // custom callback for AddItemsFragment
    @Override
    public void rvUpdateCallback() {
        groceryAdapter.notifyItemInserted(groceryAdapter.getItemCount());
    }
}
