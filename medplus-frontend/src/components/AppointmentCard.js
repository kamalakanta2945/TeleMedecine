import React from 'react';
import { Card, CardContent, Typography, Button, MenuItem, Select, FormControl } from '@mui/material';
import { Link } from 'react-router-dom';

const AppointmentCard = ({ appointment, role, onUpdateStatus }) => {
  const handleStatusChange = (event) => {
    onUpdateStatus(appointment.id, event.target.value);
  };

  return (
    <Card className="mb-4 shadow-lg">
      <CardContent>
        <Typography variant="h6">Appointment ID: {appointment.id}</Typography>
        <Typography>Date: {appointment.dateTime || appointment.scheduledAt}</Typography>
        <Typography>Status: {appointment.status}</Typography>
        <Typography>Notes: {appointment.notes || appointment.consultationNotes}</Typography>
        {role === 'DOCTOR' && (
          <FormControl className="mr-2">
            <Select value={appointment.status} onChange={handleStatusChange}>
              <MenuItem value="SCHEDULED">Scheduled</MenuItem>
              <MenuItem value="COMPLETED">Completed</MenuItem>
              <MenuItem value="CANCELLED">Cancelled</MenuItem>
            </Select>
          </FormControl>
        )}
        <Button variant="outlined" component={Link} to={`/chat/${appointment.id}`}>
          Chat
        </Button>
        <Button variant="outlined" component={Link} to={`/prescriptions/${appointment.id}`} className="ml-2">
          Prescription
        </Button>
      </CardContent>
    </Card>
  );
};

export default AppointmentCard;