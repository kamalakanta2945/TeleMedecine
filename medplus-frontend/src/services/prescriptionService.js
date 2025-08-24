import api from './api';

export const issuePrescription = async (data) => {
  const response = await api.post('/prescriptions/issue', data);
  return response.data;
};

export const getPrescription = async (appointmentId) => {
  const response = await api.get(`/prescriptions/${appointmentId}`);
  return response.data;
};