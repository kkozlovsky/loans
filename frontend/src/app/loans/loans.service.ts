import {Injectable, EventEmitter} from '@angular/core';
import {Headers, Http, Response, RequestOptions} from "@angular/http";
import {Observable} from "rxjs/Rx";
import {IUser, User} from "../users/users.service";

export interface ILoan{
  loanId : string;
  term : number;
  amount : number;
  countryCode : string;
  added : Date;
  user : IUser;
}

export class Loan implements ILoan{
  loanId : string;
  term : number;
  amount : number;
  countryCode : string;
  added : Date;
  user : User;

  constructor() {}
}


@Injectable()
export class LoansService {

  private loansUrl:string = 'http://localhost:8080/apiloans/loans';
  private loansUsersUrl:string = 'http://localhost:8080/apiloans/users';
  private options = new RequestOptions({ headers : new Headers({ 'Content-Type': 'application/json' }) });

  constructor(private http:Http) {}

  getLoans():Observable<ILoan[]> {
    return this.http.get(this.loansUrl)
        .map((response:Response) => <ILoan[]> response.json())
        .do(data => console.log('All: ' + JSON.stringify(data)))
        .catch(this.handleError);
  }

  getLoanById(id: string):Observable<ILoan> {
    return this.http.get(this.loansUrl+"/"+id)
        .map((response:Response) => <ILoan> response.json())
        .do(data => console.log('getLoanById: ' + JSON.stringify(data)))
        .catch(this.handleError);
  }

  addLoan(loan : ILoan){
    let body = JSON.stringify(loan);
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.post(this.loansUsersUrl+"/"+loan.user.userId, body, this.options)
        .map((response:Response) => <ILoan> response.json())
        .do(data => console.log('addLoan: ' + JSON.stringify(data)))
        .catch(this.handleError);
  }

  deleteLoanById(id: string):Observable<ILoan> {
    return this.http.delete(this.loansUrl+"/"+id)
        .map((response:Response) => <ILoan> response.json())
        .do(data => console.log('deleteLoanById: ' + JSON.stringify(data)))
        .catch(this.handleError);
  }


  private handleError(error:Response) {
    console.error(error);
    return Observable.throw(error.json().error || 'Ошибка сервера');
  }

}
