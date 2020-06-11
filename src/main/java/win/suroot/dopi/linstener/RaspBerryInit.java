package win.suroot.dopi.linstener;


import com.pi4j.io.gpio.GpioController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * do something in start up
 *
 * @author: qianlei
 * @date: 2019-09-29 15:58
 */
@Component
public class RaspBerryInit {

    @Autowired
    private GpioController gpioController;

    @PostConstruct
    public void init() {
        System.out.println("RaspBerry init...............");
    }
}
