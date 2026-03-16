package com.lantu;

import com.lantu.sys.entity.Biaodan1;
import com.lantu.sys.entity.User;
import com.lantu.sys.mapper.Biaodan1Mapper;
import com.lantu.sys.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class XXingtieApplicationTests {

    @Resource
    private Biaodan1Mapper biaodan1Mapper;

    @Test
    void testMapper() {
        List<Biaodan1> biaodan1s = biaodan1Mapper.selectList(null);
        //List<biaodan1> biaodan1s = biaodan1Mapper.selectList(null);
        biaodan1s.forEach(System.out::println);
    }

}
