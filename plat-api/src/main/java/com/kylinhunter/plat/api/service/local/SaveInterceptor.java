package com.kylinhunter.plat.api.service.local;

import com.kylinhunter.plat.api.bean.vo.VO;
import com.kylinhunter.plat.api.bean.vo.constants.VoType;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-06 22:59
 **/
public class SaveInterceptor<T extends VO> {

    public void before(T t) {
        final VoType voType = t.getVoType();

        if (voType == VoType.CREATE) {
            saveBefore(t);
            createBefore(t);

        } else if (voType == VoType.UPDATE) {
            saveBefore(t);
            updateBefore(t);
        }

    }

    public void saveBefore(T vo) {
    }

    public void createBefore(T vo) {
    }

    public void updateBefore(T vo) {
    }

    public void after(T t) {
        final VoType voType = t.getVoType();

        if (voType == VoType.CREATE) {
            saveAfter(t);
            createAfter(t);

        } else if (voType == VoType.UPDATE) {
            saveAfter(t);
            updateAfter(t);
        }

    }

    public void saveAfter(T vo) {
    }

    public void createAfter(T vo) {
    }

    public void updateAfter(T vo) {
    }

}
