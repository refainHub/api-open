package cn.sfcoder.springbootinit.service;

import cn.sfcoder.springbootinit.model.dto.interfaceinfo.InterfaceInfoQueryRequest;
import cn.sfcoder.springbootinit.model.entity.InterfaceInfo;

import cn.sfcoder.springbootinit.model.vo.InterfaceInfoVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
* @author Lenovo
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2024-02-10 17:13:35
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {
    /**
     * 校验
     *
     * @param interfaceInfo
     * @param add
     */
    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);

}
