package com.example.demo.client.controller;

import com.example.demo.client.dto.Req;
import com.example.demo.client.dto.UserResponse;
import com.example.demo.client.service.RestTemplateService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Client 에서 사용하는 API"})  // swagger
@RestController
@RequestMapping("/client")
public class ClientApiController {

    private final RestTemplateService restTemplateService;

    public ClientApiController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @GetMapping("/hello")
    public String getHello() {
        return restTemplateService.hello();
    }

    @GetMapping("/user")
    public UserResponse getUser() {
        return restTemplateService.getUser();
    }

    @GetMapping("/addUser")
    public UserResponse postUser() {
        return restTemplateService.postUser();
    }

    @GetMapping("/exchange")
    public UserResponse exchange() {
        return restTemplateService.exchange();
    }

    @GetMapping("/generic/exchange")
    public Req<UserResponse> genericExchange() {
        return restTemplateService.genericExchange();
    }

    @ApiOperation(value = "plus 메소드")
    @GetMapping("/plus/{x}")
    public int plus(
            @ApiParam(value = "x값")
            @PathVariable int x,

            @ApiParam(value = "y값")
            @RequestParam int y) {

        return x+y;
    }

    @ApiOperation(value = "minus 메소드")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x", value = "x 값", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "y", value = "y 값", required = true, dataType = "int", paramType = "query")
    })
    @GetMapping("/minus/{x}")
    public int minus(@PathVariable int x, @RequestParam int y) {

        return x-y;
    }
}
