
export interface User {
  id: number;
  userId: string;
  username:string;
  email: string;
  firstName: string;
  lastName: string; 
  phoneNumber: number;
  position: string;
  department:string;
  profilePicture:string;
  active: boolean;
  skills: string[];
  userType: string
  lastLogin: string;
  createdAt: string;
}
