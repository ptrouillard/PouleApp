package com.pedro.raspberry.poule.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoorController {

    @Autowired
    public DoorAdapter adapter;

    /**
     * calling /door/up?ms=1500 will send a command to open the door during 1500 ms
     * @param ms
     * @return
     */
    @GetMapping("/door/up")
    public DoorActionResult up(@RequestParam(value = "ms", defaultValue = "1000") Long ms)
    {
        try {
            adapter.stepUp(ms);
        } catch (RuntimeException e) {
            return DoorActionResult.error(e.getMessage());
        }
        return DoorActionResult.success();
    }

    /**
     * calling /door/down?ms=1500 will send a command to close the door during 1500 ms
     * @param ms
     * @return
     */
    @GetMapping("/door/down")
    public DoorActionResult down(@RequestParam(value = "ms", defaultValue = "1000") Long ms)
    {
        try {
            adapter.stepDown(ms);
        } catch (RuntimeException e) {
            return DoorActionResult.error(e.getMessage());
        }
        return DoorActionResult.success();
    }
}
