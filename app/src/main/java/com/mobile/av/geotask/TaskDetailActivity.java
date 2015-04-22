package com.mobile.av.geotask;

import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;

import com.mobile.av.geotask.adapters.TaskListArrayAdapter;
import com.mobile.av.geotask.db.TaskDBOpenHelper;
import com.mobile.av.geotask.db.TaskDataSource;
import com.mobile.av.geotask.model.Task;

import static com.mobile.av.geotask.RemoveTaskDialogFragment.Listener;


public class TaskDetailActivity extends ActionBarActivity implements Listener {

    private TaskDataSource dataSource;
    private Task task;
    private int taskPosition;

    public static final String REMOVE_DIALOG = "removeDialog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        // Action Bar
        setTitle(Html.fromHtml("<font color='#12cdc2'> " + task.getTitle() + " </font>"));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
        actionBar.setElevation(10);

        // Task Parcelable
        Bundle bundle = getIntent().getExtras();
        taskPosition = bundle.getInt(TaskListArrayAdapter.POSITION);
        task = bundle.getParcelable(".model.Task");

        // List Item Detail Fragment
        ItemDetailFragment itemDetailFragment = new ItemDetailFragment();
        itemDetailFragment.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.task_detail_activity, itemDetailFragment);
        transaction.commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_task_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        switch (id) {
            case R.id.action_task_delete:
                RemoveTaskDialogFragment removeTaskDialogFragment = new RemoveTaskDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putString(TaskDBOpenHelper.TASK_TITLE, task.getTitle());
                bundle.putInt(TaskListArrayAdapter.POSITION, taskPosition);

                removeTaskDialogFragment.setArguments(bundle);
                removeTaskDialogFragment.setListener(this);
                removeTaskDialogFragment.show(getFragmentManager(), REMOVE_DIALOG);
                break;
            case R.id.action_task_edit:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void returnData(int result) {
        dataSource = new TaskDataSource(this);
        dataSource.open();
        dataSource.deleteTask(task.getTask_id());
        dataSource.close();
        finish();
    }
}
