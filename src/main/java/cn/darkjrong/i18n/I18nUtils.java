package cn.darkjrong.i18n;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;

/**
 * i18n工具类
 *
 * @author Rong.Jia
 * @date 2025/03/27
 */
@Slf4j
public class I18nUtils {

    private static MessageSourceAccessor accessor;

    private static void getAccessor() {
        if (ObjectUtil.isNull(accessor)) {
            synchronized (I18nUtils.class) {
                if (ObjectUtil.isNull(accessor)) {
                    accessor = SpringUtil.getBean(MessageSourceAccessor.class);
                }
            }
        }
    }

    /**
     * 获取消息
     * @param code 编码
     * @return {@link String }
     */
    public static String getMessage(String code) {
        getAccessor();
        return accessor.getMessage(code);
    }











}
