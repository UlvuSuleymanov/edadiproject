package az.edadi.back.constants;

public enum Provider {
    NATIVE("NATIVE"),
    GOOGLE("GOOGLE");
    private final String provider;

    Provider(String provider) {
        this.provider = provider;
    }

    public String getProvider() {
        return provider;
    }
}
