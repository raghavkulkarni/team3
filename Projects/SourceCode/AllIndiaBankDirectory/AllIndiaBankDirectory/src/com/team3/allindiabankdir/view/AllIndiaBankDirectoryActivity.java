package com.team3.allindiabankdir.view;

import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.team3.allindiabankdir.R;
import com.team3.allindiabankdir.database.AllIndiaBanksDB;
import com.team3.allindiabankdir.database.adapters.Adapter;
import com.team3.allindiabankdir.database.adapters.BanksAdapter;
import com.team3.allindiabankdir.utils.AppConstants;
import com.team3.allindiabankdir.utils.WorkflowState;

public class AllIndiaBankDirectoryActivity extends ListActivity implements AppConstants {
	private Context appContext;
	
	private static WorkflowState state;
	
	private SQLiteDatabase db;
	
	private Adapter banksAdapter;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if (AppConstants.DEBUG) {
        	Log.d(TAG_ACTIVITY, "init");
        }
        
        setContentView(R.layout.main);
        
        appContext = getApplicationContext();
        
        banksAdapter = new BanksAdapter(appContext);  
        
        state = WorkflowState.BANK;
                
        final EditText searchBox = (EditText) findViewById(R.id.editText1);
        final TextView labelTextView = (TextView) findViewById(R.id.lblTextView);

        labelTextView.setText(state.getLabelMessage());
        
        searchBox.addTextChangedListener(new TextWatcher() {
			
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				//messageTextView.setText(s.toString());
				refreshList(s);
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				//searchBox.setText(getSearchText());
			}
			
			public void afterTextChanged(Editable s) {
				//setNextStatus();
				//labelTextView.setText(getSearchText());
			}
		});
    }
	
	private void setNextStatus() {
		if (WorkflowState.BANK.compareTo(state) == 0)
			state = WorkflowState.CITY;
		else if (WorkflowState.CITY.compareTo(state) == 0)
			state = WorkflowState.BRANCH;
		else if (WorkflowState.BRANCH.compareTo(state) == 0)
			state = WorkflowState.SHOWDETAILS;
	}

	private void refreshList(CharSequence s) {
		if (s.length() > 0) {
			String[] columns = { state.getFieldName() };
			Cursor cur = banksAdapter.fetchContains(columns, s.toString());
			if (cur != null) {
				String[] arrayOfString = { state.getFieldName() };
				int[] resArray = { R.id.textView1};
				SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(appContext, R.layout.main, cur, arrayOfString, resArray);
				this.setListAdapter(listAdapter);
			}
		}
	}
}