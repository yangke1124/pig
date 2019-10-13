package com.example.demo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.bean.Goods;
import com.example.demo.service.GoodsService;

@Controller
public class GoodsController {

	@Resource
	private GoodsService goodsService;

	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	@RequestMapping("list")
	public String list(Model model) {
		List<Goods> goodList = goodsService.getGoodsList();
		model.addAttribute("goodList", goodList);
		return "list";
	}

	@RequestMapping("toAdd")
	public String toAdd() {
		return "add";
	}

	@RequestMapping("addGoods")
	public String addGoods(Goods goods) {
		ListOperations<String, Object> opsForList = redisTemplate.opsForList();
		Long leftPush = opsForList.leftPush("goodsList", goods);
		System.out.println(leftPush);
		return "add";
	}

}
