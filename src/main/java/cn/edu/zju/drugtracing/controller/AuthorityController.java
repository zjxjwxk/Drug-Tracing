package cn.edu.zju.drugtracing.controller;

import cn.edu.zju.drugtracing.common.ServerResponse;
import cn.edu.zju.drugtracing.service.AuthorityService;
import cn.edu.zju.drugtracing.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Xinkang Wu
 * @date 2020/8/12 10:30 下午
 */
@Api(tags = "药品监管部门接口")
@RestController
@RequestMapping("/authority")
public class AuthorityController {

    private final AuthorityService authorityService;

    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @ApiOperation("上传监管部门地址（需管理员权限）")
    @PostMapping("/setAuthority")
    @ResponseBody
    public ServerResponse<String> setAuthority(@ApiParam("药品监管部门地址") @RequestParam String authorityAddr) {
        return authorityService.setAuthority(authorityAddr);
    }

    @ApiOperation("药品溯源（根据药品小包编号（3bytes药品ID+3bytes大包ID+3bytes小包ID），获得药品溯源信息）")
    @GetMapping("/trace")
    @ResponseBody
    public ServerResponse<TraceVO> trace(@ApiParam("药品小包编号（3bytes药品ID+3bytes大包ID+3bytes小包ID）") @RequestParam String packageID) {
        return authorityService.trace(packageID);
    }

    @ApiOperation("查询药品生产企业信息列表")
    @GetMapping("/getManufacturers")
    @ResponseBody
    public ServerResponse<List<ManufacturerVO>> getManufacturers() {
        return authorityService.getManufacturers();
    }

    @ApiOperation("查询药品信息列表")
    @GetMapping("/getFormulations")
    @ResponseBody
    public ServerResponse<List<FormulationVO>> getFormulations() {
        return authorityService.getFormulations();
    }

    @ApiOperation("查询药品流通企业信息列表")
    @GetMapping("/getTransporters")
    @ResponseBody
    public ServerResponse<List<TransporterVO>> getTransporters() {
        return authorityService.getTransporters();
    }

    @ApiOperation("查询药品销售平台信息列表")
    @GetMapping("/getSellers")
    @ResponseBody
    public ServerResponse<List<SellerVO>> getSellers() {
        return authorityService.getSellers();
    }

    @ApiOperation("查询药品包装信息（根据药品大包编号（3bytes药品ID+3bytes大包ID），获取其中的小包编号列表）")
    @GetMapping("/getPackInfo")
    @ResponseBody
    public ServerResponse<List<String>> getPackInfo(@ApiParam("药品大包编号（3bytes药品ID+3bytes大包ID）") String boxID) {
        return authorityService.getPackInfo(boxID);
    }

    @ApiOperation("查询用户反馈列表（根据药品编号，获取用户反馈列表）")
    @GetMapping("/getFeedBacks")
    @ResponseBody
    public ServerResponse<List<FeedBackVO>> getFeedBacks(String drugID) {
        return authorityService.getFeedBacks(drugID);
    }
}
