import React, { useContext } from 'react';
import { useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import { TextField, Button, Container, Typography, Box } from '@mui/material';
import { DateTimePicker } from '@mui/x-date-pickers/DateTimePicker';
import { bookAppointment } from '../services/appointmentService';
import { AuthContext } from '../contexts/AuthContext';
import { useNavigate } from 'react-router-dom';

const schema = yup.object({
  doctorId: yup.string().required(),
  scheduledAt: yup.date().required(),
  notes: yup.string(),
});

const BookAppointment = () => {
  const { user } = useContext(AuthContext);
  const navigate = useNavigate();
  const { register, handleSubmit, setValue, formState: { errors } } = useForm({
    resolver: yupResolver(schema),
  });

  const onSubmit = async (data) => {
    try {
      await bookAppointment({ ...data, patientId: user.id });
      navigate('/consultations');
    } catch (error) {
      console.error('Booking failed', error);
    }
  };

  return (
    <Container maxWidth="sm" className="mt-8">
      <Box className="p-6 bg-white rounded shadow-md">
        <Typography variant="h4" className="mb-4">Book Appointment</Typography>
        <form onSubmit={handleSubmit(onSubmit)}>
          <TextField label="Doctor ID" fullWidth margin="normal" {...register('doctorId')} error={!!errors.doctorId} helperText={errors.doctorId?.message} />
          <DateTimePicker
            label="Scheduled At"
            onChange={(value) => setValue('scheduledAt', value)}
            renderInput={(params) => <TextField {...params} fullWidth margin="normal" error={!!errors.scheduledAt} helperText={errors.scheduledAt?.message} />}
          />
          <TextField label="Notes" fullWidth margin="normal" multiline {...register('notes')} error={!!errors.notes} helperText={errors.notes?.message} />
          <Button type="submit" variant="contained" color="primary" fullWidth className="mt-4">
            Book
          </Button>
        </form>
      </Box>
    </Container>
  );
};

export default BookAppointment;