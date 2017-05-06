import {Injectable, EventEmitter} from '@angular/core';
import {Headers, Http, Response, RequestOptions} from "@angular/http";
import {Observable} from "rxjs/Rx";

export interface IUser {
  userId:string;
  name:string;
  surName:string;
  blacklist:boolean;
}

export class User implements IUser{
  userId:string;
  name:string;
  surName:string;
  blacklist:boolean;

    constructor() {}
}

@Injectable()
export class UsersService {

  private usersUrl:string = 'http://localhost:8080/apiusers/users';
  private blacklistUrl:string = 'http://localhost:8080/apiusers/blacklist';
  private options = new RequestOptions({ headers : new Headers({ 'Content-Type': 'application/json' }) });

  constructor(private http:Http) {}

  getUsers():Observable<IUser[]> {
    return this.http.get(this.usersUrl)
      .map((response:Response) => <IUser[]> response.json())
      .do(data => console.log('All: ' + JSON.stringify(data)))
      .catch(this.handleError);
  }

  getUserById(id: string):Observable<IUser> {
    return this.http.get(this.usersUrl+"/"+id)
      .map((response:Response) => <IUser> response.json())
      .do(data => console.log('getUserById: ' + JSON.stringify(data)))
      .catch(this.handleError);
  }

  addUser(user : IUser){
    let body = JSON.stringify(user);
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.post(this.usersUrl, body, this.options)
      .map((response:Response) => <IUser> response.json())
      .do(data => console.log('addUser: ' + JSON.stringify(data)))
      .catch(this.handleError);
  }

  updateUser(user : IUser){
    let body = JSON.stringify(user);
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.put(this.usersUrl+"/"+user.userId, body, this.options)
        .map((response:Response) => <IUser> response.json())
        .do(data => console.log('addUser: ' + JSON.stringify(data)))
        .catch(this.handleError);
  }

  deleteUserById(id: string):Observable<IUser> {
    return this.http.delete(this.usersUrl+"/"+id)
      .map((response:Response) => <IUser> response.json())
      .do(data => console.log('deleteUserById: ' + JSON.stringify(data)))
      .catch(this.handleError);
  }

  private handleError(error:Response) {
    console.error(error);
    return Observable.throw(error.json().error || 'Ошибка сервера');
  }

  getBlacklist():Observable<IUser[]> {
    return this.http.get(this.blacklistUrl)
      .map((response:Response) => <IUser[]> response.json())
      .do(data => console.log('Blacklist: ' + JSON.stringify(data)))
      .catch(this.handleError);
  }

}
