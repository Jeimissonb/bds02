package com.devsuperior.bds02.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.services.CityService;


@RestController
@RequestMapping("/cities")
public class CityController {
	
	@Autowired
	private CityService service;
	//Tentei fazer com essa implementação comentada, ela funciona perfeitamente no postman mas não funciona no teste, então optei por fazer usando lista
	/* @GetMapping
	public ResponseEntity<Page<CityDTO>> findAll (Pageable pageable){
		PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("name"));
		Page <CityDTO> list = service.findAll(pageRequest);
		return ResponseEntity.ok().body(list);
		
	} */
	
	@GetMapping
	public ResponseEntity<List<CityDTO>> findAll (){
		List <CityDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	} 
	
	@PostMapping
	public ResponseEntity<CityDTO> insert (@RequestBody CityDTO dto) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		dto = service.insert(dto);
		return ResponseEntity.created(uri).body(dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete (@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
