//package az.edadi.back.config;
//
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.regions.Regions;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3ClientBuilder;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//
//@Configuration
//@Profile("prod")
//public class S3Bean {
//    @Value("${jsa.aws.access-key-id}")
//    private String awsId;
//
//    @Value("${jsa.aws.secret-access-key}")
//    private String awsKey;
//
//    @Value("${jsa.s3.region}")
//    private String region;
//
//    @Bean
//    public AmazonS3 s3client() {
//
//        BasicAWSCredentials awsCreds = new BasicAWSCredentials(awsId, awsKey);
//        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
//                .withRegion(Regions.fromName(region))
//                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
//                .build();
//
//        return s3Client;
//    }
//}
