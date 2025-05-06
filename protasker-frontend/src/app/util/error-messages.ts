export function getErrorMessage(field: string, errorType: string): string {
  const messages: { [key: string]: { [key: string]: string } } = {
    username: {
      pattern: '* Min 3 chars: letters, numbers, or _',
      empty: '* Username is required',
      invalid: '* User already exists with the username'
    },
    email: {
      pattern: '* Please enter a valid email',
      empty: '* Email is required',
      invalid: '* User already exists with the email',
    },
    password: {
      mismatch: '* Passwords do not match',
      empty: '* Password is required',
      syntax: '* Password must contain at least one digit, one lowercase, one uppercase letter, and one special character',
      invalid: '* Invalid password'
    },
    usernameOrEmail: {
      pattern: '* Enter a valid email or username',
       empty: '* Username or Email is required',
       invalid: '* Invalid username or email'
    },
    confirmPassword: {
      empty: '* Confirm Password is required'
    }
  };

  return messages[field]?.[errorType] || 'Invalid input';
}
