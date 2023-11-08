import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import io.github.kylinhunter.commons.io.ResourceHelper;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-27 00:22
 **/
public class Test {

    public static void main(String[] args)
            throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException,
            NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {

        String name = "local_test";
        String pass = "local_test";
        String bucket = "bijian";
        String endpoint = "http://127.0.0.1:9000";
        //         name = "admin";
        //         pass = "admin123";
        final MinioClient minioClient =
                MinioClient.builder().endpoint(endpoint).credentials(name, pass).build();

        minioClient.listBuckets().forEach(e -> {
            System.out.println(e.name());
        });

        File file = ResourceHelper.getFileInClassPath("test.docx");

        FileInputStream inputStream = new FileInputStream(file);
        final ObjectWriteResponse objectWriteResponse = minioClient.putObject(PutObjectArgs.builder()
                .bucket(bucket)
                .object("1/1/tes1t   3.docx")
                .stream(inputStream, file.length(), -1)
                .build());
        System.out.println(objectWriteResponse);
        System.out.println(objectWriteResponse.object());
        System.out.println(objectWriteResponse.versionId());
        System.out.println(objectWriteResponse.etag());
        System.out.println(objectWriteResponse.bucket());
        System.out.println(objectWriteResponse.region());


    }
}
