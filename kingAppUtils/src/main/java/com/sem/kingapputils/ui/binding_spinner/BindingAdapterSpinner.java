package com.sem.kingapputils.ui.binding_spinner;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.sem.kingapputils.utils.KArrayUtils;

import java.util.List;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

/**
 * @ProjectName: semtools
 * @Package: com.sem.kingapputils.ui.view.binding_spinner
 * @ClassName: BindingAdapterSpinner
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2021/6/2 15:24
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/6/2 15:24
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BindingAdapterSpinner {

    @BindingAdapter(value = "setEntries", requireAll = false)
    public static void setEntries(Spinner spinner, List items) {
        if (KArrayUtils.isEmpty(items)){
            return;
        }
        Context context = spinner.getContext();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //绑定 Adapter到控件
        spinner.setAdapter(adapter);
    }

    @BindingAdapter(value = "selectedPos", requireAll = false)
    public static void setSelectedPos(Spinner view, int setSelectedPos) {
        if (setSelectedPos >= 0) {
            if (view.getAdapter() != null && view.getAdapter().getCount() > setSelectedPos) {
                view.setSelection(setSelectedPos);
            }
        }
    }

    @InverseBindingAdapter(attribute = "selectedPos", event = "selectedChange")
    public static int getSelectedPos(Spinner view) {
        return view.getSelectedItemPosition();
    }

    @BindingAdapter(value = "selectedChange")
    public static void setChangeListener(Spinner view, InverseBindingListener listener) {
        view.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listener.onChange();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @BindingAdapter(value = "selectedPositionByte", requireAll = false)
    public static void setSelectedPositionByte(Spinner sp, byte selectedItem) {
        if (selectedItem >= 0) {
            if (sp.getAdapter() != null && sp.getAdapter().getCount() > selectedItem) {
                sp.setSelection(selectedItem);
            }
        }
    }

    @InverseBindingAdapter(attribute = "selectedPositionByte", event = "selectedByteChange")
    public static byte getSelectedPositionByte(Spinner view) {
        return (byte) view.getSelectedItemPosition();
    }


    @BindingAdapter(value = "selectedByteChange")
    public static void setByteChangeListener(Spinner view, InverseBindingListener listener) {
        view.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listener.onChange();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
