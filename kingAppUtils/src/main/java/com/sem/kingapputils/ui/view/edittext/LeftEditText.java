package com.sem.kingapputils.ui.view.edittext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.sem.kingapputils.R;
import com.sem.kingapputils.databinding.LeftEditTextLayoutBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.viewpager.widget.ViewPager;

/**
 * @ProjectName: WidgetDemo
 * @Package: com.example.widgetdemo.edittext
 * @ClassName: LeftEditTextLayout
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2021/5/7 8:49
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/7 8:49
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LeftEditText extends LinearLayoutCompat {

    private int lineColor;
    private float leftTextSize;
    private String hitText;
    private String leftText;
    private int inputType;

    public ObservableField<String> content;

    @BindingAdapter("content")
    public static void setContent(LeftEditText vp, ObservableField<String> value){
        vp.content = value;
    }

    public LeftEditText(@NonNull Context context) {
        super(context);
    }

    public LeftEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, R.attr.titleTextStyle);
    }

    public LeftEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EditTextLayout, defStyleAttr, 0);
        leftText = typedArray.getString(R.styleable.EditTextLayout_leftText);
        hitText = typedArray.getString(R.styleable.EditTextLayout_hintText);
        lineColor = typedArray.getColor(R.styleable.EditTextLayout_lineColor, Color.parseColor("#1E90FF"));
        leftTextSize = typedArray.getFloat(R.styleable.EditTextLayout_leftTextSize,16);
        inputType = typedArray.getInt(R.styleable.EditTextLayout_customInputType,1);
        initView();
    }

    private void initView(){
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LeftEditTextLayoutBinding binding= DataBindingUtil.inflate(inflater, R.layout.left_edit_text_layout, this, true);
        binding.setHandler(this);
        binding.leftText.setTextSize(leftTextSize);
        binding.leftText.setText(leftText);
        binding.content.setHint(hitText);
        binding.content.setInputType(inputType);
        binding.content.setOnFocusChangeListener((v, hasFocus)->{
            if (hasFocus){
                binding.line.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.wx_theme_green));
            }else {
                binding.line.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.edittext_line_color));
            }
        });
    }
}
