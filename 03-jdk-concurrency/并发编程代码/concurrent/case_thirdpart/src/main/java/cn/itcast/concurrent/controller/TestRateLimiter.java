package cn.itcast.concurrent.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Semaphore;

/**
 * @author lingwh
 * @desc 限流测试控制器
 * @date 2026/7/9 00:00
 */
@RestController
public class TestRateLimiter {

    //    private RateLimiter limiter = RateLimiter.create(50);
    private Semaphore semaphore = new Semaphore(2);

    @GetMapping("/test")
    public String test() throws InterruptedException {
//        limiter.acquire();
        semaphore.acquire();
        try {
            return "ok";
        } finally {
            semaphore.release();
        }
    }
}
