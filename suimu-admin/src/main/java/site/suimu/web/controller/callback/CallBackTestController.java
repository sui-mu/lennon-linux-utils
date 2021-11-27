package site.suimu.web.controller.callback;


import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/callback/test")
public class CallBackTestController {


    private static final Logger log = LoggerFactory.getLogger(CallBackTestController.class);

    @GetMapping
    public void testGet(HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        // Get request URL.
        map.put("URL", request.getRequestURL());
        map.put("Protocol", request.getProtocol());
        // 获取header信息
        List<Map<String, String>> headerList = new ArrayList<>();
        Map<String, String> headerMaps = new HashMap<String, String>();
        for (Enumeration<String> enu = request.getHeaderNames(); enu.hasMoreElements(); ) {
            String name = enu.nextElement();
            headerMaps.put(name, request.getHeader(name));
        }
        headerList.add(headerMaps);
        map.put("headers", headerList);
        //获取parameters信息

        List<Map<String, String>> parameterList = new ArrayList<>();
        Map<String, String> parameterMaps = new HashMap<String, String>();
        for (Enumeration<String> names = request.getParameterNames(); names.hasMoreElements(); ) {
            String name = names.nextElement();
            parameterMaps.put(name, request.getParameter(name));
        }
        parameterList.add(parameterMaps);
        map.put("parameters", parameterList);
        String line = "";
        int idx = request.getRequestURL().indexOf("?");
        if (idx != -1) {
            line = request.getRequestURL().substring(idx + 1);
        } else {
            line = null;
        }
        if (line != null) {
            map.put("Context", new String[]{line});
        }
        log.info("GET请求体, 接收请求报文：\n" + JSONObject.toJSONString(map));
    }


    @PostMapping
    public void testPost(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        log.info("POST请求体:  {}", request);
        Map<String, Object> map = new HashMap<String, Object>();
        // Get request URL.
        map.put("URL", request.getRequestURL());
        map.put("Protocol", request.getProtocol());
        // 获取header信息
        List<Map<String, String>> headerList = new ArrayList<>();
        Map<String, String> headerMaps = new HashMap<String, String>();
        for (Enumeration<String> enu = request.getHeaderNames(); enu.hasMoreElements(); ) {
            String name = enu.nextElement();
            headerMaps.put(name, request.getHeader(name));
        }
        headerList.add(headerMaps);
        map.put("headers", headerList);
        //获取parameters信息

        List<Map<String, String>> parameterList = new ArrayList<>();
        Map<String, String> parameterMaps = new HashMap<String, String>();
        for (Enumeration<String> names = request.getParameterNames(); names.hasMoreElements(); ) {
            String name = names.nextElement();
            parameterMaps.put(name, request.getParameter(name));
        }
        parameterList.add(parameterMaps);
        map.put("parameters", parameterList);
        if (jsonObject != null) {
            map.put("Context", jsonObject.toString());
        }
        log.info("POST请求体, 接收请求报文：\n" + JSONObject.toJSONString(map));
    }


}
