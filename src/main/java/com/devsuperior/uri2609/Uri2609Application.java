package com.devsuperior.uri2609;

import com.devsuperior.uri2609.dto.CategorySumDTO;
import com.devsuperior.uri2609.projections.CategorySumProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2609.repositories.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Uri2609Application implements CommandLineRunner {

	@Autowired
	private CategoryRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2609Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<CategorySumProjection> categorySumProjectionList = repository.searchSQL();
		List<CategorySumDTO> categorySumDTOList = categorySumProjectionList.stream().map(x-> new CategorySumDTO(x)).collect(Collectors.toList());
		List<CategorySumDTO> categorySumDTOListJPQL = repository.searchJPQL();

		System.out.println("");
		System.out.println("-----SQL-PROJ-----");

		for (CategorySumProjection item: categorySumProjectionList) {
			System.out.println(item.getName() + " - " + item.getSum());
		}

		System.out.println("");
		System.out.println("-----SQL-DTO-----");

		for (CategorySumDTO item: categorySumDTOList) {
			System.out.println(item.getName() + " - " + item.getSum());
		}

		System.out.println("");
		System.out.println("-----JPQL-DTO-----");

		for (CategorySumDTO item: categorySumDTOListJPQL) {
			System.out.println(item.getName() + " - " + item.getSum());
		}

	}
}
