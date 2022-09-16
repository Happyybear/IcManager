package com.sem.kingapputils.ui.base.viewmodel;

import com.kunminx.architecture.ui.callback.UnPeekLiveData;
import com.sem.kingapputils.http.ApiException;
import com.sem.kingapputils.ui.base.repo.BaseRepository;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.Disposable;
import per.goweii.rxhttp.core.RxLife;

/**
 * @ProjectName: VQCStation
 * @Package: com.sem.kingapputils.ui.mvvm.viewmodel
 * @ClassName: BaseViewModel
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2021/5/8 14:57
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/8 14:57
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class BaseViewModel extends ViewModel {

    protected BaseRepository repository;

     protected abstract BaseRepository initRepository();
    /**
     * 错误信息liveData
     */
    public final UnPeekLiveData<ApiException> errorLiveData = new UnPeekLiveData<ApiException>();

    public BaseViewModel() {
    }

    private RxLife rxLife;

    public RxLife getRxLife() {
        return rxLife;
    }

    public void addToRxLife(Disposable disposable) {
        if (rxLife != null) {
            rxLife.add(disposable);
        }
    }

    public void stopNet(){
        if (repository == null){
            repository = initRepository();
        }
        if (repository != null) {
            repository.stopNet();
        }
    }

}
