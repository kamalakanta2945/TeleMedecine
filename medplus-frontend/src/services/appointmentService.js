import api from './api';

export const bookAppointment = async (data) => {
  return await api.post('/consultations/book', data);  // Using consultations/book as per your backend
};

export const getPatientConsultations = async (patientId) => {
  const response = await api.get(`/consultations/patient/${patientId}`);
  return response.data;
};

export const getDoctorConsultations = async (doctorId) => {
  const response = await api.get(`/consultations/doctor/${doctorId}`);
  return response.data;
};

export const updateStatus = async (id, status) => {
  const response = await api.patch(`/consultations/${id}/status?status=${status}`);
  return response.data;
};

// Also support /appointments if needed, but backend seems to use consultations