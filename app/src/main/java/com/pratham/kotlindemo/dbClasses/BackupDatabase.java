package com.pratham.kotlindemo.dbClasses;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class BackupDatabase {

    public static void backup(Context mContext) {
        try {
            deletePreviousDbs();
            File sd = new File(Environment.getExternalStorageDirectory() + "/" + "DemoBackup");
            if (!sd.exists()) sd.mkdir();
            if (sd.canWrite()) {
                File currentDB = mContext.getDatabasePath("demoDB");
                File parentPath = currentDB.getParentFile();
                for (File f : parentPath.listFiles()) {
                    File temp = new File(sd, f.getName());
                    if (!temp.exists()) temp.createNewFile();
                    FileChannel src = new FileInputStream(f).getChannel();
                    FileChannel dst = new FileOutputStream(temp).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                }
            } else {
/*                EventMessage message = new EventMessage();
                message.setMessage(PD_Constant.WRITE_PERMISSION);
                EventBus.getDefault().post(message);*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void deletePreviousDbs() {
        File sd = Environment.getExternalStorageDirectory();
        for (File f : sd.listFiles()) {
            if (f.getName().contains("demoDB"))
                f.delete();
        }
    }
}
