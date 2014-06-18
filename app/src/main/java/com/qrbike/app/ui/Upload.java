package com.qrbike.app.ui;
import com.qrbike.app.common.SoapHelper;
import java.util.ArrayList;

/**
 * Created by Kingsun on 14-6-14.
 */
public class Upload{
    private String method="insertVio";
    private String sfbh;
    private String jlsj;
    private String jlr;
    private String jldz;
    private String wgxw;

    public String upload2DB(ArrayList content) {

        try {
            int size = content.size();
            int done = 0;
            String result="";
            String[] record = (String[]) content.toArray(new String[size]);
            for(int i=0; i<content.size();i++)
            {
                String[] item = record[i].split(",");
                sfbh=item[0];
                jlr=item[1];
                jlsj=item[2];
                jldz=item[3];
                wgxw=item[4];
                SoapHelper soapHelper=new SoapHelper();
                soapHelper.setMethodName(method);
                soapHelper.setSoapAction(method);
                soapHelper.setDotNet(true);
                soapHelper.setRpc();
                soapHelper.setProperty("sfbh", sfbh);
                soapHelper.setProperty("jlsj", jlsj);
                soapHelper.setProperty("jlr", jlr);
                soapHelper.setProperty("jldz", jldz);
                soapHelper.setProperty("wgxw", wgxw);
                soapHelper.setSObject();
                soapHelper.callWebService();
                result = soapHelper.getResultByIndex(0);
                if(result.equals("成功录入."))
                {
                    done++;
                }
            }
            return done+"条记录已录入";
        }catch (Exception e){}
        return "没有记录录入";
    }
}
