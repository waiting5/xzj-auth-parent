package com.xzj.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.xzj.sys.entity.base.BaseField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 类名
 *
 * @ClassName SysUser
 * 简述此类作用
 * @Description 用户信息表
 * 作者
 * @Author xiajun
 * 创建时间
 * @Date 2023/1/17 19:14
 * 版本
 * @Version 1.0
 **/
@ApiModel(value = "用户信息表",description = "")
@TableName("sys_user")
@Data
public class SysUser extends BaseField{
    /** 用户ID */
    @ApiModelProperty(name = "用户ID",notes = "")
    @TableId(type = IdType.AUTO)
    private Integer userId ;
    /** 部门ID */
    @ApiModelProperty(name = "部门ID",notes = "")
    private Integer deptId ;
    /** 登录账号 */
    @ApiModelProperty(name = "登录账号",notes = "")
    private String loginName ;
    /** 用户昵称 */
    @ApiModelProperty(name = "用户昵称",notes = "")
    private String userName ;
    /** 用户类型（00系统用户 01注册用户） */
    @ApiModelProperty(name = "用户类型（00系统用户 01注册用户）",notes = "")
    private String userType ;
    /** 用户邮箱 */
    @ApiModelProperty(name = "用户邮箱",notes = "")
    private String email ;
    /** 手机号码 */
    @ApiModelProperty(name = "手机号码",notes = "")
    private String phoneNumber ;
    /** 用户性别（0男 1女 2未知） */
    @ApiModelProperty(name = "用户性别（0男 1女 2未知）",notes = "")
    private String sex ;
    /** 头像路径 */
    @ApiModelProperty(name = "头像路径",notes = "")
    private String avatar ;
    /** 密码 */
    @ApiModelProperty(name = "密码",notes = "")
    private String password ;
    /** 盐加密 */
    @ApiModelProperty(name = "盐加密",notes = "")
    private String salt ;
}
