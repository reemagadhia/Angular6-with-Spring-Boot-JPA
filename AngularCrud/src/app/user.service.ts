import { Injectable, Input } from '@angular/core';
import { HttpClient} from  '@angular/common/http';
import { User } from './user';
@Injectable({
  providedIn: 'root'
})
export class UserService {
  API_URL  =  'http://localhost:8080';
  //@Input() newStudent: Student = new Student('', '', '', '')
  constructor(private httpClient: HttpClient) {}
  getUsers(){
    return this.httpClient.get(this.API_URL+'/user/getAll');
  }
  addUser(newUser: User){
    return this.httpClient.post(this.API_URL+'/user/create', newUser);
  }
  deleteUser (id:number){
    return this.httpClient.delete(this.API_URL+'/user/delete/'+id);
  }
  updateUser (id:number, updatedUser: User){
    return this.httpClient.patch(this.API_URL+'/user/update/'+id, updatedUser);
  }
  checkUser (emailId:string, password: string){
    return this.httpClient.get(this.API_URL+'/user/check/'+emailId+'/'+password);
  }
}
