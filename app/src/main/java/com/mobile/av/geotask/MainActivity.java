package com.mobile.av.geotask;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;

import com.mobile.av.geotask.adapters.TaskListArrayAdapter;
import com.mobile.av.geotask.model.Task;


public class MainActivity extends ActionBarActivity implements TaskListFragment.OnListItemSelectedListener {

    TaskListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Custom Action Bar
        setTitle(Html.fromHtml("<font color='#12cdc2'> Tasks </font>"));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
        actionBar.setElevation(10);
    }

    @Override
    protected void onResume() {
        super.onResume();

        listFragment = new TaskListFragment();
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, listFragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent prefsIntent = new Intent(this,PrefsActivity.class);
            startActivity(prefsIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClicked(int position, Task task) {
        Intent listItemClickIntent = new Intent(MainActivity.this, TaskDetailActivity.class);
        listItemClickIntent.putExtra(TaskListArrayAdapter.POSITION, position);
        listItemClickIntent.putExtra(".model.Task", task);
        startActivity(listItemClickIntent);
    }
}
