package xyz.slienceme.servicebase.exceptionhandler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuliException extends RuntimeException {

    @ApiModelProperty(value = "状态码")
    private Integer code;
    private String msg;
    /*
    try {
        int a = 10/0;
    }catch(Exception e) {
        throw new GuliException(20001,"出现自定义异常");
    }
    */


}
