// models/user.model.ts
export class LoginRequest {
    [x: string]: any;

    usernameOrEmail: string;
    password: string;

    constructor(usernameOrEmail: string, password: string){
      this.usernameOrEmail = usernameOrEmail;
      this.password=password;
    }
    
}