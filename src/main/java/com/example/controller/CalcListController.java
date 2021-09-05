package com.example.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.CalcDate;
import com.example.form.BaseDateForm;
import com.example.service.CalcService;


@Controller
@RequestMapping("/calc")
public class CalcListController {
	
	@Autowired
	private CalcService calcService;
	
	@GetMapping
	public String getcalcList(@ModelAttribute BaseDateForm form ,Model model) {
		
		//baseDateにデフォルトで値をセット
		form.setBaseDate(LocalDate.now());
		
		//計算一覧を取得
		List<CalcDate> calcList = calcService.getcalcAll();
		
		//Modelに登録
		model.addAttribute("calcList", calcList);
		
		//list.htmlを返す
		return "list";
	}

	@PostMapping
	public String postcalcResult(  @DateTimeFormat(pattern="yyyy/MM/dd") @ModelAttribute BaseDateForm form ,Model model) {
		
		
		System.out.println(form);
		
		
		//計算一覧を取得
		List<CalcDate> calcList = calcService.getcalcAll();
		
		//getcalc
		calcList = calcService.getcalcResultAll(calcList,form.getBaseDate());
		
		
		System.out.println(calcList);
		
		//Modelに登録
		model.addAttribute("calcList", calcList);

		//list.htmlを返す
		return "list";
	}
	
}