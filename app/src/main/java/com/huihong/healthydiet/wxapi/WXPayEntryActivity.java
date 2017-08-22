package com.huihong.healthydiet.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

/**
 * Created by zangyi_shuai_ge on 2017/8/9
 */

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    @Override
    public void onReq(BaseReq baseReq) {

        LogUtil.i("微信支付回调");
    }

    @Override
    public void onResp(BaseResp baseResp) {
        LogUtil.i("微信支付回调","onPayFinish,errCode=");
        if(baseResp.getType()== ConstantsAPI.COMMAND_PAY_BY_WX){
            LogUtil.i("微信支付回调","onPayFinish,errCode="+baseResp.errCode);
//            AlertDialog.Builderbuilder=newAlertDialog.Builder(this);
//            builder.setTitle(R.string.app_tip);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_wechat_pay_result);

    }
}
