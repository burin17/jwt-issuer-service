package com.gmail.burinigor7.jwtissuerservice.inner_service_api;

import com.gmail.burinigor7.jwtissuerservice.dto.FetchedCredentials;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "crud-service")
@Service
public interface CrudServiceApiInterface {

    @GetMapping("/api/users")
    FetchedCredentials getUserByUsername(@RequestParam String username);
}
