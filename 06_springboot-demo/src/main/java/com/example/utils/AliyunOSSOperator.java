package com.example.utils;

import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.ByteArrayInputStream;
import java.net.URLDecoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 阿里云OSS上传文件，新员工的图片上传
 */
@Slf4j
@Component
public class AliyunOSSOperator {

    // 方式一: 通过@Value注解一个属性一个属性的注入
    // @Value("${aliyun.oss.endpoint}")
    // private String endpoint;
    // @Value("${aliyun.oss.bucketName}")
    // private String bucketName;
    // @Value("${aliyun.oss.region}")
    // private String region;

    @Autowired
    private AliyunOSSProperties aliyunOSSProperties;

    /**
     * 上传文件
     * 
     * @param content
     * @param originalFilename
     * @return
     * @throws Exception
     */
    public String upload(byte[] content, String originalFilename) throws Exception {
        // 读取配置文件参数
        String endpoint = aliyunOSSProperties.getEndpoint();
        String bucketName = aliyunOSSProperties.getBucketName();
        String region = aliyunOSSProperties.getRegion();

        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory
                .newEnvironmentVariableCredentialsProvider();

        // 填写Object完整路径，例如2024/06/1.png。Object完整路径中不能包含Bucket名称。
        // 获取当前系统日期的字符串,格式为 yyyy/MM
        String dir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM"));
        // 生成一个新的不重复的文件名
        String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
        String objectName = dir + "/" + newFileName;

        // 创建OSSClient实例。
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        OSS ossClient = OSSClientBuilder.create()
                .endpoint(endpoint)
                .credentialsProvider(credentialsProvider)
                .clientConfiguration(clientBuilderConfiguration)
                .region(region)
                .build();

        try {
            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content));
        } finally {
            ossClient.shutdown();
        }

        /**
         * 返回值为OSS文件访问路径https://web-aaaaaa.oss-cn-beijing.aliyuncs.com/2025/06/63eccdf0-47ae-4495-b3e2-439156235dcb.png
         * 1 https://
         * 2 web-aaaaaa
         * 3 .
         * 4 oss-cn-beijing.aliyuncs.com
         * 5 /
         * 6 exampleobject.txt
         */
        return endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + objectName;
    }

    /**
     * 删除文件
     * 
     * @param path
     * @throws Exception
     */
    public void delete(List<String> keys) throws Exception {

        // 读取配置文件参数
        String endpoint = aliyunOSSProperties.getEndpoint();
        String bucketName = aliyunOSSProperties.getBucketName();
        String region = aliyunOSSProperties.getRegion();

        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory
                .newEnvironmentVariableCredentialsProvider();

        // 填写文件完整路径。文件完整路径中不能包含Bucket名称。
        // https://web-aaaaaa.oss-cn-beijing.aliyuncs.com/2025/06/664acf7e-3f86-4b54-8d71-77f9e299854b.png
        String url = "https://web-aaaaaa.oss-cn-beijing.aliyuncs.com";
        // 遍历keys，提取文件路径
        List<String> newList = keys.stream()
                .filter(path -> path.startsWith(url))
                .map(path -> path.substring(url.length() + 1))
                .collect(Collectors.toList());

        log.info("要删除的文件目录：{}", newList);
        // 创建OSSClient实例。
        // 当OSSClient实例不再使用时，调用shutdown方法以释放资源。
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        OSS ossClient = OSSClientBuilder.create()
                .endpoint(endpoint)
                .credentialsProvider(credentialsProvider)
                .clientConfiguration(clientBuilderConfiguration)
                .region(region)
                .build();
        try {
            // 删除文件或目录。如果要删除目录，目录必须为空。
            // ossClient.deleteObject(bucketName, objectName);

            // 删除文件。
            // 填写需要删除的多个文件完整路径。文件完整路径中不能包含Bucket名称。
            // List<String> keys = new ArrayList<String>();
            // keys.add("exampleobjecta.txt");
            // keys.add("testfolder/sampleobject.txt");
            // keys.add("exampleobjectb.txt");

            DeleteObjectsResult deleteObjectsResult = ossClient
                    .deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(newList)
                            .withEncodingType("url"));
            List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
            for (String obj : deletedObjects) {
                String deleteObj = URLDecoder.decode(obj, "UTF-8");
                boolean exists = ossClient.doesObjectExist(bucketName, deleteObj);
                System.out.println("文件：" + deleteObj + " 是否存在: " + exists);

            }

        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

}
