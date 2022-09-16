package com.sem.kingapputils.utils;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import java.io.File;

import androidx.core.content.FileProvider;

/**
 * @ProjectName: VQCStation
 * @Package: com.sem.kingapputils.utils
 * @ClassName: OpenFiles
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2021/5/19 16:21
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/19 16:21
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class OpenFiles {
    public static Intent getOpenFileIntent(File file){
        if (file.isFile()){
            return getMediaFileIntent(file);
        }
        return null;
    }

    public static Intent getMediaFileIntent(File file) {
        Intent intent = null;
        String mimeTypeFromFile = FileUtils.getMimeTypeFromFile(file.getPath());
        if (Build.VERSION.SDK_INT >= 29) {
            intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Uri apkUri = FileProvider.getUriForFile(Utils.getApp().getApplicationContext(), "com.king.sem.fileProvider", file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, mimeTypeFromFile);
        }else {
            intent = new Intent("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Uri uri = Uri.fromFile(file);
            intent.setDataAndType(uri, mimeTypeFromFile);
        }
        return intent;
    }
    //android获取一个用于打开HTML文件的intent
    public static Intent getHtmlFileIntent(String path) {
        File file = new File(path);
        Uri uri = Uri.parse(file.toString()).buildUpon().encodedAuthority("com.android.htmlfileprovider").scheme("content").encodedPath(file.toString()).build();
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(uri, FileUtils.getMimeTypeFromFile(path));//"text/html"
        return intent;
    }

    //android获取一个用于打开图片文件的intent
    public static Intent getImageFileIntent(String path) {
        File file = new File(path);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, FileUtils.getMimeTypeFromFile(path));
        return intent;
    }

    //android获取一个用于打开PDF文件的intent
    public static Intent getPdfFileIntent(String path) {
        File file = new File(path);
        Intent intent = null;
        if (Build.VERSION.SDK_INT >= 29) {
            intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Uri apkUri = FileProvider.getUriForFile(Utils.getApp().getApplicationContext(), "com.king.sem.fileProvider", file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/pdf");
        }else {
            intent = new Intent("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Uri uri = Uri.fromFile(file);
            intent.setDataAndType(uri, FileUtils.getMimeTypeFromFile(path));// "application/pdf"
        }
        return intent;
    }

    //android获取一个用于打开文本文件的intent
    public static Intent getTextFileIntent(String path) {
        File file = new File(path);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, FileUtils.getMimeTypeFromFile(path));//"text/plain"
        return intent;
    }

    //android获取一个用于打开音频文件的intent
    public static Intent getAudioFileIntent(String path) {
        File file = new File(path);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, FileUtils.getMimeTypeFromFile(path));//"audio/*"
        return intent;
    }

    //android获取一个用于打开视频文件的intent
    public static Intent getVideoFileIntent(String path) {
        File file = new File(path);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, FileUtils.getMimeTypeFromFile(path));//"video/*"
        return intent;
    }


    //android获取一个用于打开CHM文件的intent
    public static Intent getChmFileIntent(String path) {
        File file = new File(path);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, FileUtils.getMimeTypeFromFile(path));//"application/x-chm"
        return intent;
    }


    //android获取一个用于打开Word文件的intent
    public static Intent getWordFileIntent(String path) {
        File file = new File(path);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, FileUtils.getMimeTypeFromFile(path));//"application/msword"
        return intent;
    }

    //android获取一个用于打开Excel文件的intent
    public static Intent getExcelFileIntent(String path) {
        File file = new File(path);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, FileUtils.getMimeTypeFromFile(path));
        return intent;
    }

    //android获取一个用于打开PPT文件的intent
    public static Intent getPPTFileIntent(String path) {
        File file = new File(path);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, FileUtils.getMimeTypeFromFile(path));//"application/vnd.ms-powerpoint"
        return intent;
    }

    //android获取一个用于打开apk文件的intent
    public static Intent getApkFileIntent(String path) {
        File file = new File(path);
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), FileUtils.getMimeTypeFromFile(path));//"application/vnd.android.package-archive"
        return intent;
    }
}