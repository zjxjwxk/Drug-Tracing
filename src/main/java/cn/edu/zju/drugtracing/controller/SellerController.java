package cn.edu.zju.drugtracing.controller;

import cn.edu.zju.drugtracing.common.ServerResponse;
import cn.edu.zju.drugtracing.service.SellerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * @author Xinkang Wu
 * @date 2020/8/12 10:17 下午
 */
@Api(tags = "药品销售平台接口")
@RestController
@RequestMapping("/seller")
public class SellerController {

    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

//    @GetMapping("/get")
//    @ResponseBody
//    public ServerResponse get() {
//        return null;
//    }

    @ApiOperation("上传药品销售平台信息（根据药品销售平台地址（输入0则为当前函数调用者地址）、" +
            "药品销售平台名称、药品销售平台类型（0-医院；1-药店；2-电商），上传药品销售平台信息）")
    @PostMapping("/setSeller")
    @ResponseBody
    public ServerResponse<String> setSeller(@ApiParam("药品销售平台地址") @RequestParam String sellerAddr,
                                            @ApiParam("药品销售平台名称") @RequestParam String sellerName,
                                            @ApiParam(value = "药品销售平台类型（0-医院；1-药店；2-电商）", example = "0") @RequestParam Integer sellerType) {
        return sellerService.setSeller(sellerAddr, sellerName, sellerType);
    }

    @ApiOperation("上传销售信息（根据药品小包编号、销售时间（输入0则为当前时间）、销售平台地址、消费者地址、销售价格，上传销售信息）")
    @PostMapping("/setSellInfo")
    @ResponseBody
    public ServerResponse<String> setSellInfo(@ApiParam("药品小包编号（3bytes药品ID+3bytes大包ID+3bytes小包ID）") @RequestParam String packageID,
                                      @ApiParam(value = "销售时间（输入0则为当前时间）", example = "0") @RequestParam Integer time,
                                      @ApiParam("销售平台地址") @RequestParam String sellerAddr,
                                      @ApiParam("消费者地址") @RequestParam String consumerAddr,
                                      @ApiParam(value = "销售价格", example = "20") @RequestParam Integer price) {
        return sellerService.setSellInfo(packageID, time, sellerAddr, consumerAddr, price);
    }
}
