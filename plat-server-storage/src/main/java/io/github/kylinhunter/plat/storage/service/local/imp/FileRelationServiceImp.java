package io.github.kylinhunter.plat.storage.service.local.imp;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.github.kylinhunter.plat.api.module.storage.bean.entity.FileRelation;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileRelationReqCreate;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileRelationReqQuery;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileRelationReqUpdate;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileRelationResp;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileRelationVO;
import io.github.kylinhunter.plat.dao.service.local.CommonServiceImpl;
import io.github.kylinhunter.plat.storage.dao.mapper.FileRelationMapper;
import io.github.kylinhunter.plat.storage.service.local.FileRelationService;

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