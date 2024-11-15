package hu.masterfield.bankproject.services;

import java.sql.SQLException;
import java.util.List;

import hu.masterfield.bankproject.datatypes.Account;
import hu.masterfield.bankproject.interfaces.AccountRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Transactional
public class JpaAccountRepository implements AccountRepository{

    @PersistenceContext
    private EntityManager em;

    public JpaAccountRepository() {

    }

    @Override
    public Account loadAccount(String accountNo) throws SQLException {
        Account acc = em.find(Account.class, accountNo);

        if (acc == null) {
            acc = new Account(accountNo, Account.DEFAULT_BALANCE);
            insertAccount(acc);
        }

        return acc;
    }

    @Override
    public void updateAccount(Account accountNo) throws SQLException {
        em.merge(accountNo);
    }

    @Override
    public void insertAccount(Account accountNo) throws SQLException {
        em.persist(accountNo);
    }
    
    private void dumpAllAccounts() {
        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<Account> query = builder.createQuery(Account.class);
        Root<Account> root = query.from(Account.class);
        query.select(root);

        List<Account> list = em.createQuery(query).getResultList();
        System.out.println("--- DUMP ALL ACCOUNTS ---");
        list.forEach(element -> System.out.println(element));
    }

    private void dumpAllAccountsNativeSQL() {
        Query query = em.createNativeQuery("SELECT * FROM ACCOUNTS");

        List<Account> list = query.getResultList();
        System.out.println("--- DUMP ALL ACCOUNTS (NATIVE QUERY) ---");
        list.forEach(element -> System.out.println(element));
    }
}
