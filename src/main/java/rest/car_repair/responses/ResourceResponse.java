package rest.car_repair.responses;

import lombok.Getter;

import java.time.LocalDateTime;

import static java.lang.Boolean.TRUE;

@Getter
public class ResourceResponse extends Response {

    private Object resource;

    public ResourceResponse(LocalDateTime timestamp, String status, Object resource) {
        super(timestamp, TRUE, status);
        this.resource = resource;
    }
}
