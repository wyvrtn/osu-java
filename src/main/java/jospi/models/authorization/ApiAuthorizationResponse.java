package jospi.models.authorization;

public interface ApiAuthorizationResponse {
    public void validation();

    public default boolean anyNull(Object... args) {
        for (Object obj : args) if (obj==null) return false;
        return true;
    }
}
