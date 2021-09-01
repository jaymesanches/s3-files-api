package br.com.s3files.api.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "s3files")
public class S3FilesProperty {
	
	private final S3 s3 = new S3();
	
	public S3 getS3() {
		return s3;
	}

	public static class S3 {
		
		public String accessKey;
		public String secretKey;
		private String bucket = "s3-files-testes";

		public String getAccessKey() {
			return accessKey;
		}

		public void setAccessKey(String accessKey) {
			this.accessKey = accessKey;
		}

		public String getSecretKey() {
			return secretKey;
		}

		public void setSecretKey(String secretKey) {
			this.secretKey = secretKey;
		}

		public String getBucket() {
			return bucket;
		}

		public void setBucket(String bucket) {
			this.bucket = bucket;
		}

	}
}
