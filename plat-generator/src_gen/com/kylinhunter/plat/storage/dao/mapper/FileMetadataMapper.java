package com.kylinhunter.plat.storage.dao.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.kylinhunter.plat.api.module.storage.bean.entity.FileMetadata;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author biji'an
 * @since 2022-07-03
 */
@Mapper
@Repository
public interface FileMetadataMapper extends BaseMapper<FileMetadata> {

}
