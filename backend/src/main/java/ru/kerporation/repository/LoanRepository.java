package ru.kerporation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kerporation.entities.Loan;
import ru.kerporation.entities.User;

import java.util.List;


@Repository
public interface LoanRepository extends JpaRepository<Loan,String> {
    List<Loan> findByUser(User user);
    Loan findTop1ByUserOrderByAddedDesc(User user);
}
