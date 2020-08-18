package com.altimetrik.rest.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.altimetrik.rest.api.helper.ExcelHelper;
import com.altimetrik.rest.api.model.Response;
import com.altimetrik.rest.api.service.ExcelService;

@Controller
@RequestMapping("/excel")
public class ExcelController {

  @Autowired
  ExcelService fileService;

  @PostMapping("/upload")
  public ResponseEntity<Response> uploadFile(@RequestParam("file") MultipartFile file) {
    String message = "";

    if (ExcelHelper.hasExcelFormat(file)) {
      try {
        fileService.save(file);

        message = "Uploaded the file successfully: " + file.getOriginalFilename();
        return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK.value(),message));
      } catch (Exception e) {
        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new Response(message));
      }
    }

    message = "Please upload an excel file!";
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(message));
  }

}