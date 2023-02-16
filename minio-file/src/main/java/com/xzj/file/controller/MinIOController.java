package com.xzj.file.controller;

import com.xzj.basic.base.dto.AjaxResult;
import com.xzj.file.domain.FilePathRequest;
import com.xzj.file.tools.MinIOTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName [类名 : MinIOController ]
 * @Description [简述 : TODO]
 * @Author [作者 : xiajun]
 * @Date [创建时间 : 2023/2/16 22:02]
 * @Version [版本 : 1.0]
 **/
@RestController
@Slf4j
public class MinIOController {

    @Resource
    private MinIOTool minIOTool;

    /**
     * 文件上传
     * @param request
     * @param file
     * @return
     */
    @PostMapping("/uploadFile")
    public AjaxResult uploadFile(HttpServletRequest request, MultipartFile file){
        String url;
        try {
            minIOTool.uploadFile(file,file.getOriginalFilename(), file.getContentType());
            url = minIOTool.getPreSignedObjectUrl(file.getOriginalFilename());
        } catch (Exception e) {
            return AjaxResult.failResult().putMsg(e.getMessage());
        }
        return AjaxResult.successResult().putData(url);
    }

    @PostMapping("/filePath")
    public AjaxResult filePath(@RequestBody FilePathRequest filePathRequest){
        String url;
        try {
            url = minIOTool.getPreSignedObjectUrl(filePathRequest.getFileName(),filePathRequest.getExpireTime());
        } catch (Exception e) {
            log.error("发生异常",e);
            return AjaxResult.failResult().putStatus(400).putMsg(e.getMessage());
        }
        return AjaxResult.successResult().putData(url);
    }


}
