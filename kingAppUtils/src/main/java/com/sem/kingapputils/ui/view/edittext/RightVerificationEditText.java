package com.sem.kingapputils.ui.view.edittext;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.sem.kingapputils.R;
import com.sem.kingapputils.databinding.VerificationTextLayoutBinding;
import com.sem.kingapputils.utils.CountDownButtonHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;

/**
 * @ProjectName: WidgetDemo
 * @Package: com.example.widgetdemo.edittext
 * @ClassName: VerificationEditText
 * @Description: 手机验证码输入框
 * @Author: king
 * @CreateDate: 2021/5/6 16:01
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/6 16:01
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RightVerificationEditText extends LinearLayoutCompat {
    private CountDownButtonHelper mCountDownHelper;
    public ObservableField<String> content;
    public VerifyCodeClick clickListener;

    public interface VerifyCodeClick{

        /**
         * @param view btn
         * @return 是否可以执行点击action
         */
       Boolean onClick(View view);
    }

    @BindingAdapter("content")
    public static void setContent(RightVerificationEditText vp, ObservableField<String> value){
        vp.content = value;
    }

    @BindingAdapter("verifyClick")
    public static void verifyClick(RightVerificationEditText vp, VerifyCodeClick listener){
        vp.clickListener = listener;
    }

    public RightVerificationEditText(@NonNull Context context) {
        super(context);
        initView();
    }

    public RightVerificationEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public RightVerificationEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        VerificationTextLayoutBinding binding= DataBindingUtil.inflate(inflater, R.layout.verification_text_layout, this, true);
        binding.setHandler(this);

        mCountDownHelper = new CountDownButtonHelper(binding.getCodeButton, 60);

        binding.ed.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus){
                binding.line.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.wx_theme_green));
            }else {
                binding.line.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.edittext_line_color));
            }
        });
    }

    public void getCode(View view) {
        if (clickListener != null) {
            if(clickListener.onClick(view)){
                mCountDownHelper.start();
            }
        }
    }

    public void getCodeStart(){
        mCountDownHelper.start();
    }

}
