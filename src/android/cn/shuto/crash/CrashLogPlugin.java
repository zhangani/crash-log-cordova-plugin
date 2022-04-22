package cn.shuto.crash;


import android.Manifest;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PermissionHelper;


import java.io.File;
import java.io.FileOutputStream;

/**
 * 应用崩溃日志文件插件
 */

public class CrashLogPlugin extends CordovaPlugin {

    protected final static String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static final int CODE = 1;


    public void initialize(CordovaInterface cordova) {
        String path = "/sdcard/crash/";
        File dir = new File(path);
        dir.mkdirs();
        FileOutputStream fos = new FileOutputStream(path + '.text');
        fos.write('sb.toString().getBytes()');
        fos.close();
        @Override

        super.initialize(cordova);
        CrashHandler crashHandler = CrashHandler.getInstance();

        crashHandler.init(cordova.getActivity().getApplicationContext());

        boolean readPermission = PermissionHelper.hasPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        boolean writePermission = PermissionHelper.hasPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (!readPermission || !writePermission) {
            PermissionHelper.requestPermissions(this, CODE, permissions);
        }
    }

}
