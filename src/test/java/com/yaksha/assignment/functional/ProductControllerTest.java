package com.yaksha.assignment.functional;

import static com.yaksha.assignment.utils.TestUtils.businessTestFile;
import static com.yaksha.assignment.utils.TestUtils.currentTest;
import static com.yaksha.assignment.utils.TestUtils.testReport;
import static com.yaksha.assignment.utils.TestUtils.yakshaAssert;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.yaksha.assignment.controller.ProductController;
import com.yaksha.assignment.utils.CustomParser;

public class ProductControllerTest {

	private ProductController productController;

	@BeforeEach
	public void setup() {
		// Initialize the ProductController before each test
		productController = new ProductController();
	}

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testProductControllerAnnotations() throws Exception {
		// Check for @Controller annotation on ProductController class
		boolean hasControllerAnnotation = CustomParser.checkClassAnnotation(
				"src/main/java/com/yaksha/assignment/controller/ProductController.java", "Controller");

		// Check for @GetMapping("/products") on viewProducts() method
		boolean hasGetMappingViewProducts = CustomParser.checkMethodAnnotationWithValueAgain(
				"src/main/java/com/yaksha/assignment/controller/ProductController.java", "viewProducts", "/products");

		// Check for @GetMapping("/product/add") on showAddProductForm() method
		boolean hasGetMappingAddProduct = CustomParser.checkMethodAnnotationWithValueAgain(
				"src/main/java/com/yaksha/assignment/controller/ProductController.java", "showAddProductForm",
				"/product/add");

		// Check for @PostMapping("/product/add") on addProduct() method
		boolean hasPostMappingAddProduct = CustomParser.checkMethodAnnotationWithValueAgain(
				"src/main/java/com/yaksha/assignment/controller/ProductController.java", "addProduct", "/product/add");

		// Check for @GetMapping("/product/edit/{id}") on showEditProductForm() method
		boolean hasGetMappingEditProduct = CustomParser.checkMethodAnnotationWithValueAgain(
				"src/main/java/com/yaksha/assignment/controller/ProductController.java", "showEditProductForm",
				"/product/edit/{id}");

		// Check for @PostMapping("/product/edit/{id}") on updateProduct() method
		boolean hasPostMappingUpdateProduct = CustomParser.checkMethodAnnotationWithValueAgain(
				"src/main/java/com/yaksha/assignment/controller/ProductController.java", "updateProduct",
				"/product/edit/{id}");

		// Check for @GetMapping("/product/delete/{id}") on deleteProduct() method
		boolean hasGetMappingDeleteProduct = CustomParser.checkMethodAnnotationWithValueAgain(
				"src/main/java/com/yaksha/assignment/controller/ProductController.java", "deleteProduct",
				"/product/delete/{id}");

		// Run auto-grading using yakshaAssert
		yakshaAssert(currentTest(),
				hasControllerAnnotation && hasGetMappingViewProducts && hasGetMappingAddProduct
						&& hasPostMappingAddProduct && hasGetMappingEditProduct && hasPostMappingUpdateProduct
						&& hasGetMappingDeleteProduct,
				businessTestFile);
	}

	@Test
	public void testAddProductJspTagsAndFormAction() throws Exception {
		String filePath = "src/main/webapp/WEB-INF/views/add-product.jsp";

		// Check for form action URL and required input fields
		boolean hasFormTag = CustomParser.checkJspTagPresence(filePath, "<form");
		boolean hasFormAction = CustomParser.checkJspTagPresence(filePath, "action=\"/product/add\"");
		boolean hasNameField = CustomParser.checkJspTagPresence(filePath, "path=\"name\"");
		boolean hasDescriptionField = CustomParser.checkJspTagPresence(filePath, "path=\"description\"");
		boolean hasPriceField = CustomParser.checkJspTagPresence(filePath, "path=\"price\"");
		boolean hasSubmitButton = CustomParser.checkJspTagPresence(filePath,
				"<button type=\"submit\">Add Product</button>");

		// Run auto-grading using yakshaAssert
		yakshaAssert(currentTest(),
				hasFormTag && hasFormAction && hasNameField && hasDescriptionField && hasPriceField && hasSubmitButton,
				businessTestFile);
	}

	@Test
	public void testEditProductJspTagsAndFormAction() throws Exception {
		String filePath = "src/main/webapp/WEB-INF/views/edit-product.jsp";

		// Check for form action URL, input fields, and proper bindings
		boolean hasFormTag = CustomParser.checkJspTagPresence(filePath, "<form");
		boolean hasFormAction = CustomParser.checkJspTagPresence(filePath, "action=\"/product/edit/${product.id}\"");
		boolean hasNameField = CustomParser.checkJspTagPresence(filePath, "path=\"name\"");
		boolean hasDescriptionField = CustomParser.checkJspTagPresence(filePath, "path=\"description\"");
		boolean hasPriceField = CustomParser.checkJspTagPresence(filePath, "path=\"price\"");
		boolean hasSubmitButton = CustomParser.checkJspTagPresence(filePath,
				"<button type=\"submit\">Update Product</button>");

		// Run auto-grading using yakshaAssert
		yakshaAssert(currentTest(),
				hasFormTag && hasFormAction && hasNameField && hasDescriptionField && hasPriceField && hasSubmitButton,
				businessTestFile);
	}

	@Test
	public void testProductListJspDynamicContent() throws Exception {
		String filePath = "src/main/webapp/WEB-INF/views/product-list.jsp";

		// Check if dynamic content like product attributes are being rendered
		boolean hasProductId = CustomParser.checkJspTagPresence(filePath, "${product.id}");
		boolean hasProductName = CustomParser.checkJspTagPresence(filePath, "${product.name}");
		boolean hasProductDescription = CustomParser.checkJspTagPresence(filePath, "${product.description}");
		boolean hasProductPrice = CustomParser.checkJspTagPresence(filePath, "${product.price}");
		boolean hasEditLink = CustomParser.checkJspTagPresence(filePath, "/product/edit/${product.id}");
		boolean hasDeleteLink = CustomParser.checkJspTagPresence(filePath, "/product/delete/${product.id}");

		// Run auto-grading using yakshaAssert
		yakshaAssert(currentTest(), hasProductId && hasProductName && hasProductDescription && hasProductPrice
				&& hasEditLink && hasDeleteLink, businessTestFile);
	}

	@Test
	public void testSuccessPageContent() throws Exception {
		String filePath = "src/main/webapp/WEB-INF/views/success.jsp";

		// Check for success message and the back link
		boolean hasSuccessMessage = CustomParser.checkJspTagPresence(filePath,
				"<h1>Product added/updated successfully!</h1>");
		boolean hasBackLink = CustomParser.checkJspTagPresence(filePath,
				"<a href=\"/products\">Back to Product List</a>");

		// Run auto-grading using yakshaAssert
		yakshaAssert(currentTest(), hasSuccessMessage && hasBackLink, businessTestFile);
	}
}
