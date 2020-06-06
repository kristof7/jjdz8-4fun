package com.infoshare.fourfan.servlet;

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

@WebServlet("/filterUserProductsByCalories")
public class FilterProductsForUserByCalories extends HttpServlet {

    public enum CaloriesRangeEnum {
        PRZEDZIAL_0_150,
        PRZEDZIAL_151_300,
        PRZEDZIAL_301_450;
    }
        @Inject
        private ProductService productService;

        private static final Logger logger
                = Logger.getLogger(com.infoshare.fourfan.servlet.FilterProductsForUserByCategory.class.getName());

        @Inject
        private TemplateProvider templateProvider;

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter printWriter = resp.getWriter();

            String calories = req.getParameter("calories");

            Template template = templateProvider.getTemplate(getServletContext(), "filterByCalories.ftlh");
            Map<String, Object> dataModel = new HashMap<>();
            dataModel.put("firstRange", CaloriesRangeEnum.PRZEDZIAL_0_150);
            dataModel.put("secondRange", CaloriesRangeEnum.PRZEDZIAL_151_300);
            dataModel.put("thirdRange", CaloriesRangeEnum.PRZEDZIAL_301_450);

            if(calories != null && !calories.equals("Przedzial") ) {
                CaloriesRangeEnum caloriesRangeEnum = CaloriesRangeEnum.valueOf(calories);
                CaloriesRange caloriesRange = new CaloriesRange(caloriesRangeEnum).invoke();
                int productMin = caloriesRange.getProductMin();
                int productMax = caloriesRange.getProductMax();
                dataModel.put("products", productService.filterByCalories(productMin, productMax));
                switch (caloriesRangeEnum) {
                    case PRZEDZIAL_0_150:
                        dataModel.put("firstChoiceSelected", "checked");
                        break;

                    case PRZEDZIAL_151_300:
                        dataModel.put("secondChoiceSelected", "checked");
                        break;

                    case PRZEDZIAL_301_450:
                        dataModel.put("thirdChoiceSelected", "checked");
                        break;
                }
            }

            try {
                template.process(dataModel, printWriter);
            } catch (TemplateException e) {
                logger.severe(e.getMessage());
            }
        }

    private static class CaloriesRange {
        private CaloriesRangeEnum caloriesRange;
        private int productMin;
        private int productMax;

        public CaloriesRange(CaloriesRangeEnum caloriesRange) {
            this.caloriesRange = caloriesRange;
        }

        public int getProductMin() {
            return productMin;
        }

        public int getProductMax() {
            return productMax;
        }

        public CaloriesRange invoke() {
            productMin = -1;
            productMax = 1000;

            switch (caloriesRange) {
                case PRZEDZIAL_0_150:
                    productMin = 0;
                    productMax = 150;
                    break;
                case PRZEDZIAL_151_300:
                    productMin = 151;
                    productMax = 300;
                    break;
                case PRZEDZIAL_301_450:
                    productMin = 301;
                    productMax = 450;
                    break;
            }
            return this;
        }
    }
}
