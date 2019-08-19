package com.github.sophie.springbootrabbitmqdemo.reciver;

import com.alibaba.fastjson.JSONObject;
import org.springframework.context.annotation.Configuration;

/**
 * reciver
 *
 * @author sophie
 * @date 2019-08-19 18:23
 */
@Configuration
public class MessageReciver {

    public void process(JSONObject mes) {
        if (null == mes) {
            System.out.println("mes 为空");
        } else {
            System.out.println("mes = [" + mes + "]");
        }
    }

}
