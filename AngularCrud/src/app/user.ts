export class User{
    uId: number;
    fullName: string;
    contact: string;
    emailId: string;
    password: string;
    role: string;

    constructor(fullName, contact, emailId, password, role) {
        this.fullName = fullName;
        this.contact = contact;
        this.emailId = emailId;
        this.password = password;
        this.role = role;
     }

}