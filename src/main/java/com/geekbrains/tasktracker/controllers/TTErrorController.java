package com.geekbrains.tasktracker.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class TTErrorController implements ErrorController {
    @RequestMapping("/error")
    public String errorHandler(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if(status != null) {
            String message = String.valueOf(request.getAttribute(RequestDispatcher.ERROR_MESSAGE));
            model.addAttribute("title", "Что-то пошло не так...");
            model.addAttribute("error_message", message);
            model.addAttribute("error_code", status.toString());
        }
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "error";
    }
}
