package com.qrbike.app.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.qrbike.app.R;
import com.qrbike.app.common.SoapHelper;

/**
 * Created by Kingsun on 14-4-20.
 */
public class Operate extends Activity  {
    private EditText studentId;
    private TextView resultView;
    private Button queryButton;
    private EditText method;
    private EditText property;
    private Button qsibut;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //解决空指针,无效
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads() .detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.operate);

        studentId = (EditText) findViewById(R.id.stuId);
        resultView = (TextView) findViewById(R.id.result_text);
        queryButton = (Button) findViewById(R.id.query_btn);

        method=(EditText)findViewById(R.id.method);
        property=(EditText)findViewById(R.id.property);
        qsibut=(Button)findViewById(R.id.qsi_btn);
        qsibut.setOnClickListener(new OnClickListener() {
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
                //
                SoapHelper soapHelper=new SoapHelper();
                soapHelper.setMethodName(methodName);
                soapHelper.setSoapAction(methodName);
                soapHelper.setDotNet(true);
                soapHelper.setRpc();
                soapHelper.setProperty("stuId",id);
                soapHelper.setSObject();
                soapHelper.callWebService();
                String result1 = soapHelper.getResultByIndex(0);
                String result2 = soapHelper.getResultByName(propertyName);
                int result3 = soapHelper.getPropertyCount();
                String result4=soapHelper.getObject().toString();
                // 查询学生信息
                resultView.setText(result1 + "\n" + result2);
//                resultView.setText(result1 + "\n" + result2 + "\n" + result3);
            }
        });

        queryButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //ID
                String id = studentId.getText().toString().trim();
                // 简单判断ID是否正确
                if ("".equals(id) || id.length() < 11) {
                    // 给出错误提示
                    studentId.setError("Wrong ID");
                    studentId.requestFocus();
                    // 将显示查询结果的TextView清空
                    resultView.setText("");
                    return;
                }
                // 查询学生信息
                getRemoteInfo(id);
            }
        });
    }

    /**
     * 学生信息查询
     *
     * @param id 学号
     */
    public void getRemoteInfo(String id) {
        // 命名空间
        String nameSpace = "http://tempuri.org/";
        // 调用的方法名称
        String methodName = "getStudentInfo";
        // EndPoint
        String endPoint = "http://10.0.2.2:8085/StudentService/Default.asmx";
        // SOAP Action
        String soapAction = "http://tempuri.org/getStudentInfo";
        // 指定WebService的命名空间和调用的方法名
        SoapObject rpc = new SoapObject(nameSpace, methodName);
        // 设置需调用WebService接口需要传入的参数StudentId
        rpc.addProperty("StudentId", id);
        rpc.addProperty("userId", "");

        // 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

//        envelope.bodyOut = rpc;
        // 设置是否调用的是dotNet开发的WebService
        envelope.dotNet = true;

        // 等价于envelope.bodyOut = rpc;
        envelope.setOutputSoapObject(rpc);

        HttpTransportSE transport = new HttpTransportSE(endPoint);
        try {
            // 调用WebService
            transport.call(soapAction, envelope);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 获取返回的数据
        SoapObject object = (SoapObject) envelope.bodyIn;
        // 获取返回的结果
//        String result = object.toString();
        String result = object.getProperty(0).toString();
        // 将WebService返回的结果显示在TextView中
        resultView.setText(result);
    }

}