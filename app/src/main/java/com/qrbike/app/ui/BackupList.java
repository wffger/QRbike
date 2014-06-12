package com.qrbike.app.ui;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.qrbike.app.R;

/**
 * Created by Kingsun on 14-6-12.
 */
public class BackupList extends Activity{
    private TextView tvPath;
    private ListView lvFiles;
    private Button btnParent;

    File currentParent;
    File[] currentFiles;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.backuplist);

        lvFiles=(ListView) this.findViewById(R.id.files);


        File sd = Environment.getExternalStorageDirectory();
        final String path=sd.getPath()+"/QrBike/";
        File dir = new File(path);
        if(dir.exists()) {
            currentFiles = dir.listFiles();
            inflateListView(currentFiles);
        }

        lvFiles.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int position, long id) {
                if (currentFiles[position].isFile()) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    File f = new File(path+currentFiles[position].getName());
                    intent.setDataAndType(Uri.fromFile(f),"text/plain");
                    startActivity(intent);
//                    Toast.makeText(BackupList.this, "这是文件", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getApplicationContext(), "这是文件", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void inflateListView(File[] files) {
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        for(int i=0;i<files.length;i++) {
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("filename",files[i].getName());
            listItems.add(listItem);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                BackupList.this, listItems, R.layout.backupitem,
                new String[] {"filename"}, new int[] {R.id.filename}
        );

        lvFiles.setAdapter(adapter);
    }
}
