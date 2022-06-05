package com.kylinhunter.plat.api.bean.vo.request;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author BiJi'an
 * @description
 * @date 2022-05-11 02:55
 **/
@Data
public class UserContext implements Serializable {

    @ApiModelProperty(value = "currentTenantId", hidden = true)
    private String currentTenantId = "";

    @ApiModelProperty(value = "currentUserId", hidden = true)
    private String currentUserId = "";

    @ApiModelProperty(value = "username", hidden = true)
    private String currentUserName = "";

    @ApiModelProperty(value = "admin", hidden = true)
    private boolean superuser = false;

    @ApiModelProperty(value = "agentCreator", hidden = true)
    private boolean agentCreator = false;

    @ApiModelProperty(value = "currentRoleIds", hidden = true)
    private List<String> currentRoleIds;

    @ApiModelProperty(value = "currentRoleCodes", hidden = true)
    private List<String> currentRoleCodes;
}
