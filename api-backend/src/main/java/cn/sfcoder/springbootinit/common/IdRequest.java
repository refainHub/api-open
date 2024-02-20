package cn.sfcoder.springbootinit.common;

/**
 * @Author: refain
 * @Description:
 * @Date: 2024/2/18 21:38
 * @Version: 1.0
 */

import lombok.Data;

import java.io.Serializable;

/**
 * 删除请求
 *
 * @author yupi
 */
@Data
public class IdRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    private static final long serialVersionUID = 1L;
}
