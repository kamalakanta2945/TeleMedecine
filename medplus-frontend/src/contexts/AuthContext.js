import React, { createContext, useState, useEffect } from 'react';
import { jwtDecode } from 'jwt-decode';
import { useNavigate } from 'react-router-dom';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const token = localStorage.getItem('token');
    if (token) {
      const decoded = jwtDecode(token);
      setUser({ role: decoded.role, id: decoded.sub });  // Assuming JWT has 'role' and 'sub' (userId)
    }
  }, []);

  const login = (token, role) => {
    localStorage.setItem('token', token);
    setUser({ role, id: jwtDecode(token).sub });
    navigate(role === 'PATIENT' ? '/patient/dashboard' : '/doctor/dashboard');
  };

  const logout = () => {
    localStorage.removeItem('token');
    setUser(null);
    navigate('/login');
  };

  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};