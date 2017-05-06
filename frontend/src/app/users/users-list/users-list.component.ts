import {Component, OnInit} from '@angular/core';
import {UsersService, IUser, User} from "../users.service";

@Component({
    selector: 'app-users-list',
    templateUrl: './users-list.component.html',
    styleUrls: ['./users-list.component.css']
})
export class UsersListComponent implements OnInit {
    private titleMessage:string = 'Список пользователей';
    private errorMessage:string;
    private users:IUser[] = [];
    private user:IUser = new User();
    private blackListOptions = [{key: true, value: 'Да'}, {key: false, value: 'Нет'}];
    private isBlacklistShow = false;

    constructor(private usersService:UsersService) {
    }

    ngOnInit():void {
        this.reload();
    }

    private deleteUser(userId:string) {
        this.usersService.deleteUserById(userId)
            .subscribe(user => {
                    console.log(user);
                    this.reload()
                },
                error => this.errorMessage = <any>error);
    }

    private addUser(user:IUser) {
        this.usersService.addUser(user)
            .subscribe(user => {
                    console.log(user);
                    this.reload()
                },
                error => this.errorMessage = <any>error);
        this.clear();
    }

    private setUser(user:IUser) {
        this.user = user;
    }

    private reload():void {
        this.usersService.getUsers()
            .subscribe(users => this.users = users,
                error => this.errorMessage = <any>error);
    }

    private onSubmit() {
        this.addUser(this.user);
    }

    private clear():void {
        this.user = new User();
    }

    private checkUserExist(userId:string):boolean {
        for (let user of this.users) {
            if (user.userId === userId) {
                this.user = user;
                return true;
            }
        }
        return false;
    }

  private toggleList():void {
    if(!this.isBlacklistShow) {
      this.showBlacklist();
    }
    else {
      this.reload();
    }
    this.isBlacklistShow= !this.isBlacklistShow;
  }

  private showBlacklist():void {
    this.usersService.getBlacklist()
      .subscribe(blackUsers => this.users = blackUsers,
        error => this.errorMessage = <any>error);
  }

}
