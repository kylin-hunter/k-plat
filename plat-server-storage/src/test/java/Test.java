import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import com.kylinhunter.plat.commons.io.ResourceHelper;

import io.minio.MinioClient;
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

        String name = "admin";
        String pass = "admin123";
        String bucket = "bijian";
        final MinioClient minioClient =
                MinioClient.builder().endpoint("http://10.233.28.42:8200").credentials(name, pass).build();


        minioClient.listBuckets().forEach(e -> {
            System.out.println(e.name());
        });

        File file = ResourceHelper.getFileInClassPath("test.docx");

        FileInputStream inputStream = new FileInputStream(file);
        minioClient.putObject(PutObjectArgs.builder()
                .bucket(bucket)
                .object("test.docx")
                .stream(inputStream, file.length(), -1)
                .build());

    }
}
