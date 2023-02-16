package com.xzj.file.config;

import io.minio.MinioClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName [类名 : MinioConfiguration ]
 * @Description [简述 : TODO]
 * @Author [作者 : xiajun]
 * @Date [创建时间 : 2023/2/16 21:55]
 * @Version [版本 : 1.0]
 **/
@Configuration
@ConfigurationProperties(prefix = "spring.minio")
@Data
public class MinioConfiguration {
    private String accessKey;

    private String secretKey;

    private String url;

    private String bucketName;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(url)
                .credentials(accessKey, secretKey)
                .build();
    }
}
