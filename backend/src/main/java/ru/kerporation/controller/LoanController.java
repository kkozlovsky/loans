package ru.kerporation.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kerporation.entities.Loan;
import ru.kerporation.service.LoanService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Api(value = "apiloans", description = "Loan API")
@RequestMapping("apiloans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @ApiOperation(value = "Получить список одобренных займов", response = Loan.class)
    @RequestMapping(value = "/loans", method = RequestMethod.GET)
    public ResponseEntity<List<Loan>> list() {
        return new ResponseEntity<>(loanService.getAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Получить описание займа по loanId", response = Loan.class)
    @RequestMapping(value = "/loans/{loanId}", method = RequestMethod.GET)
    public ResponseEntity<Loan> get(@PathVariable String loanId) {
        return new ResponseEntity<>(loanService.getOne(loanId), HttpStatus.OK);
    }

    @ApiOperation(value = "Изменить условия займа", response = Loan.class)
    @RequestMapping(value = "/loans/{loanId}", method = RequestMethod.PUT)
    public ResponseEntity<Loan> update(@PathVariable String loanId, @RequestBody Loan loan) {
        return new ResponseEntity<>(loanService.update(loanId, loan), HttpStatus.OK);
    }

    @ApiOperation(value = "Удалить займ", response = Loan.class)
    @RequestMapping(value = "/loans/{loanId}", method = RequestMethod.DELETE)
    public ResponseEntity<Loan> delete(@PathVariable String loanId) {
        return new ResponseEntity<>(loanService.delete(loanId), HttpStatus.OK);
    }

    @ApiOperation(value = "Получить займы конкретного пользователя", response = Loan.class)
    @RequestMapping(value = "/users/{userId:.+}", method = RequestMethod.GET)
    public ResponseEntity<List<Loan>> getLoansByUser(@PathVariable String userId) {
        return new ResponseEntity<>(loanService.getAllByUser(userId), HttpStatus.OK);
    }

    @ApiOperation(value = "Создать новый займ", response = Loan.class)
    @RequestMapping(value = "/users/{userId:.+}", method = RequestMethod.POST)
    public ResponseEntity<Loan> create(@PathVariable String userId, @RequestBody Loan loan) {
        return new ResponseEntity<>(loanService.create(userId, loan), HttpStatus.CREATED);
    }

}
