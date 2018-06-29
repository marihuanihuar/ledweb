package com.example.led.controller;

import com.pi4j.io.gpio.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LedController {

    private static GpioPinDigitalOutput pin;

    @RequestMapping("/")
    public String greeting(){
        return "Hello World!!!!";
    }

    @RequestMapping("/light")
    public String light(){

        if (pin == null) {
            GpioController gpio = GpioFactory.getInstance();
            pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.LOW);
        }
        if (pin.isHigh()){
            log.info("LED OFF");
        } else {
            log.info("LED ON");
        }

        pin.toggle();

        return "OK";
    }
}
