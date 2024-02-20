package cn.sfcoder.springbootinit.model.dto.interfaceinfo;

/**
 * @Author: refain
 * @Description:
 * @Date: 2024/2/18 23:29
 * @Version: 1.0
 */

import lombok.Data;

import java.io.Serializable;

/**
 * 接口调用请求
 *
 * @TableName product
 */
@Data
public class InterfaceInfoInvokeRequest implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户请求参数
     */
    private String userRequestParams;

    private static final long serialVersionUID = 1L;
}
