package com.project.resourceserver.controller;

import com.project.resourceserver.constants.*;

import com.project.resourceserver.model.Account;
import com.project.resourceserver.model.ConfirmationToken;
import com.project.resourceserver.repository.AccountRepository;
import com.project.resourceserver.repository.ConfirmationTokenRepository;
import com.project.resourceserver.service.EmailSenderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // Constructor
    public AccountController(AccountRepository accountRepository, 
                             BCryptPasswordEncoder bCryptPasswordEncoder,
                             ConfirmationTokenRepository confirmationTokenRepository) {
        this.accountRepository = accountRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.confirmationTokenRepository = confirmationTokenRepository;
    }


    @PostMapping(value="/public/register")
    public ResponseEntity<String> registerUser(@RequestBody Account account) {
        HttpHeaders httpHeaders = new HttpHeaders();
        Account existingAccount = accountRepository.findByUsername(account.getUsername());
 
        if(existingAccount != null)
            return new ResponseEntity<>("User already exists.", httpHeaders, HttpStatus.OK);

        bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = bCryptPasswordEncoder.encode(account.getPassword());
        // String encryptedPassword = bCryptPasswordEncoder.encode("password");
        account.setPassword(encryptedPassword);
        accountRepository.save(account);

        ConfirmationToken confirmationToken = new ConfirmationToken(account);

        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(account.getUsername());
        mailMessage.setSubject("Spring Boot Complete Registration!");
        mailMessage.setFrom("jakefrench1984@gmail.com");
        mailMessage.setText("To confirm your account, please click here: " + 
        "http://" + Constants.SERVER_IP + "/resource/public/confirm-account/" + confirmationToken.getConfirmationToken());

        emailSenderService.sendEmail(mailMessage);

        String returnMessage = String.format("Email Sent to %s", account.getUsername());
        return new ResponseEntity<>(returnMessage, httpHeaders, HttpStatus.OK);
    }
    

    @RequestMapping(value="/public/confirm-account/{confirmationToken}", method={RequestMethod.GET, RequestMethod.POST})
    public String confirmUserAccount(@PathVariable String confirmationToken) {
        String result = "";
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null) {
            // Account account = accountRepository.findByUsername(token.getAccount().getUsername()).get();
            Account account = accountRepository.findByUsername(token.getAccount().getUsername());
            account.setVerified(true);
            accountRepository.save(account);
            result = "Success";
        } else {
            result = "Error.";
        }
        return result;
    }

    @GetMapping("/public/account/email={email}")
    public Account getAccountByEmail(@PathVariable String email) {
        return accountRepository.findByUsername(email);
    }

}