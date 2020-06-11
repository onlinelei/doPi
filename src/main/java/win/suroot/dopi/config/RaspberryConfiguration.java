package win.suroot.dopi.config;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import win.suroot.dopi.linstener.AllPinChangeListener;


/**
 * Config Class
 *
 * @author: qianlei
 * @date: 2019-09-29 15:58
 */
@Configuration
public class RaspberryConfiguration {

    @Bean
    public GpioController piIoController() {
        GpioController piIoController = GpioFactory.getInstance();
        return piIoController;
    }

    @Bean
    public GpioPinListenerDigital piIoListener() {
        return new AllPinChangeListener();
    }
}