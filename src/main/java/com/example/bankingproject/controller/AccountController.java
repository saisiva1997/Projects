package com.example.bankingproject.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.bankingproject.dto.AccountDTO;
import com.example.bankingproject.service.AccountService;

@Controller
@RequestMapping("/api/accounts")
public class AccountController {

	@Autowired
	AccountService accountService;

	@PostMapping
	public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO) {
		return new ResponseEntity<AccountDTO>(accountService.createAccount(accountDTO), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AccountDTO> getAccount(@PathVariable Long id) {
		return new ResponseEntity<AccountDTO>(accountService.getAccount(id), HttpStatus.OK);
	}

	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDTO> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request) {
		AccountDTO dto = accountService.depositAmount(id, request.get("amount"));
		return ResponseEntity.ok(dto);
	}

	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDTO> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request) {
		AccountDTO dto = accountService.withdraw(id, request.get("amount"));
		return ResponseEntity.ok(dto);
	}

	@GetMapping
	public ResponseEntity<List<AccountDTO>> getAll() {
		return ResponseEntity.ok(accountService.getAllAccounts());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		accountService.deleteAccount(id);
		return ResponseEntity.ok("Account deleted successfully");
	}
}
