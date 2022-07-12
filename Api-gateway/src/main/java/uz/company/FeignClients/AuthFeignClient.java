package uz.company.FeignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="kiruvchi")
public interface AuthFeignClient {
    @RequestMapping(method = RequestMethod.GET,value = "/kiruvchi/api/auth/validToken")
    Boolean validToken(@RequestHeader(value = "Authorization",required = true) String authorization);
}
