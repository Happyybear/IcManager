package com.sem.kingapputils.ui.view.bindingemptyview;

import android.content.Context;
import android.util.AttributeSet;

import com.qmuiteam.qmui.widget.QMUIEmptyView;
import com.sem.kingapputils.R;
import com.sem.kingapputils.utils.ResUtils;

/**
 * @ProjectName: sem
 * @Package: com.beseClass.view
 * @ClassName: KEmptyView
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2021/12/6 17:31
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/12/6 17:31
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class KEmptyView extends QMUIEmptyView {
    public int state;
    public KEmptyView(Context context) {
        super(context);
        initView();
    }

    public KEmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public KEmptyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        setTitleColor(ResUtils.getColor(R.color.gray));
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
