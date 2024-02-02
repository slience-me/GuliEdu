package xyz.slienceme.serurity.config;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import xyz.slienceme.serurity.filter.TokenAuthenticationFilter;
import xyz.slienceme.serurity.filter.TokenLoginFilter;
import xyz.slienceme.serurity.security.DefaultPasswordEncoder;
import xyz.slienceme.serurity.security.TokenLogoutHandler;
import xyz.slienceme.serurity.security.TokenManager;
import xyz.slienceme.serurity.security.UnauthorizedEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * <p>
 * Security配置类
 * </p>
 *
 * @author slience_me
 * @since 2024-02-02
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        // 根据自己的需求开启 - 注解配置方法安全
        prePostEnabled = true  // 根绝权限控制
)
public class TokenWebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 自定义查询数据库类
     */
    private UserDetailsService userDetailsService;
    /**
     * Token管理器
     */
    private TokenManager tokenManager;
    /**
     * 密码加密方式
     */
    private DefaultPasswordEncoder defaultPasswordEncoder;
    private RedisTemplate redisTemplate;

    /**
     * 注入需要的类，可根据自己的需要进行改造
     * @param userDetailsService 自定义查询数据库类
     * @param defaultPasswordEncoder 密码加密方式
     * @param tokenManager Token管理器
     */
    @Autowired
    public TokenWebSecurityConfig(UserDetailsService userDetailsService, DefaultPasswordEncoder defaultPasswordEncoder,
                                  TokenManager tokenManager, RedisTemplate redisTemplate) {
        this.userDetailsService = userDetailsService;
        this.defaultPasswordEncoder = defaultPasswordEncoder;
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 配置设置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .authenticationEntryPoint(new UnauthorizedEntryPoint())
                .and().csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                // 退出请求路径
                .and().logout().logoutUrl("/admin/acl/index/logout")
                // 退出处理器
                .addLogoutHandler(new TokenLogoutHandler(tokenManager,redisTemplate)).and()
                // 认证过滤器
                .addFilter(new TokenLoginFilter(authenticationManager(), tokenManager, redisTemplate))
                // 授权过滤器
                .addFilter(new TokenAuthenticationFilter(authenticationManager(), tokenManager, redisTemplate)).httpBasic();
    }

    /**
     * 数据源配置、加密方式配置
     * @param auth auth
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 这里如果需要BCryptPassword加密，第二个参数传入passwordEncoder()即可
        auth.userDetailsService(userDetailsService).passwordEncoder(defaultPasswordEncoder);
    }

    /**
     * 配置哪些请求不拦截，这些是没有认证就可以访问的路径
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api/**",
                "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**"
               );
    }
}