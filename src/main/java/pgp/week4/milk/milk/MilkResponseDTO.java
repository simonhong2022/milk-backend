package pgp.week4.milk.milk;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MilkResponseDTO(@JsonProperty String id, @JsonProperty String name,
                              @JsonProperty String type, @JsonProperty int storage) {
}
