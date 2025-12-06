// Production constants
const rootUrl = "http://localhost:8082"
export const SIGNIN_PROD = rootUrl+"/api/auth/signin";
export const ADD_APPOINTMENT_PROD = rootUrl+"/api/v1/appointments/create";
export const ADD_DOCTOR_PROD = rootUrl+"/api/v1/doctor";
export const GET_ALL_DOCTORS_PROD = rootUrl+"/api/v1/doctor/all";
export const GET_ALL_PATIENTS_PROD = rootUrl+"/api/v1/patient/all";
export const DELETE_DOCTOR_BY_ID_PROD = rootUrl+"/api/v1/doctor";
export const DELETE_APPOINTMENT_BY_ID_PROD = rootUrl+"/api/v1/appointments";
export const DELETE_PATIENT_BY_ID_PROD = rootUrl+"/api/v1/patient";
export const GET_DOCTOR_BY_ID_PROD = rootUrl+"/api/v1/doctor";
export const GET_DOCTOR_BY_EMAIL_PROD = rootUrl+"/api/v1/doctor/email";
export const GET_PATIENT_BY_EMAIL_PROD = rootUrl+"/api/v1/patient/email";
export const GET_ALL_APPOINTMENTS_PROD = rootUrl+"/api/v1/appointments/all";
export const GET_ALL_APPOINTMENTS_BY_DOCTOR_PROD = rootUrl+"/api/v1/appointments/doctor";
export const GET_ALL_APPOINTMENTS_BY_PATIENT_PROD = rootUrl+"/api/v1/appointments/patient";
export const UPDATE_APPOINTMENT_PROD = rootUrl+"/api/v1/appointments";
export const UPDATE_DOCTOR_PROD = rootUrl+"/api/v1/doctor";

// Development constants
export const SIGNIN_DEV = rootUrl+"/api/auth/signin";
export const ADD_APPOINTMENT_DEV = rootUrl+"/api/v1/appointments/create";
export const ADD_DOCTOR_DEV = "http://localhost:8081/api/v1/doctor/";
export const GET_ALL_DOCTORS_DEV = "http://localhost:8081/api/v1/doctor/all";
export const GET_ALL_PATIENTS_DEV = "http://localhost:8082/api/v1/patient/all";
export const DELETE_DOCTOR_BY_ID_DEV = "http://localhost:8081/api/v1/doctor";
export const DELETE_APPOINTMENT_BY_ID_DEV = rootUrl+"/api/v1/appointments";
export const DELETE_PATIENT_BY_ID_DEV = "http://localhost:8081/api/v1/patient";
export const GET_DOCTOR_BY_ID_DEV = "http://localhost:8081/api/v1/doctor";
export const GET_DOCTOR_BY_EMAIL_DEV = "http://localhost:8081/api/v1/doctor/email";
export const GET_PATIENT_BY_EMAIL_DEV = "http://localhost:8082/api/v1/patient/email";
export const GET_ALL_APPOINTMENTS_DEV = rootUrl+"/api/v1/appointments/all";
export const GET_ALL_APPOINTMENTS_BY_DOCTOR_DEV = rootUrl+"/api/v1/appointments/doctor";
export const GET_ALL_APPOINTMENTS_BY_PATIENT_DEV = rootUrl+"/api/v1/appointments/patient";
export const UPDATE_APPOINTMENT_DEV = rootUrl+"/api/v1/appointments";
export const UPDATE_DOCTOR_DEV = "http://localhost:8081/api/v1/doctor";
