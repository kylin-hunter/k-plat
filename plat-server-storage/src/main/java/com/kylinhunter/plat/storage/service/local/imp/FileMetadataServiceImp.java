package com.kylinhunter.plat.storage.service.local.imp;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kylinhunter.plat.api.module.storage.bean.entity.FileMetadata;
import com.kylinhunter.plat.api.module.storage.bean.vo.FileMetadataReqCreate;
import com.kylinhunter.plat.api.module.storage.bean.vo.FileMetadataReqQuery;
import com.kylinhunter.plat.api.module.storage.bean.vo.FileMetadataReqUpdate;
import com.kylinhunter.plat.api.module.storage.bean.vo.FileMetadataResp;
import com.kylinhunter.plat.api.module.storage.bean.vo.FileMetadataVO;
import com.kylinhunter.plat.dao.service.local.CommonServiceImpl;
import com.kylinhunter.plat.storage.dao.mapper.FileMetadataMapper;
import com.kylinhunter.plat.storage.service.local.FileMetadataService;

/**
 * <p>
 * FileMetadataServiceImp 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-30
 */
@Service
public class FileMetadataServiceImp
        extends CommonServiceImpl<FileMetadataMapper, FileMetadata,
        FileMetadataReqCreate, FileMetadataReqUpdate,
        FileMetadataResp, FileMetadataVO, FileMetadataReqQuery> implements FileMetadataService {

    @Override
    public FileMetadata findByMd5(String md5) {
        final LambdaQueryWrapper<FileMetadata> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(FileMetadata::getMd5, md5);
        queryWrapper.last("limit 1");
        queryWrapper.orderByAsc(FileMetadata::getSysCreatedTime);
        return this.baseMapper.selectOne(queryWrapper);
    }
}