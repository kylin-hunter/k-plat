package io.github.kylinhunter.plat.search.protocol;

import io.github.kylinhunter.commons.utils.json.JsonOptions;
import io.github.kylinhunter.commons.utils.json.JsonUtils;
import io.github.kylinhunter.plat.search.bean.index.IndexBean;
import java.util.List;

/**
 * @author BiJi'an
 * @description
 * @date 2023-11-11 23:41
 */
public class ProtocolHelper {

  public static  <T extends IndexBean> List<T> toIndex(String content, Class<T> clazz) {
    return JsonUtils.readToListObject(content, clazz, JsonOptions.NO_FAIL_SNAKE);
  }

  public static String toString(Object obj) {
    return JsonUtils.writeToString(obj, JsonOptions.NO_FAIL_SNAKE);
  }

}