package cn.itcast.order.service;

import cn.itcast.feign.clients.UserClient;
import cn.itcast.feign.pojo.User;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserClient userClient;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        //restTemplate方式发送http请求
       /* String url="http://userserver/user/" + order.getUserId();

        order.setUser(restTemplate.getForObject(url, User.class));
       */
        // 4.返回
        //测试分支合并
        User user = userClient.findById(order.getUserId());
        order.setUser(user);


        return order;
    }
}
