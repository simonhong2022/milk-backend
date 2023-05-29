package pgp.week4.milk.milk;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MilkResponseDTO(@JsonProperty("id") String id, @JsonProperty("name") String name,
                              @JsonProperty("type") String type, @JsonProperty("storage") int storage) {
}
