package com.qrbike.app.common;

import android.content.Context;
import android.content.Intent;

import com.qrbike.app.ui.Backup;
import com.qrbike.app.ui.BackupList;
import com.qrbike.app.ui.Capture;
import com.qrbike.app.ui.Operate;

/**
 * Created by Kingsun on 14-5-21.
 */
public class UIHelper {
    private final static String TAG = "UIHelper";

    /**
     * 显示扫一扫界面
     * @param context
     */
    public static void showCapture(Context context) {
        Intent intent = new Intent(context, Capture.class);
        context.startActivity(intent);
    }

    public static void showBackup(Context context) {
        Intent intent = new Intent(context, Backup.class);
        context.startActivity(intent);
    }

    public static void showBackupList(Context context) {
        Intent intent = new Intent(context, BackupList.class);
        context.startActivity(intent);
    }

    public static void showOperate(Context context) {
        Intent intent = new Intent(context, Operate.class);
        context.startActivity(intent);
    }
}
