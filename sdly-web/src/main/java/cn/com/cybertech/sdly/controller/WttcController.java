package cn.com.cybertech.sdly.controller;


import cn.com.cybertech.sdly.enums.ResultCode;
import cn.com.cybertech.sdly.file.FileUploader;
import cn.com.cybertech.sdly.model.dto.DriverVehicleInfo;
import cn.com.cybertech.sdly.model.po.TdWttc;
import cn.com.cybertech.sdly.result.PlatformResult;
import cn.com.cybertech.sdly.service.WttcService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@Api("违停拖车")
public class WttcController {

    @Autowired
    private WttcService wttcService;

    @Value("${file.wttc.diskPath}")
    private String diskPath;

    @Value("${file.wttc.modulePath}")
    private String modulePath;

    @PostMapping("/wttc")
    //@PreAuthorize("(hasAuthority('ROLE_TEST'))")
    public PlatformResult<Integer> addWttc(@RequestBody TdWttc wttc){
        int ei=wttcService.insertWttc(wttc);
        if (ei > 0) {
            return PlatformResult.success(wttc.getId());
        }
        return PlatformResult.failure(ResultCode.ADD_RECORD_FAIL);
    }

    @PostMapping("/wttc/pic")
    public PlatformResult<String> wttcPicUpload(@RequestParam MultipartFile file){
        FileUploader fileUploader=new FileUploader(file, diskPath,modulePath);
        fileUploader.upload();
        return PlatformResult.success(fileUploader.getUrlPath());
    }

    @GetMapping("/wttc/driverVehicleInfo")
    public PlatformResult<DriverVehicleInfo> getDriverVehicleInfo(String mjjh){
        DriverVehicleInfo driverVehicleInfo=new DriverVehicleInfo();
        driverVehicleInfo.setXm("test");
        driverVehicleInfo.setCllx("test");
        driverVehicleInfo.setFzjg("test");
        driverVehicleInfo.setZjhm("xxxxxx");
        driverVehicleInfo.setHpzl("test");
        driverVehicleInfo.setHphm("test");
        return PlatformResult.success(driverVehicleInfo);
    }


}
