package com.mp.animals.controller;

import com.mp.animals.domain.Host;
import com.mp.animals.service.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/animals/host")
public class HostController {

    @Autowired
    private HostService hostService;

    @GetMapping
    public Set<Host> getAllHosts() {
        return hostService.getAllHosts();
    }
}
