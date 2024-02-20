package cn.sfcoder.springbootinit.controller;
import cn.sfcoder.apiinterfacesdk.client.ApiClient;
import cn.sfcoder.springbootinit.annotation.AuthCheck;
import cn.sfcoder.springbootinit.common.*;
import cn.sfcoder.springbootinit.constant.CommonConstant;
import cn.sfcoder.springbootinit.constant.UserConstant;
import cn.sfcoder.springbootinit.exception.BusinessException;
import cn.sfcoder.springbootinit.model.dto.userinterfaceinfo.UserInterfaceInfoAddRequest;
import cn.sfcoder.springbootinit.model.dto.userinterfaceinfo.UserInterfaceInfoQueryRequest;
import cn.sfcoder.springbootinit.model.dto.userinterfaceinfo.UserInterfaceInfoUpdateRequest;
import cn.sfcoder.springbootinit.model.entity.UserInterfaceInfo;
import cn.sfcoder.springbootinit.model.entity.User;
import cn.sfcoder.springbootinit.service.UserInterfaceInfoService;
import cn.sfcoder.springbootinit.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 帖子接口
 *
 * @author yupi
 */
@RestController
@RequestMapping("/userinterfaceInfo")
@Slf4j
public class UserInterfaceInfoController {

    @Resource
    private UserInterfaceInfoService userinterfaceInfoService;

    @Resource
    private UserService userService;

    /**
     * 创建
     *
     */
    @PostMapping("/add")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Long> addUserInterfaceInfo(@RequestBody UserInterfaceInfoAddRequest userinterfaceInfoAddRequest, HttpServletRequest request) {
        if (userinterfaceInfoAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UserInterfaceInfo userinterfaceInfo = new UserInterfaceInfo();
        BeanUtils.copyProperties(userinterfaceInfoAddRequest, userinterfaceInfo);
        // 校验
        userinterfaceInfoService.validUserInterfaceInfo(userinterfaceInfo, true);
        User loginUser = userService.getLoginUser(request);
        userinterfaceInfo.setUserId(loginUser.getId());
        boolean result = userinterfaceInfoService.save(userinterfaceInfo);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        long newUserInterfaceInfoId = userinterfaceInfo.getId();
        return ResultUtils.success(newUserInterfaceInfoId);
    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> deleteUserInterfaceInfo(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        long id = deleteRequest.getId();
        // 判断是否存在
        UserInterfaceInfo oldUserInterfaceInfo = userinterfaceInfoService.getById(id);
        if (oldUserInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 仅本人或管理员可删除
        if (!oldUserInterfaceInfo.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = userinterfaceInfoService.removeById(id);
        return ResultUtils.success(b);
    }


    /**
     * 更新
     *
     * @param userinterfaceInfoUpdateRequest
     * @param request
     * @return
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateUserInterfaceInfo(@RequestBody UserInterfaceInfoUpdateRequest userinterfaceInfoUpdateRequest,
                                                     HttpServletRequest request) {
        if (userinterfaceInfoUpdateRequest == null || userinterfaceInfoUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UserInterfaceInfo userinterfaceInfo = new UserInterfaceInfo();
        BeanUtils.copyProperties(userinterfaceInfoUpdateRequest, userinterfaceInfo);
        // 参数校验
        userinterfaceInfoService.validUserInterfaceInfo(userinterfaceInfo, false);
        User user = userService.getLoginUser(request);
        long id = userinterfaceInfoUpdateRequest.getId();
        // 判断是否存在
        UserInterfaceInfo oldUserInterfaceInfo = userinterfaceInfoService.getById(id);
        if (oldUserInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 仅本人或管理员可修改
        if (!oldUserInterfaceInfo.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean result = userinterfaceInfoService.updateById(userinterfaceInfo);
        return ResultUtils.success(result);
    }


    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<UserInterfaceInfo> getUserInterfaceInfoById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UserInterfaceInfo userinterfaceInfo = userinterfaceInfoService.getById(id);
        return ResultUtils.success(userinterfaceInfo);
    }

    /**
     * 获取列表（仅管理员可使用）
     *
     * @param userinterfaceInfoQueryRequest
     * @return
     */
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @GetMapping("/list")
    public BaseResponse<List<UserInterfaceInfo>> listUserInterfaceInfo(UserInterfaceInfoQueryRequest userinterfaceInfoQueryRequest) {
        UserInterfaceInfo userinterfaceInfoQuery = new UserInterfaceInfo();
        if (userinterfaceInfoQueryRequest != null) {
            BeanUtils.copyProperties(userinterfaceInfoQueryRequest, userinterfaceInfoQuery);
        }
        QueryWrapper<UserInterfaceInfo> queryWrapper = new QueryWrapper<>(userinterfaceInfoQuery);
        List<UserInterfaceInfo> userinterfaceInfoList = userinterfaceInfoService.list(queryWrapper);
        return ResultUtils.success(userinterfaceInfoList);
    }

    /**
     * 分页获取列表
     *
     * @param userinterfaceInfoQueryRequest
     * @param request
     * @return
     */
    @GetMapping("/list/page")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<UserInterfaceInfo>> listUserInterfaceInfoByPage(UserInterfaceInfoQueryRequest userinterfaceInfoQueryRequest, HttpServletRequest request) {
        if (userinterfaceInfoQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UserInterfaceInfo userinterfaceInfoQuery = new UserInterfaceInfo();
        BeanUtils.copyProperties(userinterfaceInfoQueryRequest, userinterfaceInfoQuery);
        long current = userinterfaceInfoQueryRequest.getCurrent();
        long size = userinterfaceInfoQueryRequest.getPageSize();
        String sortField = userinterfaceInfoQueryRequest.getSortField();
        String sortOrder = userinterfaceInfoQueryRequest.getSortOrder();
        // 限制爬虫
        if (size > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<UserInterfaceInfo> queryWrapper = new QueryWrapper<>(userinterfaceInfoQuery);
        queryWrapper.orderBy(StringUtils.isNotBlank(sortField),
                sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        Page<UserInterfaceInfo> userinterfaceInfoPage = userinterfaceInfoService.page(new Page<>(current, size), queryWrapper);
        return ResultUtils.success(userinterfaceInfoPage);
    }

}
