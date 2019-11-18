package com.github.wuchao.webproject.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public abstract class NetUtils {

    private static final Logger log = LoggerFactory.getLogger(NetUtils.class);

    /**
     * 根据 Request 获取客户端 IP
     * [根据Request获取客户端IP](https://www.cnblogs.com/icerainsoft/p/3584532.html)
     *
     * @return
     */
    public static String getRemoteHost() {
        if (RequestContextHolder.getRequestAttributes() != null) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            if (request != null) {
                String ip = request.getHeader("x-forwarded-for");
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("Proxy-Client-IP");
                }
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("WL-Proxy-Client-IP");
                }
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getRemoteAddr();
                }
                return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
            }
        }
        return "Unknown";
    }

    /**
     * 远程调用 http 接口
     *
     * @param url
     * @param type
     * @return
     */
    public static Object httpRequest(String url, String type) {
        long start = System.currentTimeMillis();
        HttpClient client = HttpClients.createDefault();

        try {
            URL u = new URL(url);
            URI uri = new URI(u.getProtocol(), null, u.getHost(), u.getPort(), u.getPath(), u.getQuery(), null);

            HttpUriRequest httpUriRequest = null;
            if (StringUtils.isBlank(type)) {
                type = "GET";
            }
            if ("GET".equalsIgnoreCase(type)) {
                httpUriRequest = new HttpGet(uri);
            } else if ("PATCH".equalsIgnoreCase(type)) {
                httpUriRequest = new HttpPatch(uri);
            } else if ("POST".equalsIgnoreCase(type)) {
                httpUriRequest = new HttpPost(uri);
            } else if ("PUT".equalsIgnoreCase(type)) {
                httpUriRequest = new HttpPut(uri);
            } else if ("DELETE".equalsIgnoreCase(type)) {
                httpUriRequest = new HttpDelete(uri);
            }

            if (httpUriRequest != null) {
                HttpResponse response = client.execute(httpUriRequest);
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    return EntityUtils.toString(response.getEntity());
                } else {
                    return response;
                }
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {
            log.debug("请求{}耗时{}ms", url, System.currentTimeMillis() - start);
        }

        return null;
    }

}
