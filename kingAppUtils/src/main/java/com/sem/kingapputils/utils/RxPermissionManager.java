package com.sem.kingapputils.utils;

import android.Manifest;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.tbruyelle.rxpermissions3.Permission;
import com.tbruyelle.rxpermissions3.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

/**
 * @ProjectName: sem
 * @Package: com.sem.kingapputils.utils
 * @ClassName: RxPermissionManager
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2021/10/14 9:35
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/10/14 9:35
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RxPermissionManager {

    public static void requestPermissions(FragmentActivity activity, RxPermissionListener listener, String... permissions){

        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions
                .requestEachCombined(permissions)
                .subscribe(permissionRes -> {
                    if (permissionRes.granted){
                        if (listener != null)
                        listener.accept();
                    }else if (permissionRes.shouldShowRequestPermissionRationale){
                        if (listener != null)
                        listener.refuse();
                    }else{
                        if (listener != null)
                        listener.noAsk();
                    }
                });
    }

    /**
     * 检查权限
     * @param activity ac
     * @param permissions 权限字段
     * @return
     */
    public static Boolean checkPermissions(FragmentActivity activity, String... permissions){
        Boolean result = true;
        RxPermissions rxPermission = new RxPermissions(activity);
        for (int i = 0; i < permissions.length; i++) {
            result = (result && rxPermission.isGranted(permissions[i]));
        }
        return result;
    }

    /**
     * 检查权限是否NO-ASK
     * @param activity ac
     * @param permission 权限字段
     * @return
     */
    public static Boolean getShouldShowRequestPermissionRationale(AppCompatActivity activity, String permission){
        //shouldShowRequestPermissionRationale 如果用户以前从未被请求过许可，也会返回 false
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
    }

    /**
     * 处理多组权限问题
     * @param activity
     * @param length
     * @param listener
     */
//    private static void handlePermission(FragmentActivity activity, int length, RxPermissionListener listener){
//        //当权限个数等于申请次数时，证明所有权限都已申请完毕
//        if (length == requestCount){
//            if (refuseCount == 0 && noAskPermission.size() == 0){
//                //权限全部申请通过
//                listener.accept();
//            }else if (noAskPermission.size() == 0){
//                //拒绝权限
//                listener.refuse();
//            }else{
//                //拒绝权限，并勾选了不再提醒，弹窗提示去设置页
//                listener.noAsk();
//                String permissionName = getPermissionName();
//                new QMUIDialog.MessageDialogBuilder(Utils.getTopActivityOrApp())
//                        .setTitle("告警事件")
//                        .setMessage(data)
//                        .addAction("取消", new QMUIDialogAction.ActionListener() {
//                            @Override
//                            public void onClick(QMUIDialog dialog, int index) {
//                                dialog.dismiss();
//                            }
//                        })
//                        .addAction(0, "查看", QMUIDialogAction.ACTION_PROP_NEGATIVE, (dialog, index) -> {
//                            dialog.dismiss();
//                            Intent intentClick = null;
//                            if (checkAppRun()) {
//                                if (Utils.getTopActivityOrApp().getClass() != WarnHomeActivity.class) {
//                                    intentClick = new Intent(Utils.getTopActivityOrApp(), WarnHomeActivity.class);
//                                    intentClick.putExtra(Constantss.PUSH_CONTENT, data);
//                                }else {
//                                    return;
//                                }
//                            } else {
//                                intentClick = getPackageManager().getLaunchIntentForPackage("com.tsr.ele_manager");
//                            }
//
//                            startActivity(intentClick);
//                        })
//                        .create(R.style.QMUI_Dialog).show();
//                CustomDialog.Builder builder = new CustomDialog.Builder(activity).setMessage("请前往设置->应用->朴新助教->权限中打开" + permissionName + "权限，否则功能无法正常运行！")
//                        .setLeftBtnTitle("取消")
//                        .setLeftButtonListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                //取消
//                                customDialog.dismiss();
//                            }
//                        })
//                        .setRightBtnTitle("确定")
//                        .setRightBtnBlue()
//                        .setRightButtonListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                                Uri uri = Uri.fromParts("package", AppUtil.getLocalPackageInfo(activity).packageName, null);
//                                intent.setData(uri);
//                                activity.startActivity(intent);
//                                customDialog.dismiss();
//                            }
//                        });
//                customDialog = builder.createTwoButtonAndNoTitleDialog();
//                customDialog.show();
//            }
//        }
//    }


}
