package org.bluebridge.java8.section_04_optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 男孩实体类
 *
 * @author lingwh
 * @date 2026/6/22 15:10
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Boy {

    private Girl girl;
}
