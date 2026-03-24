package com.saas.employee.dto.request;

import com.saas.employee.enums.Listening;
import com.saas.employee.enums.Writing;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageRequest {

    @NotNull(message = "Listening proficiency cannot be null")
    private Listening listening;

    @NotNull(message = "Speaking proficiency cannot be null")
    private Listening speaking;

    @NotNull(message = "Reading proficiency cannot be null")
    private Writing reading;

    @NotNull(message = "Writing proficiency cannot be null")
    private Writing writing;

    @NotNull(message = "Language name ID cannot be null")
    private UUID languageNameId;
}
