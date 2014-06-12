package com.qrbike.app.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.qrbike.app.R;
import com.qrbike.app.common.SoapHelper;

/**
 * Created by Kingsun on 14-4-20.
 */
public class Operate extends Activity  {
    private EditText studentId;
    private TextView resultView;
    private EditText method;
    private EditText property;
    private Button execute_button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //解决空指针,无效
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads() .detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.operate);

        studentId = (EditText) findViewById(R.id.recordPK);
        resultView = (TextView) findViewById(R.id.txt_result);

        method=(EditText)findViewById(R.id.method);
        property=(EditText)findViewById(R.id.property);
        execute_button=(Button)findViewById(R.id.btn_execute);
        execute_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //ID
                String id = studentId.getText().toString().trim();
                //methodName
                String  methodName=method.getText().toString().trim();
                String propertyName=property.getText().toString().trim();
                // methodName
                if ("".equals(methodName)) {
                    // 给出错误提示
                    method.setError("Wrong methodName");
                    method.requestFocus();
                    // 将显示查询结果的TextView清空
                    resultView.setText("");
                }
                SoapHelper soapHelper=new SoapHelper();
                soapHelper.setMethodName(methodName);
                soapHelper.setSoapAction(methodName);
                soapHelper.setDotNet(true);
                soapHelper.setRpc();
                soapHelper.setProperty("id",id);
                soapHelper.setSObject();
                soapHelper.callWebService();
                String result1 = soapHelper.getResultByIndex(0);
                String result2 = soapHelper.getResultByName(propertyName);
                String result4=soapHelper.getObject().toString();
                // 查询学生信息
                resultView.setText(result1 + "\n" + result2+ "\n" + result4);
            }
        });
    }
}