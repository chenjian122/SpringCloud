package com.sinspark.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HomeController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	@RequestMapping("/consumer")
	public String doHello(){
		ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-client");
		String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/";
        return restTemplate.getForObject(url, String.class);
	}
}
