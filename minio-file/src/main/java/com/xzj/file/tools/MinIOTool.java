package com.xzj.file.tools;

import com.xzj.file.config.MinioConfiguration;
import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.DeleteObject;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
/**
 * @ClassName [类名 : MinIOTool ]
 * @Description [简述 : TODO]
 * @Author [作者 : xiajun]
 * @Date [创建时间 : 2023/2/16 21:56]
 * @Version [版本 : 1.0]
 **/
@Slf4j
@Component
public class MinIOTool {

    @Resource
    private MinioClient minioClient;
    
    @Resource
    private MinioConfiguration configuration;

    private final String SEPARATOR = "/";


    /**
     * 获取上传文件前缀路径
     * @return
     */
    public String getBasisUrl() {
        return configuration.getUrl() + SEPARATOR + configuration.getBucketName() + SEPARATOR;
    }

    /******************************  Operate Bucket Start  ******************************/

    /**
     * 启动SpringBoot容器的时候初始化Bucket
     * 如果没有Bucket则创建
     * @throws Exception
     */
    private void createBucket() throws Exception {
        if (!bucketExists()) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(configuration.getBucketName()).build());
        }
    }

    /**
     *  判断Bucket是否存在，true：存在，false：不存在
     * @return
     * @throws Exception
     */
    public boolean bucketExists() throws Exception {
        return minioClient.bucketExists(BucketExistsArgs.builder().bucket(configuration.getBucketName()).build());
    }


    /**
     * 获得Bucket的策略
     * @return
     * @throws Exception
     */
    public String getBucketPolicy() throws Exception {
        return minioClient
                .getBucketPolicy(
                        GetBucketPolicyArgs
                                .builder()
                                .bucket(configuration.getBucketName())
                                .build()
                );
    }


    /**
     * 获得所有Bucket列表
     * @return
     * @throws Exception
     */
    public List<Bucket> getAllBuckets() throws Exception {
        return minioClient.listBuckets();
    }

    /**
     * 根据bucketName获取其相关信息
     * @return
     * @throws Exception
     */
    public Optional<Bucket> getBucket() throws Exception {
        return getAllBuckets().stream().filter(b -> b.name().equals(configuration.getBucketName())).findFirst();
    }

    /**
     * 根据bucketName删除Bucket，true：删除成功； false：删除失败，文件或已不存在
     * @throws Exception
     */
    public void removeBucket() throws Exception {
        minioClient.removeBucket(RemoveBucketArgs.builder().bucket(configuration.getBucketName()).build());
    }

    /******************************  Operate Bucket End  ******************************/


    /******************************  Operate Files Start  ******************************/

    /**
     * 判断文件是否存在
     * @param objectName 文件名
     * @return
     */
    public boolean isObjectExist(String objectName) {
        boolean exist = true;
        try {
            createBucket();
            minioClient.statObject(StatObjectArgs.builder().bucket(configuration.getBucketName()).object(objectName).build());
        } catch (Exception e) {
            log.error("[Minio工具类]>>>> 判断文件是否存在, 异常：", e);
            exist = false;
        }
        return exist;
    }

    /**
     * 判断文件夹是否存在
     * @param objectName 文件夹名称
     * @return
     */
    public boolean isFolderExist(String objectName) {
        boolean exist = false;
        try {
            createBucket();
            Iterable<Result<Item>> results = minioClient.listObjects(
                    ListObjectsArgs.builder().bucket(configuration.getBucketName()).prefix(objectName).recursive(false).build());
            for (Result<Item> result : results) {
                Item item = result.get();
                if (item.isDir() && objectName.equals(item.objectName())) {
                    exist = true;
                }
            }
        } catch (Exception e) {
            log.error("[Minio工具类]>>>> 判断文件夹是否存在，异常：", e);
            exist = false;
        }
        return exist;
    }

    /**
     * 根据文件前置查询文件
     * @param prefix 前缀
     * @param recursive 是否使用递归查询
     * @return MinioItem 列表
     * @throws Exception
     */
    public List<Item> getAllObjectsByPrefix(String prefix,boolean recursive) throws Exception {
        createBucket();
        List<Item> list = new ArrayList<>();
        Iterable<Result<Item>> objectsIterator = minioClient.listObjects(
                ListObjectsArgs.builder().bucket(configuration.getBucketName()).prefix(prefix).recursive(recursive).build());
        if (objectsIterator != null) {
            for (Result<Item> o : objectsIterator) {
                Item item = o.get();
                list.add(item);
            }
        }
        return list;
    }

    /**
     * 获取文件流
     * @param objectName 文件名
     * @return 二进制流
     */
    public InputStream getObject(String objectName) throws Exception {
        createBucket();
        return minioClient.getObject(GetObjectArgs.builder().bucket(configuration.getBucketName()).object(objectName).build());
    }

    /**
     * 断点下载
     * @param objectName 文件名称
     * @param offset 起始字节的位置
     * @param length 要读取的长度
     * @return 二进制流
     */
    public InputStream getObject(String objectName, long offset, long length)throws Exception {
        createBucket();
        return minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(configuration.getBucketName())
                        .object(objectName)
                        .offset(offset)
                        .length(length)
                        .build());
    }

    /**
     * 获取路径下文件列表
     * @param prefix 文件名称
     * @param recursive 是否递归查找，false：模拟文件夹结构查找
     * @return 二进制流
     */
    public Iterable<Result<Item>> listObjects(String prefix,boolean recursive) throws Exception {
        createBucket();
        return minioClient.listObjects(
                ListObjectsArgs.builder()
                        .bucket(configuration.getBucketName())
                        .prefix(prefix)
                        .recursive(recursive)
                        .build());
    }

    /**
     * 使用MultipartFile进行文件上传
     * @param file 文件名
     * @param objectName 对象名
     * @param contentType 类型
     * @return
     * @throws Exception
     */
    public ObjectWriteResponse uploadFile(MultipartFile file, String objectName, String contentType) throws Exception {
        createBucket();
        InputStream inputStream = file.getInputStream();
        return minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(configuration.getBucketName())
                        .object(objectName)
                        .contentType(contentType)
                        .stream(inputStream, inputStream.available(), -1)
                        .build());
    }

    /**
     * 上传本地文件
     * @param objectName 对象名称
     * @param fileName 本地文件路径
     */
    public ObjectWriteResponse uploadFile(String objectName,String fileName) throws Exception {
        createBucket();
        return minioClient.uploadObject(
                UploadObjectArgs.builder()
                        .bucket(configuration.getBucketName())
                        .object(objectName)
                        .filename(fileName)
                        .build());
    }

    /**
     * 通过流上传文件
     *
     * @param objectName 文件对象
     * @param inputStream 文件流
     */
    public ObjectWriteResponse uploadFile(String objectName, InputStream inputStream) throws Exception {
        createBucket();
        return minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(configuration.getBucketName())
                        .object(objectName)
                        .stream(inputStream, inputStream.available(), -1)
                        .build());
    }

    /**
     * 创建文件夹或目录
     * @param objectName 目录路径
     */
    public ObjectWriteResponse createDir(String objectName) throws Exception {
        createBucket();
        return minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(configuration.getBucketName())
                        .object(objectName)
                        .stream(new ByteArrayInputStream(new byte[]{}), 0, -1)
                        .build());
    }

    /**
     * 获取文件信息, 如果抛出异常则说明文件不存在
     *
     * @param objectName 文件名称
     */
    public String getFileStatusInfo(String objectName) throws Exception {
        createBucket();
        return minioClient.statObject(
                StatObjectArgs.builder()
                        .bucket(configuration.getBucketName())
                        .object(objectName)
                        .build()).toString();
    }

    /**
     * 拷贝文件
     *
     * @param objectName 文件名
     * @param srcBucketName 目标存储桶
     * @param srcObjectName 目标文件名
     */
    public ObjectWriteResponse copyFile(String objectName,
                                               String srcBucketName, String srcObjectName) throws Exception {
        createBucket();
        return minioClient.copyObject(
                CopyObjectArgs.builder()
                        .source(CopySource.builder().bucket(configuration.getBucketName()).object(objectName).build())
                        .bucket(srcBucketName)
                        .object(srcObjectName)
                        .build());
    }

    /**
     * 删除文件
     * @param objectName 文件名称
     */
    public void removeFile(String objectName) throws Exception {
        createBucket();
        minioClient.removeObject(
                RemoveObjectArgs.builder()
                        .bucket(configuration.getBucketName())
                        .object(objectName)
                        .build());
    }

    /**
     * 批量删除文件
     * @param keys 需要删除的文件列表
     * @return
     */
    public void removeFiles(List<String> keys) {
        List<DeleteObject> objects = new LinkedList<>();
        keys.forEach(s -> {
            objects.add(new DeleteObject(s));
            try {
                createBucket();
                removeFile(s);
            } catch (Exception e) {
                log.error("[Minio工具类]>>>> 批量删除文件，异常：", e);
            }
        });
    }

    /**
     * 获取文件外链
     * @param objectName 文件名
     * @param expires 过期时间 <=7 秒 （外链有效时间（单位：秒））
     * @return url
     * @throws Exception
     */
    public String getPreSignedObjectUrl(String objectName, Integer expires) throws Exception {
        createBucket();
        GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
                .expiry(expires)
                .bucket(configuration.getBucketName())
                .object(objectName)
                .method(Method.GET)
                .build();
        return minioClient.getPresignedObjectUrl(args);
    }

    /**
     * 获得文件外链
     * @param objectName
     * @return url
     * @throws Exception
     */
    public String getPreSignedObjectUrl(String objectName) throws Exception {
        createBucket();
        GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
                .bucket(configuration.getBucketName())
                .object(objectName)
                .method(Method.GET).build();
        return minioClient.getPresignedObjectUrl(args);
    }

    /**
     * 将URLDecoder编码转成UTF8
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public String getUtf8ByURLDecoder(String str) throws UnsupportedEncodingException {
        String url = str.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
        return URLDecoder.decode(url, "UTF-8");
    }

    /******************************  Operate Files End  ******************************/
}
