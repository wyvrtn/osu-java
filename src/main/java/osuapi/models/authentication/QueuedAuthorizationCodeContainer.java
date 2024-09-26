package osuapi.models.authentication;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import osuapi.client.authorization.AuthorizationCodeKey;;

@NoArgsConstructor
public class QueuedAuthorizationCodeContainer {
    private @Getter AuthorizationCodeKey key;
    private @Getter ContainerStatus status;

    private @Getter @Setter String code;
    private @Getter @Setter String state = "";

    public QueuedAuthorizationCodeContainer(AuthorizationCodeKey key) {
        this.key = key;
    }

    public ContainerStatus awaitResponse() {
        return awaitResponse(1000, 10);
    }

    public synchronized ContainerStatus awaitResponse(long interval, long timeout) {
        if (timeout<=0) throw new IllegalArgumentException("timeout must be a natural number");
        
        long timer = 0L;
        while (timer<timeout) {
            if (code!=null) {
                status = ContainerStatus.SUCCESS;
                return status;
            }
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
            timer++;
        }
        status = ContainerStatus.FAILED;
        return status;
    }

    public enum ContainerStatus {
        SUCCESS,
        FAILED;
    }
}
