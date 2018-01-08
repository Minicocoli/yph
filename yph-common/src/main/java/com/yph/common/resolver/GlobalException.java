package com.yph.common.resolver;

import com.alibaba.druid.support.json.JSONUtils;
import com.yph.common.result.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

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
            MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
            HashMap<String, Object> errorMap = new HashMap<>();
            errorMap.put("code","1");
            errorMap.put("msg",e.getMessage());
            jsonView.setAttributesMap(errorMap);
            mv.setView(jsonView);
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
