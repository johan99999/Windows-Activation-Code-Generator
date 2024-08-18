package windowsactivationcodegenerator.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Windows7Response {

    private Integer id;

    private String activationCode;

    private String version;

    private LocalDateTime createdAt;
}
