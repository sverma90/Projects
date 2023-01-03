package jhu.softwaresleuthers.clueless.models;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Builder
@Data
public class Shortcut {

    // Source Room that the Shortcut is located in
    @NotNull
    Room source;

    // Destination Room that the Shortcut navigates to
    @NotNull
    Room destination;
}
