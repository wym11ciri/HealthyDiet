package com.huihong.healthydiet.utils.current;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.LoginActivity;
import com.huihong.healthydiet.activity.base.ActivityCollector;
import com.huihong.healthydiet.mInterface.HttpUtilsListener;
import com.huihong.healthydiet.utils.common.SPUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by zangyi_shuai_ge on 2017/7/29
 */

public class HttpUtils {

    private static AlertDialog mAlertDialog;

    private static AlertDialog getAlertDialog(final Context mContext) {
        if (mAlertDialog != null) {
            mAlertDialog.dismiss();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("您已被挤下线或Token失效,请重新登录");

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //重置当前内容
                SPUtils.put(mContext, "isDoSimpleTest", false);
                SPUtils.put(mContext, "isLogin", false);
                ActivityCollector.finishAll();//销毁所有界面
                Intent mIntent = new Intent(mContext, LoginActivity.class);
                mContext.startActivity(mIntent);
                //
            }
        });
        builder.setCancelable(false);
        mAlertDialog = builder.create();
        return mAlertDialog;

    }

    //携带token
    public static void sendHttpAddToken(final Context mContext
            , String url
            , Map<String, String> pMap
            , final HttpUtilsListener mHttpUtilsListener) {

        //传入Url
        PostFormBuilder mBuilder =
                OkHttpUtils
                        .post()
                        .url(url);

        //判断是否有传入参数
        if (pMap != null) {
            //传入键值对
            for (String obj : pMap.keySet()) {
                String value = pMap.get(obj);
                mBuilder.addParams(obj, value);
            }
            mBuilder.addParams("Token", (String) SPUtils.get(mContext, "Token", ""));
        }

        //设置监听回调
        mBuilder.build().execute(
                new StringCallback() {
                    @Override
                    public void onError(okhttp3.Call call, Exception e, int id) {
                        Toast.makeText(mContext, R.string.service_error, Toast.LENGTH_SHORT).show();
                        mHttpUtilsListener.onError(call, e, id);
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int HttpCode = jsonObject.getInt("HttpCode");

                            if (HttpCode == 700) {
                                AlertDialog mDialog = getAlertDialog(mContext);
                                mDialog.show();

                            } else {
                                mHttpUtilsListener.onResponse(response, id);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }


}
