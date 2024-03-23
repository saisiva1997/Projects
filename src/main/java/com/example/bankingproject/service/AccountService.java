package com.example.bankingproject.service;

import java.util.List;

import com.example.bankingproject.dto.AccountDTO;

public interface AccountService {

	AccountDTO createAccount(AccountDTO accountDTO);

	AccountDTO getAccount(Long id);

	AccountDTO depositAmount(Long id, double amount);

	AccountDTO withdraw(Long id, double amount);

	List<AccountDTO> getAllAccounts();

	void deleteAccount(Long id);

}
