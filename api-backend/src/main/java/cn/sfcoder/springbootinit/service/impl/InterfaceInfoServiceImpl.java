package cn.sfcoder.springbootinit.service.impl;

import cn.sfcoder.springbootinit.common.ErrorCode;
import cn.sfcoder.springbootinit.exception.BusinessException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.sfcoder.springbootinit.model.entity.InterfaceInfo;
import cn.sfcoder.springbootinit.service.InterfaceInfoService;
import cn.sfcoder.springbootinit.mapper.InterfaceInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
* @author Lenovo
* @description 针对表【interface_info(接口信息)】的数据库操作Service实现
* @createDate 2024-02-10 17:13:35
*/
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService{

    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String name = interfaceInfo.getName();
        // 创建时，所有参数必须非空
        if (add) {
            if (StringUtils.isAnyBlank(name)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        }
        if (StringUtils.isNotBlank(name) && name.length() > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "名称过长");
        }
    }


}




