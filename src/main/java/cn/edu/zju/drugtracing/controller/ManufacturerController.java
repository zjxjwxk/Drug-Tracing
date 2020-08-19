package cn.edu.zju.drugtracing.controller;

import cn.edu.zju.drugtracing.common.ServerResponse;
import cn.edu.zju.drugtracing.service.ManufacturerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * @author Xinkang Wu
 * @date 2020/8/12 9:26 下午
 */
@Api(tags = "药品生产企业接口")
@RestController
@RequestMapping("/manufacturer")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @ApiOperation("上传药品生产企业信息（根据药品生产企业地址（输入0则为当前函数调用者地址）、药品生产企业名称，上传药品生产企业信息）")
    @PostMapping("/setManufacturer")
    @ResponseBody
    public ServerResponse<String> setManufacturer(@ApiParam("药品生产企业地址（输入0则为当前函数调用者地址）") @RequestParam String manufacturerAddr,
                                                  @ApiParam("药品生产企业名称") @RequestParam String manufacturerName) {
        return manufacturerService.setManufacturer(manufacturerAddr, manufacturerName);
    }

    @ApiOperation("打包药品（根据药品小包编号（3bytes药品ID+3bytes大包ID+3bytes小包ID）、对应的药品大包编号（3bytes药品ID+3bytes大包ID），上传对应关系）")
    @PostMapping("/pack")
    @ResponseBody
    public ServerResponse<String> pack(@ApiParam("药品小包编号（3bytes药品ID+3bytes大包ID+3bytes小包ID）") @RequestParam String packageID,
                               @ApiParam("药品大包编号（3bytes药品ID+3bytes大包ID）") @RequestParam String boxID) {
        return manufacturerService.pack(packageID, boxID);
    }

    @ApiOperation("上传药方（根据药品编号、药品名称、药品原料，上传药方）")
    @PostMapping("/setFormulation")
    @ResponseBody
    public ServerResponse<String> setFormulation(@ApiParam("药品编号（3bytes药品ID）") @RequestParam String drugID,
                                         @ApiParam("药品名称") @RequestParam String drugName,
                                         @ApiParam("药品原料") @RequestParam String material) {
        return manufacturerService.setFormulation(drugID, drugName, material);
    }

    @ApiOperation("上传包装信息（根据药品大包编号、药品生产企业地址、包装时间（输入0则为当前时间）、药品原料编号，上传包装信息）")
    @PostMapping("/setBoxInfo")
    @ResponseBody
    public ServerResponse<String> setBoxInfo(@ApiParam("药品大包编号（3bytes药品ID+3bytes大包ID）") @RequestParam String boxID,
                                     @ApiParam("药品生产企业地址（输入0则为当前函数调用者地址）") @RequestParam String manufacturerAddr,
                                     @ApiParam(value = "包装时间（输入0则为当前时间）", example = "0") @RequestParam Integer time,
                                     @ApiParam("原料编号") @RequestParam String materialID) {
        return manufacturerService.setBoxInfo(boxID, manufacturerAddr, time, materialID);
    }
}
