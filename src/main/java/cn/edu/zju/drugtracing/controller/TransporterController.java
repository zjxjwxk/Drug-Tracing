package cn.edu.zju.drugtracing.controller;

import cn.edu.zju.drugtracing.common.ServerResponse;
import cn.edu.zju.drugtracing.service.TransporterService;
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

    private final TransporterService transporterService;

    public TransporterController(TransporterService transporterService) {
        this.transporterService = transporterService;
    }

    @ApiOperation("上传药品流通企业信息（根据药品流通企业名称，上传药品流通企业信息）")
    @PostMapping("/setTransporter")
    @ResponseBody
    public ServerResponse<String> setTransporter(@ApiParam("药品流通企业名称") @RequestParam String transporterName) {
        return transporterService.setTransporter(transporterName);
    }

    @ApiOperation("上传药品收揽信息（根据药品大包编号、收揽时间（输入0则为当前时间），上传药品收揽信息）")
    @PostMapping("/pick")
    @ResponseBody
    public ServerResponse<String> pick(@ApiParam("药品大包编号（3bytes药品ID+3bytes大包ID）") @RequestParam String boxID,
                               @ApiParam(value = "收揽时间（输入0则为当前时间）", example = "0") @RequestParam Integer time) {
        return transporterService.pick(boxID, time);
    }

    @ApiOperation("上传药品送达信息（根据药品大包编号、送达时间（输入0则为当前时间）、药品销售平台地址，上传药品收揽信息）")
    @PostMapping("/drop")
    @ResponseBody
    public ServerResponse<String> drop(@ApiParam("药品大包编号（3bytes药品ID+3bytes大包ID）") @RequestParam String boxID,
                                       @ApiParam(value = "送达时间（输入0则为当前时间）", example = "0") @RequestParam Integer time,
                                       @ApiParam("药品销售平台地址") @RequestParam String sellerAddr) {
        return transporterService.drop(boxID, time, sellerAddr);
    }
}
