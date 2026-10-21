package ecommerce.ecommerce_project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

//@Configuration //configuration for aws
//public class S3Config {
//
//    //variables for cloud aws
//    @Value("${aws.access-key}")
//    private String accessKey;
//    @Value("${aws.secret-key}")
//    private String secretKey;
//    @Value("${aws.region}")
//    private String region;
//
//
//    //creating s3 client
//    @Bean
//    public S3Client s3Client(){
//        //creating aws credential object for builder
//        AwsBasicCredentials awsBasicCredentials=AwsBasicCredentials.create(secretKey, accessKey);
//
//        //building s3client
//        return S3Client.builder()
//                .region(Region.of(region))//setting region
//                .credentialsProvider(StaticCredentialsProvider.create(awsBasicCredentials))//passing credential
//                .build();
//    }
//}
