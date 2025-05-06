import { getErrorMessage } from './error-messages';

export function validateEmptyFields(value: any, action: string): any {
  
  const errors: any = {};
  console.log(errors)
  if(action=="sign-up"){
    if (!value.username) errors.usernameError = getErrorMessage('username', 'empty');
    if (!value.email) errors.emailError = getErrorMessage('email', 'empty');
    if (!value.password) errors.passwordError = getErrorMessage('password', 'empty');
    if (!value.confirmPasswrod) errors.confirmPasswordError = getErrorMessage('confirmPassword', 'empty');
  
  }

  if(action == "sign-in"){
    if (!value.usernameOrEmail) errors.usernameOrEmailError = getErrorMessage('usernameOrEmail', 'empty');
    if (!value.password) errors.passwordError = getErrorMessage('password', 'empty');
  }

  if(action == "reset-password"){
    if (!value) errors.emailError = getErrorMessage('email', 'empty');
  }

  if(action == "new-password"){
    if (!value.password) errors.passwordError = getErrorMessage('password', 'empty');
    if (!value.confirmPasswrod) errors.confirmPasswordError = getErrorMessage('confirmPassword', 'empty');
  } 
  
  return errors;
}

export function validateSyntax(value: any, action: string): any {
  const errors: any = {};
  console.log(errors)
  const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  const usernamePattern = /^[a-zA-Z0-9_]{3,}$/;

  if(action=="sign-up"){
    if (!usernamePattern.test(value.username))
      errors.usernameError = getErrorMessage('username', 'pattern');
    if (!emailPattern.test(value.email))
      errors.emailError = getErrorMessage('email', 'pattern');
    if (value.password.trim() !== value.confirmPasswrod.trim()) {
      errors.passwordError = getErrorMessage('password', 'mismatch');
      errors.confirmPasswordError = getErrorMessage('password', 'mismatch');
    }
  }

  if(action == "reset-password"){
    if (!emailPattern.test(value)){
      errors.emailError = getErrorMessage('email', 'pattern');
    }
  }

  if(action == "new-password"){
    if (value.password.trim() !== value.confirmPasswrod.trim()) {
      errors.passwordError = getErrorMessage('password', 'mismatch');
      errors.confirmPasswordError = getErrorMessage('password', 'mismatch');
    }
  } 
  return errors;
}

export function validateActualData(value: any, action: string): any {
  const errors: any = {};
  if(action=="sign-in"){
    if(getErrorMessage("usernameOrEmail","invalid")=="* "+value.detail.split(": ")[1]){
      errors.usernameOrEmailError = getErrorMessage("usernameOrEmail","invalid")
    }else{
      errors.passwordError = getErrorMessage("password","invalid");
    }
  }

  if(action=="sign-up"){
    if(value.password){
      errors.passwordError = value.password;
      errors.confirmPasswordError = value.password;
    }else{
      if(getErrorMessage("username","invalid")=="* "+value.detail.split(": ")[1]){
        errors.usernameError = getErrorMessage("username","invalid");
      }
      if(getErrorMessage("email","invalid")=="* "+value.detail.split(": ")[1]){
        errors.emailError = getErrorMessage("email","invalid");
      }
    }  
  }

  if(action == "new-password"){
    if(value.password){
      errors.passwordError = value.password;
      errors.confirmPasswordError = value.password;
    }
  } 
  
  return errors;
}
