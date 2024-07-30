package com.klipkart.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.klipkart.binding.FetchProductBinding;
import com.klipkart.binding.ProductBinding;
import com.klipkart.model.ImageModel;
import com.klipkart.model.Product;

public interface ProductService {

	public Product saveProduct(ProductBinding productBinding);
	
	public List<FetchProductBinding> getAllProducts();
	
	public ImageModel uploadImage(MultipartFile file);
	
	public List<FetchProductBinding> getProductByCategory(String category);
}
