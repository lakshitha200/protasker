export class PasswordRestRequest {
    token: string;
    newPassword: string;

    constructor(token: string, newPassword: string){
      this.token = token;
      this.newPassword=newPassword;
    }
    
}