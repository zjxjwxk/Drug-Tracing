package cn.edu.zju.drugtracing.controller;

import cn.edu.zju.drugtracing.common.ServerResponse;
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

    @GetMapping("/get")
    @ResponseBody
    public ServerResponse get() {
        return null;
    }

    @PostMapping("/set")
    @ResponseBody
    public ServerResponse set() {
        return null;
    }

    @ApiOperation("上传销售信息（根据药品小包编号、销售时间、销售平台地址、消费者地址、销售价格，上传销售信息）")
    @PostMapping("/setSellInfo")
    @ResponseBody
    public ServerResponse setSellInfo(@ApiParam("药品小包编号") String packageID,
                                      @ApiParam("销售时间") String time,
                                      @ApiParam("销售平台地址") String sellerAddr,
                                      @ApiParam("消费者地址") String consumerAddr,
                                      @ApiParam("销售价格") String price) {
        return null;
    }
}
