package io.github.kylinhunter.plat.api.context;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.BeanUtils;

import io.github.kylinhunter.plat.api.auth.Token;
import io.github.kylinhunter.plat.api.module.core.bean.entity.User;
import io.github.kylinhunter.plat.api.module.core.constants.UserType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author BiJi'an
 * @description
 * @date 2022-05-11 02:55
 **/
@Data
public class DefaultUserContext implements UserContext, Serializable {

    @ApiModelProperty(value = "tenantId", hidden = true)
    private String tenantId = "";

    @ApiModelProperty(value = "userId", hidden = true)
    private String userId = "";

    @ApiModelProperty(value = "userCode", hidden = true)
    private String userCode = "";

    @ApiModelProperty(value = "userName", hidden = true)
    private String userName = "";

    @ApiModelProperty(value = "userType")
    private int userType;

    @ApiModelProperty(value = "roleIds", hidden = true)
    private List<String> roleIds;

    @ApiModelProperty(value = "roleCodes", hidden = true)
    private List<String> roleCodes;

    public DefaultUserContext(Token token) {
        BeanUtils.copyProperties(token, this);
    }

    public DefaultUserContext(User user) {
        BeanUtils.copyProperties(user, this);
        this.userType = user.getType();
        this.userId = user.getId();
    }

    @Override
    public boolean isDummy() {
        return false;
    }

    @Override
    public boolean isSuperAdmin() {
        return UserType.isSuperAdmin(this.userType);
    }
}
