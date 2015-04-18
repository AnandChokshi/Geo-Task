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

        Bundle bundle = getIntent().getExtras();
        taskPosition = bundle.getInt(TaskListArrayAdapter.POSITION);

        ListItemDetailFragment listItemDetailFragment = new ListItemDetailFragment();
        listItemDetailFragment.setArguments(bundle);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.task_detail_activity, listItemDetailFragment);
        transaction.commit();

        task = bundle.getParcelable(".model.Task");

        setTitle(Html.fromHtml("<font color='#12cdc2'> " + task.getTitle() + " </font>"));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
        actionBar.setElevation(10);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_task_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.task_detail_edit) {
            return true;
        }

        if (id == R.id.task_detail_delete){

            RemoveTaskDialogFragment removeTaskDialogFragment = new RemoveTaskDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString(TaskDBOpenHelper.TASK_TITLE, task.getTitle());
            bundle.putInt(TaskListArrayAdapter.POSITION, taskPosition);

            removeTaskDialogFragment.setArguments(bundle);
            removeTaskDialogFragment.setListener(this);
            removeTaskDialogFragment.show(getFragmentManager(), REMOVE_DIALOG);
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
