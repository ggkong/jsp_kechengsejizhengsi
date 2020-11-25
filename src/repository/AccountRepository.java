package repository;

import java.util.List;

public interface AccountRepository {
    public Integer insertAccount(String account);
    public Integer delAccount(String account);
    public List<String> getAllAccount();
}
