package cn.com.cybertech.sdly.file;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("file.upload")
@Data
public class FileUploadProperties {

    private String localRealPath;
    private String urlPrefix;
    private String filenameSuffix;
    //属于哪个业务
    private String biz;


}
