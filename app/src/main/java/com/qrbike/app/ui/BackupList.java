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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.qrbike.app.R;
import com.qrbike.app.common.FileHelper;

/**
 * Created by Kingsun on 14-6-12.
 */
public class BackupList extends Activity{
    private TextView tvPath;
    private ListView lvFiles;
    private Button btnParent;

    File currentParent;
    File[] currentFiles;

    public void onCreate(Bundle savedInstanceState){ super.onCreate(savedInstanceState);
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
                    final String currentPath = currentFiles[position].getPath();
                    new AlertDialog.Builder(BackupList.this).setTitle("选择操作").
                    setMessage("你可选择查看已记录文件内容，或者上传").
                    setPositiveButton("查看",  new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which){
                            //打开文件
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            File f = new File(path + currentFiles[position].getName());
                            File f = new File(currentPath);
                            intent.setDataAndType(Uri.fromFile(f), "text/plain");
                            startActivity(intent);
                            dialog.dismiss();
                        }
                    }).setNegativeButton("上传",  new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which){
                            Thread thread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Looper.prepare();
                                        String result;
                                        FileHelper fh = new FileHelper();
                                        Upload upload = new Upload();
                                        result = upload.upload2DB(fh.ReadTxt(currentPath));
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                                        Looper.loop();
                                    }catch (Exception e){e.printStackTrace();}
                                }
                            });
                            thread.start();
//                            Toast.makeText(getApplicationContext(), content, Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }).show();
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
