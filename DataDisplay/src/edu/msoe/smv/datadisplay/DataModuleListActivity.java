package edu.msoe.smv.datadisplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class DataModuleListActivity extends FragmentActivity
        implements DataModuleListFragment.Callbacks {

    private boolean mTwoPane;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datamodule_list);

        if (findViewById(R.id.datamodule_detail_container) != null) {
            mTwoPane = true;
            ((DataModuleListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.datamodule_list))
                    .setActivateOnItemClick(true);
        }
    }

    //@Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putString(DataModuleDetailFragment.ARG_ITEM_ID, id);
            DataModuleDetailFragment fragment = new DataModuleDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.datamodule_detail_container, fragment)
                    .commit();

        } else {
            Intent detailIntent = new Intent(this, DataModuleDetailActivity.class);
            detailIntent.putExtra(DataModuleDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}
