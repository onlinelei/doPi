package win.suroot.dopi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@MapperScan("win.suroot.dopi.mapper")
public class DoPiApplication {
    public static void main(String[] args) {
        SpringApplication.run(DoPiApplication.class, args);
    }
}
