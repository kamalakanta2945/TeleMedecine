import React, { useContext } from 'react';
import { useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import { TextField, Button, Container, Typography, Box, Select, MenuItem, FormControl, InputLabel } from '@mui/material';
import { AuthContext } from '../contexts/AuthContext';
import { registerUser } from '../services/authService';

const schema = yup.object({
  username: yup.string().required(),
  email: yup.string().email().required(),
  password: yup.string().min(6).required(),
  role: yup.string().oneOf(['PATIENT', 'DOCTOR']).required(),
});

const Register = () => {
  const { login: authLogin } = useContext(AuthContext);
  const { register, handleSubmit, formState: { errors } } = useForm({
    resolver: yupResolver(schema),
  });

  const onSubmit = async (data) => {
    try {
      const response = await registerUser(data);
      authLogin(response.token, response.role);
    } catch (error) {
      console.error('Register failed', error);
    }
  };

  return (
    <Container maxWidth="sm" className="mt-8">
      <Box className="p-6 bg-white rounded shadow-md">
        <Typography variant="h4" className="mb-4">Register</Typography>
        <form onSubmit={handleSubmit(onSubmit)}>
          <TextField label="Username" fullWidth margin="normal" {...register('username')} error={!!errors.username} helperText={errors.username?.message} />
          <TextField label="Email" fullWidth margin="normal" {...register('email')} error={!!errors.email} helperText={errors.email?.message} />
          <TextField label="Password" type="password" fullWidth margin="normal" {...register('password')} error={!!errors.password} helperText={errors.password?.message} />
          <FormControl fullWidth margin="normal">
            <InputLabel>Role</InputLabel>
            <Select {...register('role')} error={!!errors.role}>
              <MenuItem value="PATIENT">Patient</MenuItem>
              <MenuItem value="DOCTOR">Doctor</MenuItem>
            </Select>
            {errors.role && <Typography color="error">{errors.role.message}</Typography>}
          </FormControl>
          <Button type="submit" variant="contained" color="primary" fullWidth className="mt-4">
            Register
          </Button>
        </form>
      </Box>
    </Container>
  );
};

export default Register;