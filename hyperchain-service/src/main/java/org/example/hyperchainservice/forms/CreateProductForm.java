package org.example.hyperchainservice.forms;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "创建产品请求")
public class CreateProductForm {
    @ApiModelProperty("账号名:中国")
    @NotEmpty(message = "{product.accountname.notempty}")
    public String accountName;
    @ApiModelProperty("产品名:铁矿石")
    @NotEmpty(message = "{product.stonename.notempty}")
    public String stoneName;
    @ApiModelProperty("数量:10000")
    @Min(value = 1,message = "{product.count.range}")
    @Max(value = 200000,message = "{product.count.range}")
    public Integer count;
}
