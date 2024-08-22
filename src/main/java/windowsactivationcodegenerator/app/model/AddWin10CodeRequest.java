package windowsactivationcodegenerator.app.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddWin10CodeRequest {

    @NotBlank
    @Size(max = 100)
    private String activationCode;

    @Size(max = 100)
    private String version;

    private LocalDateTime createdAt;
}
