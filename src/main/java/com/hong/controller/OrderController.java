package com.hong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

	private static String FPATH = "order/";
	
	@RequestMapping("/order.page")
	public String orderPage() {
		return FPATH+"order";
	}

	@RequestMapping("/orderBatch.page")
	public String orderBatchPage() {
		return FPATH + "orderBatch";
	}
}
