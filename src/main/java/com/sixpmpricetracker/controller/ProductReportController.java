package com.sixpmpricetracker.controller;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.sixpmpricetracker.service.ProductReportService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

//@Controller
//@RequiredArgsConstructor
//@RequestMapping(value = "/report")
public class ProductReportController {
//    private final ProductReportService productReportService;
//    private final Storage storage;

//    @SneakyThrows
////    @GetMapping
//    public void sendReportToEmail(HttpServletResponse servletResponse) {
//        File fileName = new File("report.csv");
//        servletResponse.setContentType("text/csv");
//        servletResponse.addHeader("Content-Disposition","attachment; filename=\"" + fileName + "\"");
//        productReportService.saveProductToFile(servletResponse.getWriter());
//
//        BlobId id = BlobId.of("price_tracker_report", String.valueOf(productReportService.saveProductToFile(servletResponse.getWriter())));
//        BlobInfo blobInfo = BlobInfo.newBuilder(id).build();
//        byte [] arr = Files.readAllBytes(Paths.get(fileName.toURI()));
////        storage.create(blobInfo, arr);
//    }
}
