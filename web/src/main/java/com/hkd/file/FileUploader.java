package com.hkd.file;

import com.hkd.enums.ResultCode;
import com.hkd.exceptions.BusinessException;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class FileUploader {

    private String diskPath;

    private MultipartFile file;

    private String modulePath;

    private PathGenerator generator=new PathGenerator();

    private String filename;

    private String fileSuffix;

    public FileUploader(MultipartFile file,String diskPath,String modulePath){
        if(file==null){
            throw new BusinessException(ResultCode.PARAM_IS_INVALID);
        }
        this.diskPath=diskPath;
        this.modulePath=modulePath;
        this.file=file;

    }

    public void upload(){
        String path=diskPath+modulePath+generator.ymdPath();
        File filePath=new File(path);
        if(!filePath.exists()){
            if(!filePath.mkdirs()){
                throw new BusinessException(ResultCode.UPLOAD_FILE_FAIL,"目录创建失败");
            }
        }
        path+=createFilename()+getFileSuffix();
        OutputStream outputStream=null;
        try {
            outputStream=new FileOutputStream(path);
            IOUtils.write(file.getBytes(),outputStream);
        } catch (IOException e) {
            throw new BusinessException(ResultCode.UPLOAD_FILE_FAIL,e.getMessage());
        }finally {
            IOUtils.closeQuietly(outputStream);
        }
    }

    private String getFileSuffix(){
        fileSuffix= file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        return fileSuffix;
    }

    private String createFilename(){
        filename= UUID.randomUUID().toString().replace("-","").toUpperCase();
        return filename;
    }

    public String getUrlPath(){
        return modulePath+generator.ymdPath()+filename+fileSuffix;
    }
}
