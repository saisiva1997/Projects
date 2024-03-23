package com.example.bankingproject.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.bankingproject.Entity.Account;
import com.example.bankingproject.dto.AccountDTO;
import com.example.bankingproject.dtomapper.AccountMapper;
import com.example.bankingproject.repository.AccountRepository;
import com.example.bankingproject.service.AccountService;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@Service
public class AccountServiceImpl implements AccountService {

	private AccountRepository accountRepository;

	@Override
	public AccountDTO createAccount(AccountDTO accountDTO) {
		Account account = AccountMapper.mapToAccount(accountDTO);
		Account savedAccount = accountRepository.save(account);
		AccountDTO DTO = AccountMapper.mapToAccountDTO(savedAccount);
		return DTO;
	}

	@Override
	public AccountDTO getAccount(Long id) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exist"));
		AccountDTO DTO = AccountMapper.mapToAccountDTO(account);
		return DTO;
	}

	@Override
	public AccountDTO depositAmount(Long id, double amount) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exist"));
		double total = account.getBalance() + amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
		AccountDTO DTO = AccountMapper.mapToAccountDTO(savedAccount);
		return DTO;
	}

	@Override
	public AccountDTO withdraw(Long id, double amount) {
		Account acc = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exist"));

		if (acc.getBalance() < amount) {
			throw new RuntimeException("Insufficient funds");
		}

		double remainingbalance = acc.getBalance() - amount;
		acc.setBalance(remainingbalance);
		Account savedAcc = accountRepository.save(acc);
		return AccountMapper.mapToAccountDTO(savedAcc);
	}

	@Override
	public List<AccountDTO> getAllAccounts() {
		List<AccountDTO> l = accountRepository.findAll().stream()
				.map((account) -> AccountMapper.mapToAccountDTO(account)).collect(Collectors.toList());
		return l;
	}

	@Override
	public void deleteAccount(Long id) {
		accountRepository.deleteById(id);
	}

}
