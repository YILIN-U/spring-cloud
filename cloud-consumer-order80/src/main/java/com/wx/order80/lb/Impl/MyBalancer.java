package com.wx.order80.lb.Impl;

import com.wx.order80.lb.LoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyBalancer implements LoadBalancer {

    private AtomicInteger atomicInteger=new AtomicInteger(0);


    public final int getAndIncream(){
        int current;
        int next;
        do {
            current=this.atomicInteger.get();
            next=current>2147483647 ?0:current+1;
        }while(!atomicInteger.compareAndSet(current,next));
        System.out.println("********第几次访问 ："+next);
        return next;
    }


//    得到所要访问的服务的总实例
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index=getAndIncream()%serviceInstances.size();
        return serviceInstances.get(index);
    }
}
