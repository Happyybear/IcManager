package com.sem.kingapputils.ui.view.edittext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.sem.kingapputils.R;
import com.sem.kingapputils.databinding.CodeEditTextLayoutBinding;
import com.sem.kingapputils.utils.CodeUtils;

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
 * @ClassName: CodeEditTextLayout
 * @Description: 带验证码的输入框
 * @Author: king
 * @CreateDate: 2021/5/7 15:12
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/7 15:12
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CodeEditTextLayout extends LinearLayoutCompat {
    private float leftTextSize;
    private int lineColor;
    private String hitText;
    private String leftText;
    private CodeUtils codeUtils;
    private int inputType;

    public ObservableField<String> content;

    @BindingAdapter("content")
    public static void setContent(CodeEditTextLayout vp, ObservableField<String> value){
        vp.content = value;
    }

    public CodeEditTextLayout(@NonNull Context context) {
        super(context);
    }

    public CodeEditTextLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, R.attr.titleTextStyle);
    }

    public CodeEditTextLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EditTextLayout, defStyleAttr, 0);
        leftText = typedArray.getString(R.styleable.EditTextLayout_leftText);
        hitText = typedArray.getString(R.styleable.EditTextLayout_hintText);
        lineColor = typedArray.getColor(R.styleable.EditTextLayout_lineColor, Color.parseColor("#1E90FF"));
        leftTextSize = typedArray.getFloat(R.styleable.EditTextLayout_leftTextSize,16);
        inputType = typedArray.getInt(R.styleable.EditTextLayout_customInputType,0);
        typedArray.recycle();
        initView();
    }

    private void initView(){
        codeUtils = new CodeUtils();
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        CodeEditTextLayoutBinding binding= DataBindingUtil.inflate(inflater, R.layout.code_edit_text_layout, this, true);
        binding.setHandler(this);
        binding.leftText.setTextSize(leftTextSize);
        binding.leftText.setText(leftText);
        binding.content.setHint(hitText);
        binding.content.setInputType(inputType);
        binding.codeImage.setImageBitmap(codeUtils.createBitmap());
        binding.content.setOnFocusChangeListener((v, hasFocus)->{
            if (hasFocus){
                binding.line.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.wx_theme_green));
            }else {
                binding.line.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.edittext_line_color));
            }
        });
    }

    /**
     * 更新图片验证码
     * @param view
     */
    public void changeCode(View view){
        if (view instanceof ImageView){
            ((ImageView) view).setImageBitmap(codeUtils.createBitmap());
        }
    }

    public Boolean checkResult(String value){
        if (codeUtils != null){
            return codeUtils.checkCode(value);
        }
        return false;
    }
}
