package com.zyzh.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.adapter.servlet.callback.WebCallbackManager;
import com.alibaba.csp.sentinel.adapter.servlet.config.WebServletConfig;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.zyzh.controller.ArticleController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * sentinel 组件配置
 *
 * @version 1.0.0.1
 * @author: LeoWey
 * @createTime: 2024-03-28 14:43
 */
@Configuration
public class SentinelConfiguration {

    private static Logger logger = LoggerFactory.getLogger(SentinelConfiguration.class);
    public UrlBlockHandler urlBlockHandler() {
        return (request, response, ex) -> {
            if (ex instanceof FlowException) {
                // 触发接口限流
                logger.warn("FlowException: {}","触发接口限流");
            } else if (ex instanceof DegradeException) {
                // 触发熔断降级
                logger.warn("DegradeException: {}","触发熔断降级");
            } else if (ex instanceof SystemBlockException) {
                // 触发系统保护
                logger.warn("SystemBlockException: {}","触发系统保护");
            } else if (ex instanceof AuthorityException) {
                // 触发授权规则
                logger.warn("AuthorityException: {}","触发授权规则");
            }
            String errMsg = "Blocked by Sentinel (flow limiting)!" + ex.getRule().getResource();
            StringBuffer url = request.getRequestURL();

            if ("GET".equals(request.getMethod()) && StringUtil.isNotBlank(request.getQueryString())) {
                url.append("?").append(request.getQueryString());
            }

            if (StringUtil.isBlank(WebServletConfig.getBlockPage())) {
                response.setStatus(WebServletConfig.getBlockPageHttpStatus());
                PrintWriter out = response.getWriter();
                out.print("{\"code\":0,\"message\":\"" + errMsg + "\"}");
                out.flush();
                out.close();

            } else {
                String redirectUrl = WebServletConfig.getBlockPage() + "?http_referer=" + url.toString();
                // Redirect to the customized block page.
                response.sendRedirect(redirectUrl);
            }

        };
    }

    @PostConstruct
    public void init() {
        WebCallbackManager.setUrlBlockHandler(urlBlockHandler());
    }
}