package com.qrbike.app.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.qrbike.app.R;

/**
 * Created by Kingsun on 14-5-17.
 */
public class QrMenu extends ListFragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MenuAdapter adapter = new MenuAdapter(getActivity());
        adapter.add(new MenuItem("扫一扫"));
        adapter.add(new MenuItem("待录入"));
        adapter.add(new MenuItem("已处理"));
        setListAdapter(adapter);
    }

    public class MenuAdapter extends ArrayAdapter<MenuItem> {
        public MenuAdapter(Context context) {
            super(context, 0);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_item_row, null);
            }
            TextView title = (TextView) convertView
                    .findViewById(R.id.row_title);
            title.setText(getItem(position).tag);
            return convertView;
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Fragment newContent = null;
        switch (position) {
            case 1:
                newContent = new Fragment_1();
                break;
            case 2:
                newContent = new Fragment_2();
                break;
            case 3:
                newContent = new Fragment_3();
                break;
        }
        if (newContent != null) {
            switchFragment(newContent);
        }
        super.onListItemClick(l, v, position, id);
    }

    private void switchFragment(Fragment fragment) {
        if (getActivity() == null) {
            return;
        }
        if (getActivity() instanceof QrHome) {
            QrHome fca = (QrHome) getActivity();
            fca.switchContent(fragment);
        }
    }

    private class MenuItem {
        public String tag;
        public MenuItem(String tag) {
            this.tag = tag;
        }
    }
}
