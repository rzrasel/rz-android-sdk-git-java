# rzandjavagit-propermission
Rz Android Java Git SDK

### GIT Command
```code_work
/**
 * Permission setup step 1:
 */
private ProPermission proPermission;
private final int PERMISSION_REQUEST_CODE = 1001;
protected int SPLASH_TIME = 2500;
private static final String[] ALL_PERMISSIONS = {
        Manifest.permission.ACCESS_NETWORK_STATE,
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
};


/**
 * Permission setup step 2:
 */
private void onPermissionSetup() {
    proPermission = new ProPermission.Builder()
            .with(activity)
            .isDebug(true)
            .listener(new OnPermissionListener() {
                @Override
                public void onAllPermissionsGranted(List<String> argPermissions) {
                    isPermissionGranted = true;
                    onSendMessage("PERMISSION", "Granted");
                }

                @Override
                public void onPermissionsGranted(List<String> argPermissions) {
                    /*isPermissionGranted = false;
                    smPermission.request(PERMISSION_REQUEST_CODE, ALL_PERMISSIONS);*/
                    isPermissionGranted = true;
                    onSendMessage("PERMISSION", "Granted");
                }

                @Override
                public void onPermissionsDenied(List<String> argPermissions) {
                    /*isPermissionGranted = false;
                    smPermission.request(PERMISSION_REQUEST_CODE, ALL_PERMISSIONS);*/
                    isPermissionGranted = true;
                    onSendMessage("PERMISSION", "Granted");
                }
            }).build();
    if (!proPermission.hasPermission(ALL_PERMISSIONS)) {
        isPermissionGranted = false;
        proPermission.request(PERMISSION_REQUEST_CODE, ALL_PERMISSIONS);
    } else {
        isPermissionGranted = true;
        onSendMessage("PERMISSION", "Granted");
        //Toast.makeText(context, "Permission already granted", Toast.LENGTH_SHORT).show();
    }
}

/**
 * Permission setup step 3:
 */
@Override
public void onRequestPermissionsResult(int argRequestCode, @NonNull String[] argPermissions, @NonNull int[] argGrantResults) {
    super.onRequestPermissionsResult(argRequestCode, argPermissions, argGrantResults);
    proPermission.onRequestPermissionsResult(argPermissions, argGrantResults);
}
```