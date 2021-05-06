package az.edadi.back.security.jwt;


 public class JwtBean {

    private String title;
    private Long lifeTime;
    private String secretKey;

      public String getTitle() { return title; }

      public Long getLifeTime() {
        return lifeTime;
    }

      public String getSecretKey() {
        return secretKey;
    }

      public void setTitle(String title) { this.title = title; }

      public void setLifeTime(Long lifeTime) {
          this.lifeTime = lifeTime;
      }

      public void setSecretKey(String secretKey) {
          this.secretKey = secretKey;
      }
  }
