import React, { useContext } from "react";
import {
  Container,
  Typography,
  Grid,
  Card,
  CardContent,
  Box,
  Button,
} from "@mui/material";
import { Link } from "react-router-dom";
import Sidebar from "../components/Sidebar";
import { AuthContext } from "../contexts/AuthContext";
import EventIcon from "@mui/icons-material/Event";
import PeopleIcon from "@mui/icons-material/People";
import DoneAllIcon from "@mui/icons-material/DoneAll";

const DoctorDashboard = () => {
  const { user } = useContext(AuthContext);

  // Dummy stats (replace with API later)
  const stats = [
    { title: "Pending Consultations", value: 5, icon: <EventIcon />, color: "#1976d2" },
    { title: "Completed Consultations", value: 20, icon: <DoneAllIcon />, color: "#388e3c" },
    { title: "Total Patients", value: 45, icon: <PeopleIcon />, color: "#0288d1" },
  ];

  return (
    <div style={{ display: "flex" }}>
      {/* Sidebar */}
      <Sidebar role={user.role} />

      {/* Main Content */}
      <Container sx={{ mt: 4 }}>
        {/* Title */}
        <Typography variant="h4" gutterBottom fontWeight="bold">
          Doctor Dashboard
        </Typography>

        {/* Stats Section */}
        <Grid container spacing={3} sx={{ mb: 4 }}>
          {stats.map((stat, index) => (
            <Grid item xs={12} sm={6} md={4} key={index}>
              <Card
                sx={{
                  borderRadius: 3,
                  boxShadow: 3,
                  backgroundColor: stat.color,
                  color: "white",
                }}
              >
                <CardContent>
                  <Box display="flex" alignItems="center" gap={2}>
                    {stat.icon}
                    <Box>
                      <Typography variant="h6">{stat.title}</Typography>
                      <Typography variant="h5" fontWeight="bold">
                        {stat.value}
                      </Typography>
                    </Box>
                  </Box>
                </CardContent>
              </Card>
            </Grid>
          ))}
        </Grid>

        {/* Upcoming Consultations Section */}
        <Typography variant="h5" gutterBottom fontWeight="bold">
          Upcoming Consultations
        </Typography>
        <Card sx={{ borderRadius: 3, boxShadow: 2, p: 2 }}>
          <Typography variant="body1">
            No upcoming consultations scheduled.
          </Typography>
          <Button
            component={Link}
            to="/consultations"
            variant="contained"
            sx={{ mt: 2 }}
          >
            View All Consultations
          </Button>
        </Card>
      </Container>
    </div>
  );
};

export default DoctorDashboard;
