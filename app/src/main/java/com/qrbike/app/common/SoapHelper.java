package com.qrbike.app.common;

/**
 * Created by Kingsun on 14-4-22.
 */

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class SoapHelper {
    // 命名空间
    String nameSpace = "http://tempuri.org/";
    // 调用的方法名称
    String methodName;
    // EndPoint
    String endPoint = "http://10.0.2.2:8085/StudentService/Default.asmx";
    // SOAP Action
    String soapAction;//= "http://tempuri.org/" + methodName;
    // 指定WebService的命名空间和调用的方法名
    SoapObject rpc = new SoapObject(nameSpace, methodName);
    // 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

    /*设置命名空间*/
    public  void setNameSpace(String n){nameSpace=n;}
    /*设置方法名称*/
    public void setMethodName(String m){methodName=m;}

    /*设置EndPoint*/
    public void setEndPoint(String url){endPoint=url;}

    /*设置SOAP Action*/
    public void setSoapAction(String m)
    {
        soapAction="http://tempuri.org/" + m;
    }

    /*设置是否调用的是dotNet开发的WebService*/
    public void setDotNet(boolean b){envelope.dotNet=b;}

    /*设置Rpc*/
    public void setRpc()
    {
        rpc= new SoapObject(nameSpace,methodName);
    }
    /*设置Property*/
    public void setProperty(String x,String y)
    {
        rpc.addProperty(x,y);
    }
    /*设置OutputSoapObject*/
    public void setSObject()
    {
        envelope.setOutputSoapObject(rpc);
    }

    /*调用WebService*/
    public void callWebService()
    {
        HttpTransportSE transport= new HttpTransportSE(endPoint);
        try
        {
            //调用
            transport.call(soapAction,envelope);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /*获取返回的数据*/
    public SoapObject getObject()
    {
        SoapObject object = (SoapObject)envelope.bodyIn;
        return object;
    }

    /*获取返回的结果*/
    public String getResultByIndex(int i)
    {
        SoapObject object=(SoapObject)envelope.bodyIn;
        String result=object.getPropertyAsString(i);
//        String result=object.getProperty(i).toString();
//        String result=object.getPropertyAsString(0);
        return result;
    }

    /*获取返回的结果*/
    public String getResultByName(String s)
    {
        String result="";
        try
        {
            SoapObject object=(SoapObject)envelope.getResponse();   /*获得返回结果*/
            result=object.getPropertyAsString(s);
//            result=object.getProperty(s).toString();
        }
        catch (Exception e){e.printStackTrace();}
//        String result=object.getProperty(s).toString();
//        String result=object.getProperty('"'+s+'"').toString();
//        String result=object.getPrimitivePropertyAsString(s);
        return result;
    }

    public int getPropertyCount()
    {
        SoapObject object=(SoapObject)envelope.bodyIn;
        int result=object.getPropertyCount();
        return result;
    }

}
