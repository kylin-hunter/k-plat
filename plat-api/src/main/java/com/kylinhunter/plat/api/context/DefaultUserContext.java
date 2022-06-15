package com.kylinhunter.plat.api.context;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.kylinhunter.plat.api.auth.Token;
import com.kylinhunter.plat.api.module.core.bean.entity.User;
import com.kylinhunter.plat.api.module.core.constants.UserType;

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

    @ApiModelProperty(value = "admin", hidden = true)
    private boolean admin = false;

    @ApiModelProperty(value = "账户类型")
    private int type;

    @ApiModelProperty(value = "账户类型")
    private boolean checkTenant;

    @ApiModelProperty(value = "roleIds", hidden = true)
    private List<String> roleIds;

    @ApiModelProperty(value = "roleCodes", hidden = true)
    private List<String> roleCodes;

    public DefaultUserContext(Token token) {
        BeanUtils.copyProperties(token, this);
    }

    public DefaultUserContext(User user) {
        BeanUtils.copyProperties(user, this);
        this.userId = user.getId();
        this.admin = UserType.isAdmin(user.getType());
    }

    @Override
    public boolean isDummy() {
        return false;
    }
}
