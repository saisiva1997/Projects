package com.example.bankingproject.dtomapper;

import com.example.bankingproject.Entity.Account;
import com.example.bankingproject.dto.AccountDTO;

public class AccountMapper {

	public static AccountDTO mapToAccountDTO(Account account) {
		AccountDTO accountDTO = new AccountDTO(account.getId(), account.getAccountHolderName(), account.getBalance());
		return accountDTO;
	}

	public static Account mapToAccount(AccountDTO accountDTO) {
		Account account = new Account(accountDTO.getId(), accountDTO.getAccountHolderName(), accountDTO.getBalance());
		return account;
	}

}
