package com.kylinhunter.plat.core.init.initializer;

import org.springframework.stereotype.Component;

import com.kylinhunter.plat.api.module.core.bean.entity.Role;
import com.kylinhunter.plat.core.init.data.RoleInitDatas;
import com.kylinhunter.plat.core.service.local.RoleService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-12 23:53
 **/
@Component
@RequiredArgsConstructor
@Slf4j
@Setter
@Getter
public class Order02RoleInitializer extends BasicInitializer {
    private final RoleInitDatas roleInitData;
    private final RoleService roleService;

    @Override
    public void init() {

        roleInitData.getInitDatas().values().forEach(RoleCreate -> {

            final String RoleCode = RoleCreate.getCode();
            final Role Role = roleService.queryByCode(RoleCode);
            if (Role != null) {
                log.info("default role {} exist", RoleCode);
                roleInitData.addDbData(RoleCode, Role);
            } else {
                roleService.save(RoleCreate);
                log.info("default role {} created", RoleCode);
                roleInitData.addDbData(RoleCode, roleService.queryByCode(RoleCode));
            }
        });

    }
}
