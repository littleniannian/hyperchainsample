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
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("转账表单")
public class TransferForm {
    @ApiModelProperty("转账人账户:中国")
    @NotEmpty(message = "{transfer.from.notempty}")
    public String from;
    @ApiModelProperty("收款人账户:韩国")
    @NotEmpty(message = "{transfer.to.notempty}")
    public String to;
    @ApiModelProperty("产品名称")
    @NotEmpty(message = "{transfer.stonename.notempty}")
    public String stoneName;
    @ApiModelProperty("数量：10000")
    @Min(message = "{product.count.range}",value = 1)
    @Max(message = "{product.count.range}",value = 200000)
    public Integer count;
}
