import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test {

    // 读取配置文件参数
    static final String endpoint = "https://oss-cn-beijing.aliyuncs.com";
    static final String bucketName = "web-aaaaaa";
    static final String region = "cn-beijing";

    public static void main(String[] args) {
        try {
            test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("https://web-aaaaaa.oss-cn-beijing.aliyuncs.com/2025/06/52a8337b-e876-4c5c-a519-c9a1e29c639b.png");
        list.add("https://web-aaaaaa.oss-cn-beijing.aliyuncs.com/2025/06/7718a967-fa71-4f18-af07-a19d91497c86.png");
        list.add("https://web-aaaaaa.oss-cn-beijing.aliyuncs.com/2025/06/9df5b15b-0ce7-474e-b399-333e5d4ef2e8.png");
        delete(list);
    }

    public static void delete(List<String> keys) throws Exception {

        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory
                .newEnvironmentVariableCredentialsProvider();

        // 填写文件完整路径。文件完整路径中不能包含Bucket名称。
        // https://web-aaaaaa.oss-cn-beijing.aliyuncs.com/2025/06/664acf7e-3f86-4b54-8d71-77f9e299854b.png
        String url = "https://web-aaaaaa.oss-cn-beijing.aliyuncs.com";
        // 遍历keys，提取文件路径
        List<String> newList = keys.stream()
                .filter(path -> path.startsWith(url))
                .map(path -> path.substring(url.length()+1))
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

            DeleteObjectsResult deleteObjectsResult = ossClient
                    .deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(newList)
                            .withEncodingType("url"));
            List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
            for (String obj : deletedObjects) {
                String deleteObj = URLDecoder.decode(obj, "UTF-8");
                System.out.println(deleteObj);
            }

            // 判断是否删除成功
            // newList.forEach(deleteUrl -> {
            // // 检查文件是否存在于指定的 OSS Bucket 中
            // boolean exists = ossClient.doesObjectExist(bucketName, deleteUrl);
            // System.out.println("文件 " + deleteUrl + " 是否存在: " + exists);
            // });

        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

}
