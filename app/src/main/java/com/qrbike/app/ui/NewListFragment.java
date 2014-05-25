package com.qrbike.app.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.qrbike.app.R;

/**
 * Created by Kingsun on 14-5-10.
 */
public class NewListFragment extends ListFragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_list, null);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        NewAdapter adapter = new NewAdapter(getActivity());
        adapter.add(new NewItem("扫一扫"));
        adapter.add(new NewItem("待录入"));
        adapter.add(new NewItem("已录入"));
        setListAdapter(adapter);
    }

    /*新的条项样本*/
    private class NewItem {
        public String tag;
        public NewItem(String tag) {
            this.tag = tag;
        }
    }

    public class NewAdapter extends ArrayAdapter<NewItem> {

        public NewAdapter(Context context) {
            super(context, 0);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.new_row, null); /*设置每行的样式（例如：图+字）*/
            }
            TextView title = (TextView) convertView.findViewById(R.id.row_title);
            title.setText(getItem(position).tag);

            return convertView;
        }

    }
}
