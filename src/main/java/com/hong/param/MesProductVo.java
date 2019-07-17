package com.hong.param;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MesProductVo {
    private Integer id;

    private Integer pId;
    
    private String productId;
    
    private Integer productOrderid;

    private Integer productPlanid;

    @NotNull(message = "工艺重量不能为空")
    private Float productTargetweight;

    @NotNull(message = "投料重量不能为空")
    private Float productRealweight;
    
    @NotNull(message = "剩余重量不能为空")
    private Float productLeftweight;

    private Float productBakweight;

    private String productIrontype;

    @NotNull(message = "锭型不能为空")
    private Float productIrontypeweight;

    private String productMaterialname;

    private String productImgid;
    
    private String productLuhao;

    private String productMaterialsource;

    private Integer productStatus;

    private String productRemark;

    private String productOperator;

    private String productOperateTime;

    private String productOperateIp;
    
    private Integer counts=1;
}