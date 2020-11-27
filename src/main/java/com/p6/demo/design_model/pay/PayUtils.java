package com.p6.demo.design_model.pay;

import com.google.common.collect.Maps;
import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Function3;
import io.vavr.Tuple2;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Map;

import static com.p6.demo.design_model.pay.PayUtils.PayType.DOMESTIC_WECHAT;
import static com.p6.demo.design_model.pay.PayUtils.PayType.OVERSEAS_EPAY;

public class PayUtils {

    public static final Map<PayType, Function1<Map<String, Object>, Tuple2<Object, Map<String, Object>>>>
        PAYMENT_RULES = Maps.newHashMap();

    public static final Function1<Map<String, Object>, Tuple2<Object, Map<String, Object>>> OVERSEAS_EPAY_FUNC =
        params -> {
            params.put("OVERSEAS_EPAY_FUNC", "AAAA");

            return new Tuple2<>(PayStatus.FAILURE, params);
        };

    public static final Function1<Map<String, Object>, Tuple2<Object, Map<String, Object>>> DOMESTIC_WECHAT_FUNC =
        params -> {
            params.put("DOMESTIC_WECHAT_FUNC", "BBBB");

            return new Tuple2<>(PayStatus.SUCCESS, params);
        };

    private static final Function2<String, String, PayType> PAY_TYPE_FUNC = (scope, payType) -> PayType.valueOf(
        StringUtils.join(Arrays.asList(scope, payType), PaymentConstants.SPLIT));

    public static final Function3<String, String, Map<String, Object>, Tuple2<Object, Map<String, Object>>> PAY_FUNC =
        (scope, payType, params) -> {

            PayType paymentMethod;
            try {
                paymentMethod = PAY_TYPE_FUNC.apply(scope, payType);
                return PAYMENT_RULES.get(paymentMethod).apply(params);
            } catch (Exception e) {
                throw new RuntimeException("Payment method could not be supported !!!");
            }
        };

    static {
        PAYMENT_RULES.put(DOMESTIC_WECHAT, DOMESTIC_WECHAT_FUNC);
        PAYMENT_RULES.put(OVERSEAS_EPAY, OVERSEAS_EPAY_FUNC);
    }

    /**
     * test
     *
     * @param args
     */
    public static void main(String[] args) {

        String scope   = "DOMESTIC";
        String payType = "WECHAT";

        Map<String, Object>                 params = Maps.newHashMap();
        Tuple2<Object, Map<String, Object>> rt     = PAY_FUNC.apply(scope, payType, params);
        System.out.println(rt._1() + "--->" + rt._2());

        scope = "OVERSEAS";
        payType = "EPAY";
        rt = PAY_FUNC.apply(scope, payType, params);
        System.out.println(rt._1() + "--->" + rt._2());

        scope = "DOMESTIC";
        payType = "ALIPAY";
        rt = PAY_FUNC.apply(scope, payType, params);
        System.out.println(rt._1() + "--->" + rt._2());
    }

    enum PayType {
        DOMESTIC_WECHAT, DOMESTIC_ALIPAY, DOMESTIC_UNIPAY_, OVERSEAS_GOOGLE, OVERSEAS_EPAY, OVERSEAS_FACEBOOK

    }

    enum PayStatus {
        SUCCESS, FAILURE
    }

    public static class PaymentConstants {
        public static final String SPLIT = "_";

    }

}
