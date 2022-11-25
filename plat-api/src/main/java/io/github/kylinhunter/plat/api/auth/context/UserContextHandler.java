package io.github.kylinhunter.plat.api.auth.context;

import io.github.kylinhunter.plat.api.auth.Token;
import io.github.kylinhunter.plat.api.context.UserContext;
import io.github.kylinhunter.plat.api.module.core.bean.entity.User;

/**
 * @author BiJi'an
 * @description 日志追踪
 * @date 2022/01/01
 **/
public interface UserContextHandler {
    /**
     * @return io.github.kylinhunter.plat.api.context.UserContext
     * @title create
     * @description
     * @author BiJi'an
     * @date 2022-06-11 00:41
     */
    UserContext create(Token token);

    /**
     * @return io.github.kylinhunter.plat.api.context.UserContext
     * @title create
     * @description
     * @author BiJi'an
     * @date 2022-06-11 00:41
     */
    UserContext create(User user);

    /**
     * @return io.github.kylinhunter.plat.api.context.UserContext
     * @title get get
     * @description
     * @author BiJi'an
     * @date 2022-06-11 00:40
     */
    UserContext get();

    UserContext get(boolean check);

    /**
     * @return void
     * @title remove
     * @description
     * @author BiJi'an
     * @date 2022-06-11 00:41
     */
    void remove();

}
