package org.bluebridge.java8.chapter_04_optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lingwh
 * @desc 男孩实体类
 * @date 2026/7/9 00:00
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Boy {
    private Girl girl;
}
