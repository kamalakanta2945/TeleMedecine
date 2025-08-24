import React, { useState, useEffect, useContext } from 'react';
import { Container, Typography, List, ListItem, ListItemText } from '@mui/material';
import { getHistory } from '../services/historyService';
import { AuthContext } from '../contexts/AuthContext';

const History = () => {
  const { user } = useContext(AuthContext);
  const [history, setHistory] = useState([]);

  useEffect(() => {
    const fetchHistory = async () => {
      const data = await getHistory(user.id);
      setHistory(data);
    };
    fetchHistory();
  }, [user.id]);

  return (
    <Container className="mt-8">
      <Typography variant="h4" className="mb-4">Medical History</Typography>
      <List>
        {history.map(item => (
          <ListItem key={item.id}>
            <ListItemText primary={item.summary} secondary={`Appointment: ${item.appointmentId}`} />
          </ListItem>
        ))}
      </List>
    </Container>
  );
};

export default History;