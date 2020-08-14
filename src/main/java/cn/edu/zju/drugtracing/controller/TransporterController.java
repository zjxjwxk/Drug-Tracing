package cn.edu.zju.drugtracing.controller;

import cn.edu.zju.drugtracing.common.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * @author Xinkang Wu
 * @date 2020/8/12 9:58 下午
 */
@Api(tags = "药品流通企业接口")
@RestController
@RequestMapping("/transporter")
public class TransporterController {

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

    @ApiOperation("上传药品收揽信息（根据药品大包编号、收揽时间、订单编号，上传药品收揽信息）")
    @PostMapping("/pick")
    @ResponseBody
    public ServerResponse pick(@ApiParam("药品大包编号") String boxID,
                               @ApiParam("收揽时间") String time,
                               @ApiParam("订单编号") String orderID) {
        return null;
    }

    @ApiOperation("上传药品送达信息（根据药品大包编号、送达时间、订单编号，上传药品收揽信息）")
    @PostMapping("/drop")
    @ResponseBody
    public ServerResponse drop(@ApiParam("药品大包编号") String boxID,
                               @ApiParam("送达时间") String time,
                               @ApiParam("订单编号") String orderID) {
        return null;
    }
}
