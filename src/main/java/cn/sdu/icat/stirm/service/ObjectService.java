package cn.sdu.icat.stirm.service;

import cn.sdu.icat.stirm.model.RealEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 信息对象业务类
 *
 * @author icatzfd
 * Created on 2020/6/8 15:13.
 */
public interface ObjectService {

    /**
     * 根据前缀获取对象信息列表
     *
     * @param prefix 对象前缀
     * @return 对象信息列表
     */
    List<RealEntity> getEntitiesByPrefix(String prefix);
}
