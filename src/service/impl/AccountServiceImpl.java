package service.impl;

import repository.AccountRepository;
import repository.impl.AccountRepositoryImpl;
import service.AccountService;

import java.util.List;

public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository = new AccountRepositoryImpl();

    @Override
    public List<String> getAllAccount() {
        return accountRepository.getAllAccount();
    }
}
