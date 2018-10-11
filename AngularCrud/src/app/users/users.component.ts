import { Component, OnInit, Input } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  msg = ""
  users: User[];
  userIsAdmin = true;
  userIsProfessor = true;

  user: User = JSON.parse(localStorage.getItem('user'));
  role: string = this.user.role;
  
  checkRole(role: string){
    if(role == "admin"){
      this.userIsAdmin = true;
    }else if(role == "professor"){
      this.userIsAdmin = false;
      this.userIsProfessor = true;
    }else if(role == "student"){
      this.userIsAdmin = false;
      this.userIsProfessor = false;
    }else{
      this.userIsAdmin = false;
      this.userIsProfessor = false;
    }
  }
  constructor(private userService: UserService) {}

  ngOnInit() {
    this.getUsers(),
    this.checkRole(this.role)
  }
  getUsers(): void{
    this.userService.getUsers().subscribe((data: User[]) => {
        this.users = data;
        console.log(data);
    });
  }

  selectedUser: User;
  onSelectUser(user: User){
    this.selectedUser = new User(user.fullName, user.contact, user.emailId, user.password, user.role)
    this.selectedUser.uId = user.uId
  }

  updateUser(updatedUser: User) {  
    if(updatedUser){
      const id:number = this.selectedUser.uId
      this.userService.updateUser(id, updatedUser).subscribe(user => {
        this.selectedUser = null
        this.getUsers()
      });
    }else{
      alert('FAILED')
    }
  }
  
  deleteConfirm(user: User){
    var confirmation = confirm("Are you sure you want to delete this record?");
    if(confirmation){
      this.deleteUser(user)
    }
  }
  deleteUser(user: User){
    this.userService.deleteUser(user.uId).subscribe((response) => {
      this.getUsers()
    });
  }

  logout(){
    var confirmation = confirm("Are you sure you want to logout?");
    if(confirmation){
      this.msg = "Successfully logged out";
      document.location.href = "login"
    }  
  }
  

}
