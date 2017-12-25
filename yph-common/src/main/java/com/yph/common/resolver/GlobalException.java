package com.yph.common.resolver;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 *  全局异常步骤
 *
 * @author : Administrator Hzhan
 * @create ：2017/12/25
 **/
@Slf4j
public class GlobalException implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        ModelAndView mv = new ModelAndView();

        log.info("【 全局异常捕获 】 <---------------------------> {}",e.getMessage());

        // 处理异常  ---->
        if(e instanceof RuntimeException){
            FastJsonJsonView view = new FastJsonJsonView();
            Map<String, Object> attributes = new HashMap<String, Object>();
            attributes.put("code", "1000001");
            attributes.put("msg", e.getMessage());
            view.setAttributesMap(attributes);
            mv.setView(view);
        }else{
            mv.setViewName("error/error500.jsp");
        }
        return mv;
    }


    /**
     *  保存异常信息
     * @param e
     */
    public void saveExceptionLog(Exception e){
        // 做一系列的保存日志操作。

    }

    /**
     *  将异常信息 邮件通知负责人
     * @param e
     */
    public void sendExceptionMsgToEmail(Exception e){

    }


}
