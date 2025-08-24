import React, { useState, useEffect, useContext } from 'react';
import { useParams } from 'react-router-dom';
import { Container, Typography, TextField, Button, List, ListItem } from '@mui/material';
import { getPrescription, issuePrescription } from '../services/prescriptionService';
import { AuthContext } from '../contexts/AuthContext';
import { useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';

const schema = yup.object({
  medications: yup.string().required('Please enter at least one medication'), // changed to string
  instructions: yup.string().required('Instructions are required'),
});

const Prescriptions = () => {
  const { appointmentId } = useParams();
  const { user } = useContext(AuthContext);
  const [prescription, setPrescription] = useState(null);
  const [successMessage, setSuccessMessage] = useState('');
  const [errorMessage, setErrorMessage] = useState('');

  const { register, handleSubmit, formState: { errors } } = useForm({
    resolver: yupResolver(schema),
  });

  useEffect(() => {
    const fetchPrescription = async () => {
      try {
        const data = await getPrescription(appointmentId);
        setPrescription(data);
      } catch (error) {
        console.error('Failed to fetch prescription', error);
      }
    };
    fetchPrescription();
  }, [appointmentId]);

  const onSubmit = async (data) => {
    try {
      // Convert comma-separated string to array
      const medicationsArray = data.medications
        .split(',')
        .map((m) => m.trim())
        .filter((m) => m);

      const newPres = await issuePrescription({
        ...data,
        medications: medicationsArray,
        appointmentId,
        doctorId: user.id,
        patientId: 'patientId_here', // TODO: get real patientId
      });

      setPrescription(newPres);
      setSuccessMessage('Prescription issued successfully!');
      setErrorMessage('');
    } catch (error) {
      console.error('Issue failed', error);
      setErrorMessage(error.response?.data?.message || 'Failed to issue prescription');
      setSuccessMessage('');
    }
  };

  return (
    <Container className="mt-8">
      <Typography variant="h4" className="mb-4">Prescription for Appointment </Typography>

      {prescription ? (
        <>
          <Typography>Issued: {prescription.issuedDate || 'N/A'}</Typography>
          <List>
            {prescription.medications.map((med, idx) => (
              <ListItem key={idx}>{med}</ListItem>
            ))}
          </List>
          <Typography>Instructions: {prescription.instructions}</Typography>
        </>
      ) : user.role === 'DOCTOR' ? (
        <form onSubmit={handleSubmit(onSubmit)}>
          <TextField
            label="Medications (comma-separated)"
            fullWidth
            margin="normal"
            {...register('medications')}
            error={!!errors.medications}
            helperText={errors.medications?.message}
          />
          <TextField
            label="Instructions"
            fullWidth
            margin="normal"
            multiline
            {...register('instructions')}
            error={!!errors.instructions}
            helperText={errors.instructions?.message}
          />

          {errorMessage && <Typography color="error" sx={{ mb: 1 }}>{errorMessage}</Typography>}
          {successMessage && <Typography color="success.main" sx={{ mb: 1 }}>{successMessage}</Typography>}

          <Button type="submit" variant="contained" color="primary" fullWidth className="mt-4">
            Issue Prescription
          </Button>
        </form>
      ) : (
        <Typography>No prescription issued yet.</Typography>
      )}
    </Container>
  );
};

export default Prescriptions;
