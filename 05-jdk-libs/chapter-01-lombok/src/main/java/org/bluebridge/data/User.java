package org.bluebridge.data;

import lombok.Data;

/**
 * User实体，@Data = @Getter + @Setter + @ToString + @EqualsAndHashCode + @NoArgsConstructor
 *
 * @author lingwh
 * @date 2025/8/18 11:41
 */
@Data
public class User {

    private String id;
    private String name;
    private int age;
    private String email;
}
