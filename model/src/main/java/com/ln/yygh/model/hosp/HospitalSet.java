package com.ln.yygh.model.hosp;

import com.baomidou.mybatisplus.annotation.TableField;
import com.ln.yygh.model.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Author: Euphratica
 * @Date: 2022/01/17
 */
@Data
public class HospitalSet extends BaseEntity {

    private static final long serialVersionUID = 1915232336913960360L;

    @ApiModelProperty("医院名称")
    @TableField("hosname")
    private String hosName;

    @ApiModelProperty(value = "医院编号")
    @TableField("hoscode")
    private String hosCode;

    @ApiModelProperty(value = "api基础路径")
    @TableField("api_url")
    private String apiUrl;

    @ApiModelProperty(value = "签名秘钥")
    @TableField("sign_key")
    private String signKey;

    @ApiModelProperty(value = "联系人姓名")
    @TableField("contacts_name")
    private String contactsName;

    @ApiModelProperty(value = "联系人手机")
    @TableField("contacts_phone")
    private String contactsPhone;

    @ApiModelProperty(value = "状态(1:可使用，0:不可使用)")
    @TableField("status")
    private Byte status;
}
