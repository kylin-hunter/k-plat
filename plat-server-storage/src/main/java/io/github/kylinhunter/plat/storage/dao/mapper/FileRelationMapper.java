package io.github.kylinhunter.plat.storage.dao.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import io.github.kylinhunter.plat.api.module.storage.bean.entity.FileRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author biji'an
 * @since 2022-07-05
 */
@Mapper
@Repository
public interface FileRelationMapper extends BaseMapper<FileRelation> {

}
