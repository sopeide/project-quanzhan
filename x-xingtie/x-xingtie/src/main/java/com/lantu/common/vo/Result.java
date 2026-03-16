package com.lantu.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author itmei
 * @Date 2023/10/9 21:21
 * @description:
 * @Title: Result
 * @Package com.lantu.common.vo
 */
@Data
@NoArgsConstructor //无参构造
@AllArgsConstructor //全参构造
public class Result<T> {
    private Integer code;
    private String message;
    private T data;
//    函数重载
//        无返回值
        public static<T>  Result<T> success(){
            return new Result<>(20000,"success",null);
        }
        //有返回值 data
        public static<T>  Result<T> success(T data){
            return new Result<>(20000,"success",data);
        }
        //有返回值data 和message
        public static<T>  Result<T> success(T data, String message){
            return new Result<>(20000,message,data);
        }
        //只有message
        public static<T>  Result<T> success(String message){
            return new Result<>(20000,message,null);
        }
        //错误返回
        public static<T>  Result<T> fail(){
            return new Result<>(20001,"fail",null);
        }

        public static<T>  Result<T> fail(Integer code){
            return new Result<>(code,"fail",null);
        }

        public static<T>  Result<T> fail(Integer code, String message){
            return new Result<>(code,message,null);
        }

        public static<T>  Result<T> fail( String message){
            return new Result<>(20001,message,null);
        }
}
