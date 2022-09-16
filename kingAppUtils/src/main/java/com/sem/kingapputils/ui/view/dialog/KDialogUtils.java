package com.sem.kingapputils.ui.view.dialog;

import android.content.Context;
import android.view.View;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.sem.kingapputils.R;

/**
 * @ProjectName: sem
 * @Package: com.sem.kingapputils.ui.view.dialog
 * @ClassName: KDialogUtils
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2022/1/4 11:10
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/1/4 11:10
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class KDialogUtils {

    public static void showSingleDialog(Context context, String title, String message, String actionName, View.OnClickListener clickListener){
        if (context == null) {
            return;
        }
        new QMUIDialog.MessageDialogBuilder(context)
                .setTitle(title)
                .setMessage(message)
                .addAction(actionName, new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                        if (clickListener != null) {
                            clickListener.onClick(dialog.getCurrentFocus());
                        }
                    }
                })
                .create(R.style.KDiloadStyle).show();
    }

    public static QMUIDialog.MessageDialogBuilder showDialog(Context context, String title, CharSequence message){
        if (context == null) {
            return null;
        }
        return new QMUIDialog.MessageDialogBuilder(context)
                .setTitle(title)
                .setMessage(message);
    }
}
