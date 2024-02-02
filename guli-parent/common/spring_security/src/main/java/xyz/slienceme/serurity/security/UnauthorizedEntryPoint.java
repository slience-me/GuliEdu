package xyz.slienceme.serurity.security;

import xyz.slienceme.commonutils.R;
import xyz.slienceme.commonutils.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 未授权的统一处理方式
 * </p>
 *
 * @author slience_me
 * @since 2024-02-02
 */
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {

    /**
     * 未授权返回错误码
     * @param request request
     * @param response response
     * @param authException authException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        ResponseUtil.out(response, R.error());
    }
}
