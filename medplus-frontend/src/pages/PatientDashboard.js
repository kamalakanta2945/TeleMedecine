import React, { useContext } from 'react';
import { Container, Typography, Button } from '@mui/material';
import { Link } from 'react-router-dom';
import Sidebar from '../components/Sidebar';
import { AuthContext } from '../contexts/AuthContext';

const PatientDashboard = () => {
  const { user } = useContext(AuthContext);

  return (
    <div className="flex">
      <Sidebar role={user.role} />
      <Container className="mt-8">
        <Typography variant="h4" className="mb-4">Patient Dashboard</Typography>
        <Button variant="contained" component={Link} to="/book-appointment" className="mr-4">
          Book Appointment
        </Button>
        <Button variant="outlined" component={Link} to="/consultations">
          View Consultations
        </Button>
        {/* Add more quick links or summaries */}
      </Container>
    </div>
  );
};

export default PatientDashboard;