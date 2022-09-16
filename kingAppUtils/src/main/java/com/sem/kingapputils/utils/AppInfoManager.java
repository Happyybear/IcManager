package com.sem.kingapputils.utils;

/**
 * @ProjectName: AndroidStudio-EleManager
 * @Package: com.sem.Uitils
 * @ClassName: AppInfoUitils
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2019/11/4 0004 上午 9:51
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/11/4 0004 上午 9:51
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class AppInfoManager {

    private float fontSizeScale = 1f;
    private static final float MIN_VALUE  = 0.85f;

    /**
     * 设置字体大小，main启动时直接显示即可
     */
    public boolean isSetFontSize = false;

    public static AppInfoManager getsInstance() {
        return AppInfoManagerSingletonInstance.instance;
    }

    private AppInfoManager() {
    }

    private static class AppInfoManagerSingletonInstance{
        private static final AppInfoManager instance = new AppInfoManager();
    }

    public float getFontSizeScale() {
        return fontSizeScale;
    }

    public void setFontSizeScale(float fontSizeScale) {
        if (fontSizeScale >= MIN_VALUE) {
            this.fontSizeScale = fontSizeScale;
        }else {
            // 默认
            this.fontSizeScale = 1;
        }
    }


}
