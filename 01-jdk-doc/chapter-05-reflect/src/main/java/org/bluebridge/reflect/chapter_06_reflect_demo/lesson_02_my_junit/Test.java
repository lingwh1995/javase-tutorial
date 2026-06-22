package org.bluebridge.reflect.chapter_06_reflect_demo.lesson_02_my_junit;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**  
 * @Test注解
 * @author ronin  
 * @date 2019年3月14日  
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
	
}
