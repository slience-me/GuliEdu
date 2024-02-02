package xyz.slienceme.serurity.security;

import xyz.slienceme.commonutils.MD5;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * <p>
 * t密码的处理方法类型
 * </p>
 *
 * @author slience_me
 * @since 2024-02-02
 */
@Component
public class DefaultPasswordEncoder implements PasswordEncoder {

    public DefaultPasswordEncoder() {
        this(-1);
    }

    /**
     * @param strength
     *            the log rounds to use, between 4 and 31
     */
    public DefaultPasswordEncoder(int strength) {

    }

    /**
     * 加密
     * @param rawPassword rawPassword
     * @return 加密后的数据
     */
    public String encode(CharSequence rawPassword) {
        return MD5.encrypt(rawPassword.toString());
    }


    /**
     * 匹配
     * @param rawPassword rawPassword
     * @param encodedPassword encodedPassword
     * @return 是否匹配
     */
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(MD5.encrypt(rawPassword.toString()));
    }
}