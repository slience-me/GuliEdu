package xyz.slienceme.eduservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xyz.slienceme.eduservice.entity.UcenterMember;

@Component
@FeignClient(value = "service-ucenter", fallback = UcenterClientImpl.class)
public interface UcenterClient {

    @GetMapping("/educenter/member/getInfoUc/{id}")
    public UcenterMember getInfo(@PathVariable("id") String id) ;
}
