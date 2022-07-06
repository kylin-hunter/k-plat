package com.kylinhunter.plat.kb.core.dao.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.kylinhunter.plat.kb.api.module.core.bean.entity.Doc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author biji'an
 * @since 2022-07-06
 */
@Mapper
@Repository
public interface DocMapper extends BaseMapper<Doc> {

}
