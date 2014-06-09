package com.qrbike.app.ui;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.preference.PreferenceManager;

import com.qrbike.app.R;
import com.qrbike.app.AppContext;
import com.qrbike.app.common.Save2File;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Kingsun on 14-6-7.
 */
public class Backup extends Activity{
    private AppContext ac;
    private EditText etsfbh;
    private EditText etjlr;
    private EditText etjldz;
    private EditText etwgnr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.backup);

        etsfbh = (EditText) findViewById(R.id.ET_sfbh);
        etjlr = (EditText) findViewById(R.id.ET_jlr);
        etjldz = (EditText) findViewById(R.id.ET_jldz);
        etwgnr = (EditText) findViewById(R.id.ET_wgnr);

        SharedPreferences share_read=getSharedPreferences("share_data",0);
        String sd_tmp = share_read.getString("jlr",null);
        if (!sd_tmp.isEmpty()) etjlr.setText(sd_tmp);
        sd_tmp = share_read.getString("jldz",null);
        if(!sd_tmp.isEmpty()) etjldz.setText(sd_tmp);
        sd_tmp = share_read.getString("wgnr",null);
        if(!sd_tmp.isEmpty()) etwgnr.setText(sd_tmp);/*
        int sd_int = share_read.getInt("sfbh", 0);
        if(sd_int != 0 ) etsfbh.setText(sd_int);*/
        Intent intent = getIntent();
        etsfbh.setText(intent.getStringExtra("sfbh"));
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Environment.getExternalStorageState().获取sd卡的状态，判断是安装，还是移除
        //findViewById(R.id.sdcBT).setEnabled(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onClick(View v){
        try{
            String sfbh = etsfbh.getText().toString();
            String jlr = etjlr.getText().toString();
            String jldz = etjldz.getText().toString();
            String wgnr = etwgnr.getText().toString();
            SharedPreferences.Editor share_write=getSharedPreferences("share_data",0).edit();
            share_write.putString("jlr",jlr);
            share_write.putString("jldz",jldz);
            share_write.putString("wgnr",wgnr);
            share_write.commit();

            Save2File service = new Save2File();
            boolean SDCardExit = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
            if (!SDCardExit)  {
                Toast.makeText(getApplicationContext(),"没有SD卡",Toast.LENGTH_SHORT).show();
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
            service.save2SDCard(sfbh + "," +jlr+","+sdf.format(new Date())+","+jldz+","+wgnr);

            Toast.makeText(getApplicationContext(), "保存成功", Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(getApplication(), "sdCard出异常", Toast.LENGTH_SHORT).show();
        }
    }

}
