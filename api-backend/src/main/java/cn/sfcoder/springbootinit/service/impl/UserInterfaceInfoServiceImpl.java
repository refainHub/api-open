package cn.sfcoder.springbootinit.service.impl;

import cn.sfcoder.springbootinit.common.ErrorCode;
import cn.sfcoder.springbootinit.exception.BusinessException;
import cn.sfcoder.springbootinit.model.entity.InterfaceInfo;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.sfcoder.springbootinit.model.entity.UserInterfaceInfo;
import cn.sfcoder.springbootinit.service.UserInterfaceInfoService;
import cn.sfcoder.springbootinit.mapper.UserInterfaceInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
* @author Lenovo
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service实现
* @createDate 2024-02-20 16:24:18
*/
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceInfoService{
    
    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {
        if (userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 如果是添加操作
        if (add) {
            if (userInterfaceInfo.getInterfaceInfoId()<=0 ||userInterfaceInfo.getUserId()<=0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"接口或用户不存在");
            }
        }
        if (userInterfaceInfo.getLeftNum()<=0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "无剩余次数");
        }
    }

    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        if(interfaceInfoId<0 || userId<0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //使用 UpdateWrapper 对象来构建更新条件

        UpdateWrapper<UserInterfaceInfo> updateWrapper=new UpdateWrapper<>();
        //在 updateWrapper 中设置两个条件，interfaceInfoId 等于给定的 interfaceInfoId 和 userId 等于给定的 userId
        updateWrapper.eq("interfaceInfoId",interfaceInfoId);
        updateWrapper.eq("userId",userId);
        // setSql 方法用于设置更新的SQL语句，这里通过SQL表达式实现了两个字段的更新操作;
        // leftNum=leftNum-1 和 totalNum=totalNum+1
        updateWrapper.setSql("leftNum = leftNum-1 , totalNum = totalNum+1");
        //调用update方法执行更新操作，并返回更新是否成功的结果
        return this.update(updateWrapper);
    }
}




