package com.klipkart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.klipkart.binding.FetchProductBinding;
import com.klipkart.binding.ProductBinding;
import com.klipkart.model.ImageModel;
import com.klipkart.model.Product;
import com.klipkart.service.ProductService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(value = "/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ObjectMapper mapper;

	@PostMapping(value = "/add")
	public ResponseEntity<Product> saveProduct(@RequestParam("product") String product,
			@RequestParam("imageFile") MultipartFile file) {

		try {

			ProductBinding productBinding = mapper.readValue(product, ProductBinding.class);

			ImageModel imageModel = productService.uploadImage(file);

			productBinding.setProductImages(imageModel);

			Product savedProduct = productService.saveProduct(productBinding);

			return new ResponseEntity<Product>(savedProduct, HttpStatus.OK);

		} catch (Exception e) {

			System.out.println(e.getMessage());

			return new ResponseEntity<Product>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/getAll")
	public ResponseEntity<List<FetchProductBinding>> getAllProducts() {

		List<FetchProductBinding> productList = productService.getAllProducts();

		return new ResponseEntity<List<FetchProductBinding>>(productList, HttpStatus.OK);
	}

	@GetMapping("/select/{category}")
	public ResponseEntity<List<FetchProductBinding>> getSelectedProduct(@PathVariable String category) {

		List<FetchProductBinding> productByCategory = productService.getProductByCategory(category);

		return new ResponseEntity<List<FetchProductBinding>>(productByCategory, HttpStatus.OK);
	}

}