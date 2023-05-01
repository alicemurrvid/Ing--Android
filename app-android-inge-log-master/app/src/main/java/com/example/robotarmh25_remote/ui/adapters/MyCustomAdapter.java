package com.example.robotarmh25_remote.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.robotarmh25_remote.R;
import com.example.robotarmh25_remote.RepositoryScenario.Scenario;
import com.example.robotarmh25_remote.ui.remote.autonomousFragment;

import java.util.ArrayList;
import java.util.Arrays;

public class MyCustomAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private Context context;

    public MyCustomAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_layout, null);
        }

        //Handle TextView and display string from your list
        TextView tvContact= (TextView)view.findViewById(R.id.tvContact);
        tvContact.setText(list.get(position).substring(6));

        //Handle buttons and add onClickListeners
        Switch switchbtn = (Switch) view.findViewById(R.id.switchScenarios);
        switchbtn.setText("Scénario "+list.get(position).charAt(0));
        switchbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(autonomousFragment.scenarioToPlay  != null) {
                    if (!switchbtn.isChecked()){
                        autonomousFragment.scenarioToPlay  = null;
                        autonomousFragment.key = 0;
                    }
                    else {
                        Toast.makeText(context, "Vous ne pouvez lancer qu'un scénario à la fois", Toast.LENGTH_SHORT).show();
                        switchbtn.setChecked(false);
                    }
                }
                else {
                    if (Character.compare(list.get(position).charAt(4), ',') != 0) {
                        autonomousFragment.key = Integer.parseInt(list.get(position).substring(3, 5));
                        autonomousFragment.scenarioToPlay  = new Scenario(new ArrayList<String>(Arrays.asList(list.get(position).substring(7).split(", "))), 1);
                    } else {
                        autonomousFragment.key = Character.getNumericValue(list.get(position).charAt(3));
                        autonomousFragment.scenarioToPlay  = new Scenario(new ArrayList<String>(Arrays.asList(list.get(position).substring(6).split(", "))), 1);
                    }
                }
            }
        });

        return view;
    }

}