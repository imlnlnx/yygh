package com.ln.yygh.vo.hosp;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Author: Euphratica
 * @Date: 2022/01/17
 */
@Data
public class HospitalSetQueryVo {
    @ApiModelProperty("医院名称")
    private String hosName;

    @ApiModelProperty(value = "医院编号")
    private String hosCode;
}
