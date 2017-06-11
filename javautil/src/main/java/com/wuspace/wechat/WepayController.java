package com.wuspace.wechat;

import com.google.common.base.Throwables;
import com.google.zxing.EncodeHintType;
import me.hao0.wepay.core.Notifies;
import me.hao0.wepay.util.Maps;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/wepay")
public class WepayController {
    private static final Logger logger = LoggerFactory.getLogger(Notifies.class);

    @Autowired
    private WepaySupport wepaySupport;

    /**
     * 支付成功通知
     *
     * @param request 请求对象
     * @return 处理结果
     */
    @RequestMapping("/paid")
    public String paid(HttpServletRequest request) {

        String notifyXml = getPostRequestBody(request);
        if (notifyXml.isEmpty()) {
            return wepaySupport.notifyNotOk("body为空");
        }

        Map<String, Object> notifyParams = Maps.toMap(notifyXml);

        if (wepaySupport.verifySign(notifyParams)) {

            // TODO business logic
            if (notifyParams.get("return_code").toString().equals("SUCCESS")) {
                String outTradeNo = notifyParams.get("out_trade_no").toString();
                // TODO
            }

            logger.info("verify sign success: {}", notifyParams);

            return wepaySupport.notifyOk();
        } else {
            logger.error("verify sign failed: {}", notifyParams);
            return wepaySupport.notifyNotOk("签名失败");
        }
    }

    /**
     * 传入从 WepaySupport.java 的 qrPay 方法获取的返回值生成支付二维码图片
     *
     * @param code
     * @param response
     */
    @GetMapping("/qrCode")
    public void qrCode(@RequestParam(value = "code") String code, HttpServletResponse response) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = QRCode.from(code)
                    .withHint(EncodeHintType.MARGIN, 0)
                    .withSize(200, 200).to(ImageType.PNG).stream();
            response.setHeader("Content-Type", "image/png");
            response.getOutputStream().write(byteArrayOutputStream.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getPostRequestBody(HttpServletRequest req) {
        if (req.getMethod().equals("POST")) {
            StringBuilder sb = new StringBuilder();
            try (BufferedReader br = req.getReader()) {
                char[] charBuffer = new char[128];
                int bytesRead;
                while ((bytesRead = br.read(charBuffer)) != -1) {
                    sb.append(charBuffer, 0, bytesRead);
                }
            } catch (IOException e) {
                logger.warn("failed to read request body, cause: {}", Throwables.getStackTraceAsString(e));
            }
            return sb.toString();
        }
        return "";
    }
}
