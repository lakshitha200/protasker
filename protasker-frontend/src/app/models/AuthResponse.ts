// models/user.model.ts
export class AuthResponse {

    responseMessage: string;
    accessToken: string;
    refreshToken: string;
    constructor(responseMessage: string,accessToken: string,refreshToken: string){
      this.responseMessage = responseMessage;
      this.accessToken=accessToken;
      this.refreshToken=refreshToken;
    }
    
}