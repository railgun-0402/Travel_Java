package com.example.samuraitravel.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.repository.HouseRepository;

@Controller
@RequestMapping("/admin/houses")
public class AdminHouseController {
	private final HouseRepository houseRepository;
	
	// コンストラクタで依存性の注入（DI）を行う
	public AdminHouseController(HouseRepository houseRepository) {
		this.houseRepository = houseRepository;
	}
	
	@GetMapping
	public String index(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable, @RequestParam(name = "keyword", required = false) String keyword) {
		// ページネーションを付与
		Page<House> housePage;
		
		if (keyword != null && !keyword.isEmpty()) {
			// 検索する場合は部分一致で探す
			housePage = houseRepository.findByNameLike("%" + keyword + "%", pageable);
		} else {
			housePage = houseRepository.findAll(pageable);
		}
		model.addAttribute("housePage", housePage);
		model.addAttribute("keyword", keyword);
		
		return "admin/houses/index";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable(name = "id") Integer id, Model model) {
		// URLのidと一致する民宿データを1つだけ取得
		House house = houseRepository.getReferenceById(id);
		model.addAttribute("house", house);
		
		return "admin/houses/show";		
	}
}
