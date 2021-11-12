package com.devsuperior.bds02.services;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;

@Service
public class CityService {
	
	@Autowired
	private CityRepository repository;

	 // implementação que funciona no postman mas não funciona no teste
	/* @Transactional (readOnly = true)
	public Page<CityDTO> findAll(PageRequest pageRequest) {
		Page<City> page = repository.findAll(pageRequest);
		
		return page.map(x -> new CityDTO(x));
	} */
	
	@Transactional (readOnly = true)
	public List<CityDTO> findAll() {
		List<City> page = repository.findAll(Sort.by("name"));
		
		return page.stream().map(x -> new CityDTO(x)).collect(Collectors.toList());
	}
	

	@Transactional()
	public CityDTO insert(CityDTO dto) {
		City entity = new City();
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new CityDTO(entity);
	}

	@Transactional
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found: " +id);
		} 
		
		
		
	}
	
	

}
