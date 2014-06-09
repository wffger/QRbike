package com.qrbike.app.common;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Kingsun on 14-6-7.
 */
public class Save2File {
    public void save2SDCard(String content) {
        FileOutputStream fos = null;
        String hh = "/r";
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
//            Date curDate = new Date(System.currentTimeMillis());
            String fileName=formatter.format(new java.util.Date());
            fileName = "qb_" + fileName + ".txt";
            File sd = Environment.getExternalStorageDirectory();
            String path=sd.getPath()+"/QrBike";
            File test = new File(path);
            if (!test.exists()) test.mkdir();
            File file = new File(path,fileName);
            fos = new FileOutputStream(file,true);
            if(file.length()==0)
            {
                fos.write(content.getBytes());
            }else
            {
                content="\r"+content;
                fos.write(content.getBytes());
            }
        }catch (Exception e){e.printStackTrace();}
        finally {
            try{fos.close();}catch (IOException e){e.printStackTrace();}
        }


    }












}
