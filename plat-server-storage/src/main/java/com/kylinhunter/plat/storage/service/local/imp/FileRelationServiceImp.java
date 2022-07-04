package com.kylinhunter.plat.storage.service.local.imp;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kylinhunter.plat.api.module.storage.bean.entity.FileRelation;
import com.kylinhunter.plat.api.module.storage.bean.vo.FileRelationReqCreate;
import com.kylinhunter.plat.api.module.storage.bean.vo.FileRelationReqQuery;
import com.kylinhunter.plat.api.module.storage.bean.vo.FileRelationReqUpdate;
import com.kylinhunter.plat.api.module.storage.bean.vo.FileRelationResp;
import com.kylinhunter.plat.api.module.storage.bean.vo.FileRelationVO;
import com.kylinhunter.plat.dao.service.local.CommonServiceImpl;
import com.kylinhunter.plat.storage.dao.mapper.FileRelationMapper;
import com.kylinhunter.plat.storage.service.local.FileRelationService;

/**
 * <p>
 * FileRelationServiceImp 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-07-05
 */
@Service
public class FileRelationServiceImp
        extends CommonServiceImpl<FileRelationMapper, FileRelation,
        FileRelationReqCreate, FileRelationReqUpdate,
        FileRelationResp, FileRelationVO, FileRelationReqQuery> implements FileRelationService {

    @Override
    public int countByFileId(String fileId) {
        final LambdaQueryWrapper<FileRelation> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(FileRelation::getFileId, fileId);
        return this.baseMapper.selectCount(queryWrapper);
    }
}