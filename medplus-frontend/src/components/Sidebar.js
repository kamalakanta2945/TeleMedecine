import React from 'react';
import { Drawer, List, ListItem, ListItemIcon, ListItemText, ListItemButton } from '@mui/material';
import { Link } from 'react-router-dom';
import DashboardIcon from '@mui/icons-material/Dashboard';
import EventIcon from '@mui/icons-material/Event';
import ChatIcon from '@mui/icons-material/Chat';
import HistoryIcon from '@mui/icons-material/History';
import PrescriptionIcon from '@mui/icons-material/LocalHospital';

const professionalColors = {
  dashboard: '#1976d2',    // Blue
  consultations: '#9c27b0', // Purple
  bookAppointment: '#388e3c', // Green
  history: '#f57c00',       // Orange
  chat: '#0288d1',          // Light Blue
  prescriptions: '#d32f2f', // Red
};

const Sidebar = ({ role }) => {
  return (
    <Drawer
      variant="permanent"
      anchor="left"
      sx={{
        width: 240,
        '& .MuiDrawer-paper': {
          width: 240,
          boxSizing: 'border-box',
          backgroundColor: '#f4f6f8', // sidebar background
          top: 64, // 👈 push below AppBar (default AppBar height = 64px)
      height: 'calc(100% - 64px)', // 👈 take remaining height
        },
      }}
    >
      <List>
        <ListItem sx={{ mb: 1, borderRadius: 1, color: 'white' }}>
          <ListItemButton
            component={Link}
            to={`/${role.toLowerCase()}/dashboard`}
            sx={{ bgcolor: professionalColors.dashboard }}
          >
            <ListItemIcon sx={{ color: 'white' }}><DashboardIcon /></ListItemIcon>
            <ListItemText primary="Dashboard" />
          </ListItemButton>
        </ListItem>

        <ListItem sx={{ mb: 1, borderRadius: 1, color: 'white' }}>
          <ListItemButton
            component={Link}
            to="/consultations"
            sx={{ bgcolor: professionalColors.consultations }}
          >
            <ListItemIcon sx={{ color: 'white' }}><EventIcon /></ListItemIcon>
            <ListItemText primary="Consultations" />
          </ListItemButton>
        </ListItem>

        {role === 'PATIENT' && (
          <>
            <ListItem sx={{ mb: 1, borderRadius: 1, color: 'white' }}>
              <ListItemButton
                component={Link}
                to="/book-appointment"
                sx={{ bgcolor: professionalColors.bookAppointment }}
              >
                <ListItemIcon sx={{ color: 'white' }}><EventIcon /></ListItemIcon>
                <ListItemText primary="Book Appointment" />
              </ListItemButton>
            </ListItem>

            <ListItem sx={{ mb: 1, borderRadius: 1, color: 'white' }}>
              <ListItemButton
                component={Link}
                to="/history"
                sx={{ bgcolor: professionalColors.history }}
              >
                <ListItemIcon sx={{ color: 'white' }}><HistoryIcon /></ListItemIcon>
                <ListItemText primary="History" />
              </ListItemButton>
            </ListItem>
          </>
        )}

        <ListItem sx={{ mb: 1, borderRadius: 1, color: 'white' }}>
          <ListItemButton
            component={Link}
            to="/chat/:appointmentId"
            sx={{ bgcolor: professionalColors.chat }}
          >
            <ListItemIcon sx={{ color: 'white' }}><ChatIcon /></ListItemIcon>
            <ListItemText primary="Chat" />
          </ListItemButton>
        </ListItem>

        <ListItem sx={{ mb: 1, borderRadius: 1, color: 'white' }}>
          <ListItemButton
            component={Link}
            to="/prescriptions/:appointmentId"
            sx={{ bgcolor: professionalColors.prescriptions }}
          >
            <ListItemIcon sx={{ color: 'white' }}><PrescriptionIcon /></ListItemIcon>
            <ListItemText primary="Prescriptions" />
          </ListItemButton>
        </ListItem>
      </List>
    </Drawer>
  );
};

export default Sidebar;
