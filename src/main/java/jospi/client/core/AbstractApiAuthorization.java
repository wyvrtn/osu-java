package jospi.client.core;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jospi.client.authorization.StatefulHttpServiceProvider;
import jospi.client.request.NetIOUtilities;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter(AccessLevel.PROTECTED)
@ToString(includeFieldNames = true)
public abstract class AbstractApiAuthorization implements NetIOUtilities, Serializable {
    private static final long serialVersionUID = 1111L;

    protected static final long LENIENCY = 30L;

    /**
    * Holds the authorization payload of an instance of an inheriting class.
    */
    private final Map<String, String> authorizationBody = new ConcurrentHashMap<>();

    /**
    * The access token held by an instance of an inheriting class.
    */
    private String accessToken = "";

    /**
    * The expiration date of the access token.
    */
    private OffsetDateTime expirationDate = OffsetDateTime.MIN;

    /**
    * Boolean that determines whether an instance of a subclass is
    * performing an authorization request the first time, or is only
    * refreshing its access token.
    */
    private boolean status;

    /**
    * Requests an access token for an instance of a subclass, for the first time.
    *
    * @param svc Instance of an inheriting subclass of {@link StatefulHttpServiceProvider StatefulHttpServiceProvider}
    */
    protected abstract void authorizationFlow(StatefulHttpServiceProvider svc);

    /**
    * Refreshes the access token of an instance of a subclass.
    *
    * @param svc Instance of an inheriting subclass of {@link StatefulHttpServiceProvider StatefulHttpServiceProvider}
    */
    protected abstract void refreshAccessToken(StatefulHttpServiceProvider svc);
}
