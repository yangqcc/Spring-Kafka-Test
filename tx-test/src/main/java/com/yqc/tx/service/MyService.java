package com.yqc.tx.service;

import com.yqc.tx.entity.MyBook;
import com.yqc.tx.service.dao.MyBookDaoInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//注解方式设置事务、在类的级别上设置只读事务
@Component
@Transactional(readOnly = true)
public class MyService {

    @Autowired
    private MyBookDaoInt myBookDaoInt;

    //声明事务方法，事务方法注解优先级高于类注解，设置属性会覆盖类注解。propagation设置该方法需要在事务条件下运行;isolation设置事务隔离级别为默认
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void add(MyBook myBook) {
        myBookDaoInt.insert(myBook);
        int i = 1/0;  //事务测试
    }

    //NOT_SUPPORTED声明该方法不需要事务，若该方法在一个事务中被调用，则事务会被暂时挂起直至该方法运行结束
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<MyBook> getAll() {
        //TODO 暂时返回空
        return null;
    }

}
