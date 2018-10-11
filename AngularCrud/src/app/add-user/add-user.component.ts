import { Component, OnInit,Input } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service'
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {
  msg = ""
  submitted = false;
  @Input() user: User
  users:User[] = [];
  constructor(private userService: UserService) { }
  @Input() newUser: User = new User('', '', '', '', '')

  ngOnInit(): void {
    this.addUserForm = new FormGroup({
     fullName : new FormControl('',[
        Validators.required
       ]),
     contact : new FormControl('',[
        Validators.required,
        Validators.pattern("[0-9]{10}")
     ]),
     emailId : new FormControl('',[
        Validators.required,
        Validators.email
     ]),
     password : new FormControl('',[
        Validators.required,
        Validators.minLength(10)
     ])
   }, { updateOn: 'blur' })
 }
 
 addUser(newUser: User) {  
   this.submitted = true;
  if (this.addUserForm.invalid) {
    return;
  }
   if(newUser){
     this.userService.addUser(newUser).subscribe(user => {
       this.users.push(newUser)
       alert('Record added successfully!')
       this.reload()
     });
   }else{
     alert('FAILED')
   }
 }

reload(){
  document.location.reload()
}

logout(){
  var confirmation = confirm("Are you sure you want to logout?");
  if(confirmation){ 
    this.msg = "Successfully logged out";
    document.location.href = "login"
  }  
}
addUserForm = new FormGroup({
  fullName : new FormControl(''),
  contact : new FormControl(''),
  emailId : new FormControl(''),
  password : new FormControl(''),
})

}