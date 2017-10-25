package com.onthecoffee.hanplace.FRAGMENT;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.onthecoffee.hanplace.R;

/**
 * Created by Soomti on 2017. 4. 30..
 */

public class map_fragment_area extends ListFragment {

    @Override
    public void onListItemClick (ListView l, View v, int position, long id) {

        // get TextView's Text.

        String strText = (String) l.getItemAtPosition(position) ;
        Toast.makeText(getActivity(),strText,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1));
    }
}
