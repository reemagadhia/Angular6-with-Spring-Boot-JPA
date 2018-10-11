import { Component, OnInit, Input } from '@angular/core';
import { UserService } from '../user.service';
import { User } from '../user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  msg = ""
  constructor(private userService: UserService) { }
  @Input() user: User = new User('', '', '', '', '')
  ngOnInit() {
  }
  
  checkUser(user: User){
    if(user){
      this.userService.checkUser(user.emailId, user.password).subscribe((user:User) => {
        if(user===null){   
          this.reload()
          this.msg = "Invalid Credentials!"
          document.location.href="login";
        }else if(user.role){
          localStorage.setItem("user", JSON.stringify(user));
          document.location.href="viewUsers";
        }else{
          this.reload()
          this.msg = "Invalid Credentials!"
          document.location.href="login";
        }     
        // if(data.goto==1){
        //   console.log(user.sId);
       // document.location.href="admin";
        // }else if(data.goto==2){
        //   document.location.href="viewUsers";     
        // }else if(data.goto==3){
        //   this.reload()
        //   this.msg = "Invalid Credentials!"
        //   document.location.href="login";     
        // }else{
        //   this.reload()
        //   this.msg = "Invalid Credentials!"
        //   document.location.href="login";
        // }
      });
    }else{
      alert('FAILED')
    }

  }
  reload(){
    document.location.reload()
   }

}
