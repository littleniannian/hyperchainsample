package org.example.hyperchainservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.example.hyperchainservice.dto.Result;
import org.example.hyperchainservice.forms.CreateProductForm;
import org.example.hyperchainservice.forms.TransferForm;
import org.example.hyperchainservice.service.IStoneTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Api(tags = "HyperChain智能合约接口")
@RestController
@RequestMapping("trading")
@Slf4j
public class HomeController {

    private final IStoneTradeService stoneTradeService;

    @Autowired
    public HomeController(IStoneTradeService stoneTradeService) {
        this.stoneTradeService = stoneTradeService;
    }

    @ApiOperation(value = "查看用户的产品信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "accountName",example = "中国",required = true)})
    @GetMapping("balance/{accountName}")
    public Result balance(@PathVariable("accountName")
                          @NotEmpty(message = "{product.accountname.notempty}")String accountName){
        try {
            return stoneTradeService.selectBalance(accountName);
        } catch (Exception e) {
            log.error("select balance error detail is {[]}",e.getMessage());
            return new Result(HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }

    @ApiOperation(value = "查看产品交易记录")
    @ApiImplicitParams({@ApiImplicitParam(name = "stoneName",example = "铁矿石",required = true),
                        @ApiImplicitParam(name = "num",example = "10000",required = true)})
    @GetMapping("record/{stoneName}/{num}")
    public Result record(@PathVariable("stoneName")
                         @NotEmpty(message = "{product.stonename.notempty}") String stoneName,
                         @PathVariable("num") Integer num){
        try {
            return stoneTradeService.selectRecord(stoneName,num);
        } catch (Exception e) {
            log.error("select record error detail is {[]}",e.getMessage());
            return new Result(HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }

    @ApiOperation(value = "创建用户")
    @ApiImplicitParams({@ApiImplicitParam(name = "accountName",example = "中国",required = true)})
    @PostMapping("user/{accountName}")
    public Result user(@PathVariable("accountName")
                       @NotEmpty(message = "{product.accountname.notempty}") String accountName){
        try {
            return stoneTradeService.createUser(accountName);
        } catch (Exception e) {
            log.error("create account error detail si {[]}",e.getMessage());
            return new Result(HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }

    @ApiOperation(value = "创建产品")
    @PostMapping("/product")
    public Result product(@RequestBody @Valid CreateProductForm form){
        try {
            return stoneTradeService.createProduct(form.accountName,form.stoneName,form.count);
        } catch (Exception e) {
            log.error("create product error detail is {[]}",e.getMessage());
            return new Result(HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }

    @ApiOperation(value = "转账")
    @PostMapping("/transfer")
    public Result transfer(@RequestBody @Valid TransferForm form){
        try {
            return stoneTradeService.transfer(form.from,form.to,form.stoneName,form.count);
        } catch (Exception e) {
            log.error("transfer error detail is {[]}",e.getMessage());
            return new Result(HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }





}
