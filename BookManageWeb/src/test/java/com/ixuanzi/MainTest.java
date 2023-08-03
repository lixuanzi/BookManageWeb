package com.ixuanzi;

import com.ixuanzi.util.SqlUtil;
import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    public void test() {
        SqlUtil.doSqlWork(bookMapper -> {
            bookMapper.selectBorrow().forEach(System.out::println);
        });
    }
}
