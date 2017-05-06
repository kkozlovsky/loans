import {Component, OnInit} from '@angular/core';
import {LoansService, Loan, ILoan} from "../loans.service";
import {UsersService, IUser} from "../../users/users.service";

@Component({
  selector: 'app-loans-list',
  templateUrl: './loans-list.component.html',
  styleUrls: ['./loans-list.component.css']
})
export class LoansListComponent implements OnInit {
  private titleMessage:string = 'Список займов';
  private errorMessage:string;
  private users:IUser[] = [];
  private loans:ILoan[] = [];
  private loan:Loan = new Loan();

  constructor(private loanService : LoansService, private usersService : UsersService) { }

  ngOnInit() {
    this.getUsers();
    this.reload();
    this.clear();
  }

  private getUsers():void {
    this.usersService.getUsers()
        .subscribe(users => this.users = users,
            error => this.errorMessage = <any>error);
  }

  private addLoan(loan:ILoan) {
    this.loanService.addLoan(loan)
        .subscribe(loan => {
              console.log(loan);
              this.reload()
            },
            error => this.errorMessage = <any>error);
    this.clear();
  }

  private deleteLoan(loanId:string) {
    this.loanService.deleteLoanById(loanId)
        .subscribe(loan => {
              console.log(loan);
              this.reload()
            },
            error => this.errorMessage = <any>error);

  }

  private reload():void {
    this.loanService.getLoans()
        .subscribe(loans => this.loans = loans,
            error => this.errorMessage = <any>error);
  }

  private onSubmit() {
    this.addLoan(this.loan);
  }

  private setLoan(loan:ILoan) {
    this.loan = loan;
  }

  private clear():void {
    this.loan = new Loan();
  }

  private checkLoanExist(loanId:string):boolean {
    for (let loan of this.loans) {
      if (loan.loanId === loanId) {
        this.loan = loan;
        return true;
      }
    }
    return false;
  }

}
