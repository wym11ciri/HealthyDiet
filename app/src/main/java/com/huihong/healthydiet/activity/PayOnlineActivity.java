package com.huihong.healthydiet.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseTitleActivity2;
import com.huihong.healthydiet.pay.ali.PayResult;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zangyi_shuai_ge on 2017/7/24
 * 在线支付
 */

public class PayOnlineActivity extends BaseTitleActivity2 {


    @BindView(R.id.tvPayNum)
    TextView tvPayNum;
    @BindView(R.id.tvPayName)
    TextView tvPayName;
    @BindView(R.id.tvPayTime)
    TextView tvPayTime;
    @BindView(R.id.ivPayAli)
    ImageView ivPayAli;
    @BindView(R.id.layoutPayAli)
    LinearLayout layoutPayAli;
    @BindView(R.id.ivPayWeChart)
    ImageView ivPayWeChart;
    @BindView(R.id.layoutPayWeChart)
    LinearLayout layoutPayWeChart;
    @BindView(R.id.btPay)
    Button btPay;


    private static final int SDK_PAY_FLAG = 1;
    private boolean isUseAliPay = true;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(PayOnlineActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(PayOnlineActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
            }

        }
    };

    @Override
    public int setLayoutId() {
        return R.layout.activity_pay_online;
    }

    private IWXAPI weChartApi;
    String RecipeId;
    String payName;
    String payTime;
    String payMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("在线支付");

        weChartApi = WXAPIFactory.createWXAPI(PayOnlineActivity.this, null);
        weChartApi.registerApp("wxd930ea5d5a258f4f");

//        mIntent.putExtra("payMoney", payMoney);
//        mIntent.putExtra("payName", paName);
//        mIntent.putExtra("payTime", tvTimeSelect.getText().toString().trim());
//        mIntent.putExtra("RecipeId", RecipeId);

        payMoney = getIntent().getStringExtra("payMoney");
        payName = getIntent().getStringExtra("payName");
        payTime = getIntent().getStringExtra("payTime");
        RecipeId = getIntent().getStringExtra("RecipeId");

        tvPayName.setText(payName);
        tvPayNum.setText(payMoney);
        tvPayTime.setText(payTime);

    }

    @OnClick({R.id.layoutPayAli, R.id.layoutPayWeChart})
    public void onViewClicked(View view) {
        ivPayAli.setImageResource(R.mipmap.point1);
        ivPayWeChart.setImageResource(R.mipmap.point1);
        switch (view.getId()) {
            case R.id.layoutPayAli:
                isUseAliPay = true;
                ivPayAli.setImageResource(R.mipmap.point2);
                break;
            case R.id.layoutPayWeChart:
                isUseAliPay = false;
                ivPayWeChart.setImageResource(R.mipmap.point2);
                break;
        }
    }

    @OnClick(R.id.btPay)
    public void onViewClicked() {
        if (isUseAliPay) {
            final String orderInfo = "";   // 订单信息
            Runnable payRunnable = new Runnable() {
                @Override
                public void run() {
                    PayTask alipay = new PayTask(PayOnlineActivity.this);
                    Map<String, String> result = alipay.payV2(orderInfo, true);
                    LogUtil.i("AliPayResult", result.toString());
                    Message msg = new Message();
                    msg.what = SDK_PAY_FLAG;
                    msg.obj = result;
                    mHandler.sendMessage(msg);
                }
            };

            Thread payThread = new Thread(payRunnable);
            payThread.start();
        } else {


            PayReq request = new PayReq();
            request.appId = "wxd930ea5d5a258f4f";
            request.partnerId = "1900000109";
            request.prepayId = "1101000000140415649af9fc314aa427";
            request.packageValue = "Sign=WXPay";
            request.nonceStr = "1101000000140429eb40476f8896f4c9";
            request.timeStamp = "1398746574";
            request.sign = "7FFECB600D7157C5AA49810D2D8F28BC2811827B";
            boolean zz = weChartApi.sendReq(request);
            LogUtil.i("微信支付", zz + "");

        }


    }
}
