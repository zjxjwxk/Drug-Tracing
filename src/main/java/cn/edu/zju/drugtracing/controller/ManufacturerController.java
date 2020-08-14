package cn.edu.zju.drugtracing.controller;

import cn.edu.zju.drugtracing.common.ServerResponse;
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

    @ApiOperation("打包药品（根据药品小包编号、对应的药品大包编号，打包药品）")
    @PostMapping("/pack")
    @ResponseBody
    public ServerResponse pack(@ApiParam("药品小包编号") String packageID,
                               @ApiParam("药品大包编号") String boxID) {
        return null;
    }

    @ApiOperation("上传药方（根据药方编号、药方名称、原料，上传药方）")
    @PostMapping("/setFormulation")
    @ResponseBody
    public ServerResponse setFormulation(@ApiParam("药方编号") String drugID,
                                         @ApiParam("药方名称") String drugName,
                                         @ApiParam("原料") String[] material) {
        return null;
    }

    @ApiOperation("上传包装信息（根据药品大包编号、药品生产企业地址、包装时间、原料编号，上传包装信息）")
    @PostMapping("/setBoxInfo")
    @ResponseBody
    public ServerResponse setBoxInfo(@ApiParam("药品大包编号") String boxID,
                                     @ApiParam("药品生产企业地址") String manufacturerAddr,
                                     @ApiParam("包装时间") String time,
                                     @ApiParam("原料编号") String materialID) {
        return null;
    }
}
