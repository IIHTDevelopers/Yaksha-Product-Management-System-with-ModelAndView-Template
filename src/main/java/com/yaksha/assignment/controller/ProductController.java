package com.yaksha.assignment.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yaksha.assignment.models.Product;

@Controller
public class ProductController {

	// Simulating a product list (in-memory storage)
	private List<Product> products = new ArrayList<>();
	private long nextId = 1;

	// Display list of products
	@GetMapping("/products")
	public ModelAndView viewProducts() {
		ModelAndView modelAndView = new ModelAndView("product-list");
		modelAndView.addObject("products", products);
		return modelAndView;
	}

	// Show form to add a new product
	@GetMapping("/product/add")
	public String showAddProductForm(Model model) {
		model.addAttribute("product", new Product());
		return "add-product";
	}

	// Handle form submission to add a new product
	@PostMapping("/product/add")
	public String addProduct(@ModelAttribute Product product) {
		product.setId(nextId++);
		products.add(product);
		return "redirect:/products"; // Redirect to product list after successful addition
	}

	// Show form to edit an existing product
	@GetMapping("/product/edit/{id}")
	public ModelAndView showEditProductForm(@PathVariable("id") long id) {
		Product product = products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
		ModelAndView modelAndView = new ModelAndView("edit-product");
		modelAndView.addObject("product", product);
		return modelAndView;
	}

	// Handle form submission to update an existing product
	@PostMapping("/product/edit/{id}")
	public String updateProduct(@PathVariable("id") long id, @ModelAttribute Product updatedProduct) {
		products.stream().filter(product -> product.getId() == id).forEach(product -> {
			product.setName(updatedProduct.getName());
			product.setDescription(updatedProduct.getDescription());
			product.setPrice(updatedProduct.getPrice());
		});
		return "redirect:/products";
	}

	// Handle product deletion
	@GetMapping("/product/delete/{id}")
	public String deleteProduct(@PathVariable("id") long id) {
		products.removeIf(product -> product.getId() == id);
		return "redirect:/products";
	}
}
