package com.sixpmpricetracker.service.impl;

import com.sixpmpricetracker.model.Product;
import com.sixpmpricetracker.repository.ProductRepository;
import com.sixpmpricetracker.service.ProductReportService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.Writer;
import java.util.List;

//@Service
//@AllArgsConstructor
public class ProductReportServiceImpl implements ProductReportService {
//    private final ProductRepository productRepository;


//    @SneakyThrows
//    @Override
//    public InputStream saveProductToFile(Writer writer) {
//        List<Product> allProducts = productRepository.findAll();
//        try(CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)){
//            for (Product product : allProducts){
//                csvPrinter.printRecord(product.getId(), product.getChatId(), product.getName(), product.getOriginPrice());
//            }
//        }
//        return null;
//    }
}
