package com.infoshare.fourfan.servlet;

import com.infoshare.fourfan.domain.datatypes.Product;
import com.infoshare.fourfan.domain.datatypes.ProductCategory;
import com.infoshare.fourfan.freemarker.TemplateProvider;
import com.infoshare.fourfan.service.ProductService;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@WebServlet("/filterUserProductsByCategory")
public class FilterProductsForUserByCategory extends HttpServlet {

    @Inject
    private ProductService productService;

    private static final Logger logger
            = Logger.getLogger(FilterProductsForUserByCategory.class.getName());

    @Inject
    private TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = resp.getWriter();

        String category = req.getParameter("category");

        Template template = templateProvider.getTemplate(getServletContext(), "filterByCategory.ftlh");
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("dairy", ProductCategory.NABIAŁ.ordinal());
        dataModel.put("veggies", ProductCategory.WARZYWA.ordinal());
        dataModel.put("fruits", ProductCategory.OWOCE.ordinal());

        if(category != null && !category.equals("Wybierz") ){
            Integer categoryInt = Integer.parseInt(category);
            List<Product> products = productService.filterByCategory(categoryInt);
            dataModel.put("products", products);
        }

        try {
            template.process(dataModel, printWriter);
        } catch (TemplateException e) {
            logger.severe(e.getMessage());
        }
    }
}