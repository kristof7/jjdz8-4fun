package com.infoshare.fourfan.servlet;

import com.infoshare.fourfan.domain.datatypes.Product;
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
import java.util.Map;
import java.util.logging.Logger;

@WebServlet("/admin-find-product-by-id")
public class AdminFindProductByIdServlet extends HttpServlet {

    @Inject
    private ProductService productService;

    @Inject
    private TemplateProvider templateProvider;

    private static final Logger logger = Logger.getLogger(AdminFindProductByIdServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String idParam = req.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Product product = productService.findProductById(Integer.valueOf(idParam));
        PrintWriter printWriter = resp.getWriter();

        Template template = templateProvider.getTemplate(getServletContext(), "editProduct.ftlh");
        Map<String, Object> dataModel = new HashMap<>();
        if (dataModel != null && product!= null){
            dataModel.put("product", product);
            dataModel.put("productId", idParam);
        } else {
            dataModel.put("errorMessage", "Product has not been found.");
        }
        try {
            template.process(dataModel, printWriter);
        } catch (TemplateException e) {
            logger.severe(e.getMessage());
        }
    }
}
