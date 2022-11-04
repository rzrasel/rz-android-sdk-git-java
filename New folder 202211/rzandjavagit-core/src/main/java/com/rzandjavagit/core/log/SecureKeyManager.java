package com.rzandjavagit.core.log;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/*import com.rz.librarycore.apppackage.APPStaticPackageInfo;
import com.rz.librarycore.certificate.CertificateSHA1Fingerprint;
import com.rz.librarycore.hardware.DeviceInfo;
import com.rz.librarycore.inetapi.CheckNetConn;
import com.rz.librarycore.inetapi.DeviceIPApi;
import com.rz.librarycore.storage.SharePrefPrivateHandler;*/

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by Rz Rasel on 2018-02-05.
 */
public class SecureKeyManager {
}
/*public class SecureKeyManager {
    private Activity activity;
    private Context context;
    private SharePrefPrivateHandler onSharePreference;
    private SimpleDateFormat simpleDateFormat;
    private DeviceIPApi deviceIPApi;
    private DeviceInfo deviceInfo;
    private long asyncDelayTime = 0;
    private long delayTimeMain = 1000 * 60 * 1; // 1000 * 60 * 2;
    private long delayTimeTemp = 1000 * 10;
    private Message message = new Message();
    public final static String KeyDeviceHardWareIp = "device_hardware_ip";
    public final static String KeyDeviceGlobalNetIp = "device_global_net_ip";
    public final static String KeyDevicePrimaryId = "device_primary_id";
    public final static String KeyDeviceSecondaryId = "device_secondary_id";
    public final static String KeyDeviceNetLatitude = "device_net_latitude";
    public final static String KeyDeviceNetLongitude = "device_net_longitude";
    public final static String KeyDeviceNetCountry = "device_net_country";
    public final static String KeyPrivateDataDate = "private_data_date";
    public final static String KeyPDataForceUpdate = "is_private_data_force_update";
    public final static String KeySecurityEntryDate = "security_entry_date";
    public final static String KeyAppPackageName = "app_package_name";
    public final static String KeyAppVersionCode = "app_version_code";
    public final static String KeyAppVersionName = "app_version_name";
    public final static String KeyAppAuthKey = "app_auth_key";
    public final static String KeyAppFCMKeyToken = "app_fcm_key_token";
    public final static String KeyAppIsFirstTime = "app_is_first_run";
    public final static String KeyAppFirstDate = "app_first_run_date";
    //public String ValSecureHardIp = "";
    private boolean isForceUpdate = false;
    private boolean isGrabOnlineData = false;

    public SecureKeyManager(Activity argActivity, Context argContext) {
        activity = argActivity;
        context = argContext;
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        onSharePreference = new SharePrefPrivateHandler(context, APPStaticPackageInfo.getPackageName(context));
        Object objInitDate = onSharePreference.getValue("app_initialization_date");
        if (objInitDate == null) {
            onSharePreference.setValue("app_initialization_date", simpleDateFormat.format(new Date()));
        }
        *//*asyncDelayTime = delayTimeMain;
        asyncHandler.sendEmptyMessage(0);*//*
    }

    public void onExecute(boolean argIsGrabOnlineData) {
        //False is not execute or grab online data, if true grab online data
        isGrabOnlineData = argIsGrabOnlineData;
        asyncDelayTime = delayTimeMain;
        asyncHandler.sendEmptyMessage(0);
    }

    Thread asyncThread = new Thread(new Runnable() {
        @Override
        public void run() {
            //message = new Message();
            Bundle bundle = new Bundle();
            Integer value = 1;
            bundle.putInt("KEY", value);
            message.setData(bundle);
            if (isForceUpdate) {
                message.what = 0;
            }
            asyncHandler.sendMessage(message);
        }
    });
    Handler asyncHandler = new Handler() {
        @Override
        public void handleMessage(Message argMessage) {
            Bundle bundle = argMessage.getData();
            message = new Message();
            asyncDelayTime = delayTimeTemp;
            if (CheckNetConn.isConnected(context)) {
                asyncDelayTime = delayTimeMain;
                //LogWriter.Log("Net connection found.");
            } else {
                LogWriter.Log("Net connection not found.");
            }
            Object objIsForceUpdate = onSharePreference.getValue(KeyPDataForceUpdate);
            if (objIsForceUpdate != null) {
                boolean isForceUpdate = (Boolean) objIsForceUpdate;
                asyncDelayTime = delayTimeMain;
                if (isForceUpdate) {
                    asyncDelayTime = delayTimeTemp;
                    message.what = 0;
                }
            } else {
                asyncDelayTime = delayTimeTemp;
                message.what = 0;
            }
            if (argMessage.what == 0) {
                //updateUI();
                if (CheckNetConn.isConnected(context)) {
                    onSecurityKeyInitialization();
                } else {
                    Object objHardwareIp = onSharePreference.getValue(KeyDeviceHardWareIp);
                    if (objHardwareIp == null) {
                        onSecurityKeyInitialization();
                    }
                }
                //onSharePreference.printAllKeyValue();
                //LogWriter.Log("GET 0");
                message.what = 1;
            } else {
                //showErrorDialog();
                //LogWriter.Log("GET OTHER " + argMessage.what);
                message.what = 0;
            }
            //LogWriter.Log("Delay Time: " + asyncDelayTime);
            this.postDelayed(asyncThread, asyncDelayTime);
        }
    };

    private void onSecurityKeyInitialization() {
        Object objHardwareIp = onSharePreference.getValue(KeyDeviceHardWareIp);
        if (objHardwareIp == null) {
            //LogWriter.Log("IP is: " + objHardIp.toString());
            //LogWriter.Log("Hardware IP is null.");
            onSetPrivateData();
            onSetDeviceData();
        } else {
            try {
                Object objSecurityEntryDate = onSharePreference.getValue(KeySecurityEntryDate);
                Object objForceUpdate = onSharePreference.getValue(KeyPDataForceUpdate);
                    *//*Date date = new Date();
                    long HOUR = 60 * 60 * 25;
                    Date nowDate = new Date(date.getTime() + HOUR);
                    DateTime dt = new DateTime();
                    DateTime added = dt.plusHours(6);*//*
                    *//*Calendar calendar = Calendar.getInstance();
                    calendar.setTime(new Date());
                    calendar.add(Calendar.HOUR_OF_DAY, 24);
                    Date nowDate = new Date(calendar.getTimeInMillis());
                    long diffInMillis = Math.abs(nowDate.getTime() - lastSyncDate.getTime());*//*
                //long dayDiff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                Date lastSyncDate = simpleDateFormat.parse(objSecurityEntryDate.toString());
                Date nowDate = new Date();
                long diffInMillis = Math.abs(nowDate.getTime() - lastSyncDate.getTime());
                long hourDiff = TimeUnit.HOURS.convert(diffInMillis, TimeUnit.MILLISECONDS);
                boolean isForceUpdate = (Boolean) objForceUpdate;
                *//*LogWriter.Log("Sync:-" + objSecurityEntryDate.toString()
                        + "-" + hourDiff + "-HOUR-"
                        + simpleDateFormat.format(nowDate) + "-Is Force-" + isForceUpdate);*//*
                if (hourDiff > 12 || isForceUpdate) {
                    onSetPrivateData();
                    onSetDeviceData();
                }
                //http://www.baeldung.com/java-date-difference
            } catch (ParseException e) {
                //e.printStackTrace();
                LogWriter.Log("Error: " + e);
            }
            //onSharePreference.printAllKeyValue();
        }
    }

    public static void onSetFCMKey(Context argContext, String argAppFCMKeyToken) {
        SharePrefPrivateHandler staticPreference = new SharePrefPrivateHandler(argContext, APPStaticPackageInfo.getPackageName(argContext));
        staticPreference.setValue(KeyAppFCMKeyToken, argAppFCMKeyToken);
    }

    public static void onSetAppIsRunFirstTime(Context argContext) {
        Format staticFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SharePrefPrivateHandler staticPreference = new SharePrefPrivateHandler(argContext, APPStaticPackageInfo.getPackageName(argContext));
        staticPreference.setValue(KeyAppIsFirstTime, true);
        staticPreference.setValue(KeyAppFirstDate, staticFormat.format(new Date()));
    }

    public static void onSecurityChanged(Context argContext) {
        SharePrefPrivateHandler staticPreference = new SharePrefPrivateHandler(argContext, APPStaticPackageInfo.getPackageName(argContext));
        staticPreference.setValue(KeyPDataForceUpdate, true);
    }

    private void onSetPrivateData() {
        if (!CheckNetConn.isConnected(context) && !isGrabOnlineData) {
            return;
        }
        deviceIPApi = new DeviceIPApi(context);
        deviceIPApi.getApparentIPAddress(new DeviceIPApi.OnHTTPIPEventListenerHandler() {
            @Override
            public void onPostExecute(HashMap<String, String> argResult) {
                //LogWriter.Log(argResult.toString());
                onPrivateDataEntry(argResult);
                onSharePreference.setValue(KeySecurityEntryDate, simpleDateFormat.format(new Date()));
            }
        });
    }

    private void onPrivateDataEntry(HashMap<String, String> argResult) {
        onSharePreference.setValue(KeyDeviceHardWareIp, deviceIPApi.getInterfaceIPAddress())
                .setValue(KeyDeviceGlobalNetIp, argResult.get("ip"))
                .setValue(KeyDeviceNetLatitude, argResult.get("latitude"))
                .setValue(KeyDeviceNetLongitude, argResult.get("longitude"))
                .setValue(KeyDeviceNetCountry, argResult.get("country"))
                .setValue(KeyPDataForceUpdate, false)
                .setValue(KeyPrivateDataDate, simpleDateFormat.format(new Date()));
        //LogWriter.Log("UPDATE DATE TIME: " + simpleDateFormat.format(new Date()));
    }

    private void onSetDeviceData() {
        CertificateSHA1Fingerprint certAuthKey = CertificateSHA1Fingerprint.getInstance();
        deviceInfo = new DeviceInfo(activity, context);
        String devicePrimaryId = deviceInfo.getDeviceBuildID();
        String deviceSecondaryId = deviceInfo.getDeviceID();
        *//*devicePrimaryId = MD5Builder.md5(devicePrimaryId);
        deviceSecondaryId = MD5Builder.md5(deviceSecondaryId);*//*
        onSharePreference.setValue(KeyDevicePrimaryId, devicePrimaryId)
                .setValue(KeyAppPackageName, APPStaticPackageInfo.getPackageName(context))
                .setValue(KeyAppVersionCode, APPStaticPackageInfo.getVersionCode(context))
                .setValue(KeyAppVersionName, APPStaticPackageInfo.getVersionName(context))
                .setValue(KeyAppAuthKey, certAuthKey.getAuthKey(context))
                //.setValue(KeyAppFCMKeyToken, "")
                //.setValue(KeyAppIsFirstTime, true)
                //.setValue(KeyAppFirstDate, staticFormat.format(new Date()))
                .setValue(KeyDeviceSecondaryId, deviceSecondaryId);
    }
}*/
/*
Force Update True:
1. base on distance
2. change global ip
3. change hardware ip
*/