package com.example.demo.controller;

import com.example.demo.Service.ContactService;
import com.example.demo.Service.EmailService;
import com.example.demo.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/register")
    public String saveContact(@RequestParam String name, @RequestParam String email, @RequestParam String message, Model model) {
        Contact contact = new Contact();
        contact.setName(name);
        contact.setEmail(email);
        contact.setMessage(message);

        contactService.saveContact(contact);

        // Send email to your Gmail with the contact details
        String emailBody = "New contact form submission:\n\n" +
                "Name: " + name + "\n" +
                "Email: " + email + "\n" +
                "Message: " + message;
        emailService.sendEmail("attkhadka551@gmail.com", "New Contact Form Submission", emailBody);

        model.addAttribute("message", "Contact saved successfully!");
        return "redirect:/#contact"; // Redirect to the contact section
    }
}
