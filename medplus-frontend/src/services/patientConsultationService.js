// src/services/patientConsultationService.js
import api from './api'; // make sure api.js is your axios instance

// ✅ Book a new consultation
export const bookConsultation = async (data) => {
  const response = await api.post('/consultations/book', {
    patientId: data.patientId,
    doctorId: data.doctorId,
    scheduledAt: data.scheduledAt.toISOString(), // convert JS Date to ISO string
    notes: data.notes,
  });
  return response.data;
};

// ✅ Get consultations for a patient
export const getPatientConsultations = async (patientId) => {
  const response = await api.get(`/consultations/patient/${patientId}`);
  return response.data;
};

// ✅ Get consultations for a doctor
export const getDoctorConsultations = async (doctorId) => {
  const response = await api.get(`/consultations/doctor/${doctorId}`);
  return response.data;
};

// ✅ Update consultation status
export const updateConsultationStatus = async (id, status) => {
  // PATCH request with query param ?status=...
  const response = await api.patch(`/consultations/${id}/status?status=${status}`);
  return response.data;
};

// ✅ Optional alias if you want to call it 'updateStatus' in components
export { updateConsultationStatus as updateStatus };
