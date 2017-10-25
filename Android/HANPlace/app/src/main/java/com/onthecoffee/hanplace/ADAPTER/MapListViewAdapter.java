package com.onthecoffee.hanplace.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.onthecoffee.hanplace.R;

import java.util.ArrayList;

/**
 * Created by Soomti on 2017. 4. 30..
 */

class MapListViewAdapter extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<MapListViewItem> listViewItemList ;

    // ListViewAdapter의 생성자
    public MapListViewAdapter(ArrayList<MapListViewItem> itemList) {
        if (itemList == null) {
            listViewItemList = new ArrayList<MapListViewItem>() ;
        } else {
            listViewItemList = itemList ;
        }
    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_map_area, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView TextView =(TextView) convertView.findViewById(R.id.area_name) ;

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        MapListViewItem listViewItem = listViewItemList.get(position);


        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(int no, String text) {
        MapListViewItem item = new MapListViewItem();
        item.setText(text);

        listViewItemList.add(item);
    }

    public ArrayList<MapListViewItem> getItemList() {
        return listViewItemList ;
    }
}
