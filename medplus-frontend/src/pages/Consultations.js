import React, { useState, useEffect, useContext } from 'react';
import { Container, Typography } from '@mui/material';
import AppointmentCard from '../components/AppointmentCard';
//import { getPatientConsultations, getDoctorConsultations, updateConsultationStatus } from '../services/patientConsultationService';
 import { getPatientConsultations, getDoctorConsultations, updateStatus } from '../services/patientConsultationService';
import { AuthContext } from '../contexts/AuthContext';

const Consultations = () => {
  const { user } = useContext(AuthContext);
  const [consultations, setConsultations] = useState([]);

  useEffect(() => {
    const fetchConsultations = async () => {
      let data;
      if (user.role === 'PATIENT') {
        data = await getPatientConsultations(user.id);
      } else {
        data = await getDoctorConsultations(user.id);
      }
      setConsultations(data);
    };
    fetchConsultations();
  }, [user]);

  //const handleUpdateStatus = async (id, status) => {
    const handleUpdateStatus = async (id, status) => {
   // await updateConsultationStatus(id, status);
   await updateStatus(id, status);
    const updated = consultations.map(c => c.id === id ? { ...c, status } : c);
    setConsultations(updated);
  };

  return (
    <Container className="mt-8">
      <Typography variant="h4" className="mb-4">Consultations</Typography>
      {consultations.map(consult => (
        <AppointmentCard key={consult.id} appointment={consult} role={user.role} onUpdateStatus={handleUpdateStatus} />
      ))}
    </Container>
  );
};

export default Consultations;