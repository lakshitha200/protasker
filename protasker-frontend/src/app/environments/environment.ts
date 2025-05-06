// environments/environment.ts
export const environment = {
    production: false,
    ACCESS_TOKEN_KEY:'JAT',
    REFRESH_TOKEN_KEY: 'JRT',
    registerUrl: 'http://localhost:8080/api/auth/register',
    loginUrl: 'http://localhost:8080/api/auth/login',
    verifyEmailUrl: "http://localhost:8080/api/auth/verify-email",
    resendVerifyEmailUrl: "http://localhost:8080/api/auth/resend-verification",
    forgotPasswordUrl: "http://localhost:8080/api/auth/forgot-password",
    resetPasswordUrl: "http://localhost:8080/api/auth/reset-password",
  };