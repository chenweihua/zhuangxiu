package com.jyt.baseapp.model;

import com.jyt.baseapp.api.BeanCallback;
import com.jyt.baseapp.api.Const;
import com.jyt.baseapp.api.Path;
import com.jyt.baseapp.bean.BaseJson;
import com.jyt.baseapp.bean.InfoBean;
import com.jyt.baseapp.util.BaseUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;

import okhttp3.Call;

/**
 * @author LinWei on 2017/11/20 13:36
 */
public class PersonModel {
    public void getPersonInfo(final OngetPersonInfoListener listener){
        OkHttpUtils
                .get()
                .url(Path.URL_MapDatas)
                .addParams("token", BaseUtil.getSpString(Const.UserToken))
                .addParams("method","getUserDetail")
                .addParams("page","0")
                .addParams("searchValue",BaseUtil.getSpString(Const.PositionID))
                .build()
                .execute(new BeanCallback<BaseJson<List<InfoBean>>>() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(BaseJson<List<InfoBean>> response, int id) {
                        if (listener!=null && response.ret){
                            listener.Result(true,response.data.get(0));
                        }
                    }
                });
    }

    public interface OngetPersonInfoListener{
        void Result(boolean isSuccess,InfoBean data);
    }


}
