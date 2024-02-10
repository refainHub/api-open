package cn.sfcoder.springbootinit.model.dto.interfaceinfo;

import cn.sfcoder.springbootinit.common.PageRequest;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询请求
 *
 * 
 * 
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InterfaceInfoAddRequest extends PageRequest implements Serializable {


    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 接口地址
     */
    private String url;

    /**
     * 请求头
     */
    private String requestHeader;

    /**
     * 响应头
     */
    private String responseHeader;


    /**
     * 请求类型
     */
    private String method;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}