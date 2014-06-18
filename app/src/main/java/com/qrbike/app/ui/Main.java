package com.qrbike.app.ui;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.qrbike.app.R;
import com.qrbike.app.common.UIHelper;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1=(Button)findViewById(R.id.btn_Capture);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIHelper.showCapture(Main.this);
            }
        });

        Button btn2=(Button)findViewById(R.id.btn_Backup);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIHelper.showBackup(Main.this);
            }
        });

        Button btn3=(Button)findViewById(R.id.btn_BackupList);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIHelper.showBackupList(Main.this);
            }
        });

        Button btn4=(Button)findViewById(R.id.btn_Operate);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIHelper.showOperate(Main.this);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
