import React from 'react';
import { render, fireEvent, screen } from '@testing-library/react';
import { BrowserRouter as Router } from 'react-router-dom';
import { Provider } from 'react-redux';
import { QueryClient, QueryClientProvider } from 'react-query';
import store from '../store';
import Login from '../Components/Login';
import '@testing-library/jest-dom/extend-expect';
import axios from 'axios';
import Signup from '../Components/Signup';
import ErrorPage from '../Components/ErrorPage';
import HomePage from '../Components/HomePage';

import PatientForm from '../ReceptionistComponents/PatientForm';
import ViewPatient from '../ReceptionistComponents/ViewPatient';
import NurseViewPatient from '../NurseComponents/NurseViewPatient';
import NurseVitalSignForm from '../NurseComponents/NurseVitalSignForm';
import DoctorViewPatient from '../DoctorComponents/DoctorViewPatient';
import DoctorViewMedicalReports from '../DoctorComponents/DoctorViewMedicalReports';
import UserPostFeedback from '../UserComponents/UserPostFeedback';



jest.mock('axios');

// Setup QueryClient
const queryClient = new QueryClient();

describe('Login Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  const renderLoginComponent = (props = {}) => {
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <Login {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  
  test('frontend_login_component_renders_the_with_login_heading', () => {
    renderLoginComponent();

  
    const loginHeadings = screen.getAllByText(/Login/i);
    expect(loginHeadings.length).toBeGreaterThan(0);

  });


  test('frontend_login_component_displays_validation_messages_when_login_button_is_clicked_with_empty_fields', () => {
    renderLoginComponent();

    fireEvent.click(screen.getByRole('button', { name: /Login/i }));

    expect(screen.getByText('Email is required')).toBeInTheDocument();
    expect(screen.getByText('Password is required')).toBeInTheDocument();
  });

   
});
describe('Signup Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  const renderSignupComponent = (props = {}) => {
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <Signup {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };
  test('frontend_signup_component_renders_with_signup_heading', () => {
    renderSignupComponent();

    const signupHeadings = screen.getAllByText(/Signup/i);
   expect(signupHeadings.length).toBeGreaterThan(0);

  });

  test('frontend_signup_component_displays_validation_messages_when_submit_button_is_clicked_with_empty_fields', () => {
    renderSignupComponent();

    fireEvent.click(screen.getByRole('button', { name: /Submit/i }));

    expect(screen.getByText('User Name is required')).toBeInTheDocument();
    expect(screen.getByText('Email is required')).toBeInTheDocument();
    expect(screen.getByText('Mobile Number is required')).toBeInTheDocument();
    expect(screen.getByText('Password is required')).toBeInTheDocument();
    expect(screen.getByText('Confirm Password is required')).toBeInTheDocument();
  });

  test('frontend_signup_component_displays_error_when_passwords_do_not_match', () => {
    renderSignupComponent();

    fireEvent.change(screen.getByPlaceholderText('Password'), { target: { value: 'password123' } });
    fireEvent.change(screen.getByPlaceholderText('Confirm Password'), { target: { value: 'password456' } });
    fireEvent.click(screen.getByRole('button', { name: /Submit/i }));

    expect(screen.getByText('Passwords do not match')).toBeInTheDocument();
  });
});

describe('ErrorPage Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });
  const renderErrorComponent = (props = {}) => {
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <ErrorPage {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };
  test('frontend_errorpage_component_renders_with_error_heading', () => {
    renderErrorComponent();
    const headingElement = screen.getByText(/Oops! Something Went Wrong/i);
    expect(headingElement).toBeInTheDocument();
  });

  test('frontend_errorpage_component_renders_with_error_content', () => {
    renderErrorComponent();
    const paragraphElement = screen.getByText(/Please try again later./i);
    expect(paragraphElement).toBeInTheDocument();
  });
});


describe('Home Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });
  const renderHomeComponent = (props = {}) => {
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <HomePage {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  
  test('frontend_home_component_renders_with_heading', () => {
    renderHomeComponent();
    const headingElement = screen.getAllByText(/WellCare HUB/i);
    expect(headingElement.length).toBeGreaterThan(0);

  });
  test('frontend_home_component_renders_with_contact_us', () => {
    renderHomeComponent();
    const headingElement = screen.getAllByText(/Contact Us/i);
    expect(headingElement.length).toBeGreaterThan(0);

  });
});

describe('PatientForm Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  const renderPatientFormComponent = (props = {}) => {
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <PatientForm {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  test('frontend_patientform_receptionist_component_renders_with_add_new_patient_heading', () => {
    renderPatientFormComponent();

    // Check if the heading "Add New Patient" is present when adding a new patient
    const headingElement = screen.getByText(/Add New Patient/i);
    expect(headingElement).toBeInTheDocument();
  });



  test('frontend_patientform_receptionist_component_displays_validation_messages_when_submit_button_is_clicked_with_empty_fields', () => {
    renderPatientFormComponent();

    // Click the submit button without filling the form
    fireEvent.click(screen.getByRole('button', { name: /Add Patient/i }));

    // Check for validation messages
    expect(screen.getByText('First name is required')).toBeInTheDocument();
    expect(screen.getByText('Last name is required')).toBeInTheDocument();
    expect(screen.getByText('Date of birth is required')).toBeInTheDocument();
    expect(screen.getByText('Gender is required')).toBeInTheDocument();
    expect(screen.getByText('Phone number is required')).toBeInTheDocument();
    expect(screen.getByText('Email is required')).toBeInTheDocument();
  });
});


describe('ViewPatient Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  const renderViewPatientComponent = (props = {}) => {
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <ViewPatient {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  test('frontend_viewpatient_receptionist_component_renders_with_heading', () => {
    renderViewPatientComponent();

    // Check if the heading "Patients" is present
    const headingElement = screen.getByText(/Patients/i);
    expect(headingElement).toBeInTheDocument();
  });

  test('frontend_viewpatient_receptionist_component_renders_with_table', () => {
    renderViewPatientComponent();

    // Check if the table is present
    const tableElement = screen.getByRole('table');
    expect(tableElement).toBeInTheDocument();
  });

  test('frontend_viewpatient_receptionist_component_displays_no_records_found_when_no_data', () => {
    renderViewPatientComponent();

    // Simulate no records found in the table
    const noRecordsText = screen.getByText(/Oops! No records Found/i);
    expect(noRecordsText).toBeInTheDocument();
  });
});

describe('NurseViewPatient Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  const renderNurseViewPatientComponent = (props = {}) => {
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <NurseViewPatient {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  test('frontend_nurseviewpatient_nurse_component_renders_with_heading', () => {
    renderNurseViewPatientComponent();

    // Check if the heading "Patients" is present
    const headingElement = screen.getByText(/Patients/i);
    expect(headingElement).toBeInTheDocument();
  });

  test('frontend_nurseviewpatient_nurse_component_renders_with_table', () => {
    renderNurseViewPatientComponent();

    // Check if the table is present
    const tableElement = screen.getByRole('table');
    expect(tableElement).toBeInTheDocument();
  });

  test('frontend_nurseviewpatient_nurse_component_displays_no_records_found_when_no_data', () => {
    renderNurseViewPatientComponent();

    // Simulate no records found in the table
    const noRecordsText = screen.getByText(/Oops! No records Found/i);
    expect(noRecordsText).toBeInTheDocument();
  });
});


describe('NurseVitalSignForm Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });


  const renderNurseVitalSignFormComponent = (props = {}) => {
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <NurseVitalSignForm {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  test('frontend_nursevitalsignform_nurse_component_renders_with_heading_for_adding_vital_sign', () => {
    renderNurseVitalSignFormComponent();

    // Check if the heading "Add New Vital Sign" is present when adding a new record
    const headingElement = screen.getByText(/Add New Vital Sign/i);
    expect(headingElement).toBeInTheDocument();
  });

  test('frontend_nursevitalsignform_nurse_component_displays_validation_messages_when_form_is_submitted_with_empty_fields', () => {
    renderNurseVitalSignFormComponent();

    // Click the submit button without filling in the form fields
    fireEvent.click(screen.getByText(/Add Vital Sign/i));

    // Check for validation error messages
    expect(screen.getByText(/Date is required/i)).toBeInTheDocument();
    expect(screen.getByText(/Temperature is required/i)).toBeInTheDocument();
    expect(screen.getByText(/Pulse is required/i)).toBeInTheDocument();
    expect(screen.getByText(/Respiration rate is required/i)).toBeInTheDocument();
    expect(screen.getByText(/Systolic blood pressure is required/i)).toBeInTheDocument();
    expect(screen.getByText(/Diastolic blood pressure is required/i)).toBeInTheDocument();
  });
});

describe('DoctorViewPatient Component', () => {


  const renderDoctorViewPatientComponent = (props = {}) => {
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <DoctorViewPatient {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  test('frontend_doctorviewpatient_doctor_component_renders_with_heading', () => {
    renderDoctorViewPatientComponent();

    // Check if the heading "Patients" is present
    const headingElement = screen.getByText(/Patients/i);
    expect(headingElement).toBeInTheDocument();
  });

  test('frontend_doctorviewpatient_doctor_component_renders_with_table', () => {
    renderDoctorViewPatientComponent();

    // Check if the table exists
    const tableElement = screen.getByRole('table');
    expect(tableElement).toBeInTheDocument();
  });

  test('frontend_doctorviewpatient_doctor_component_displays_no_records_found_message_when_no_data', () => {
    renderDoctorViewPatientComponent();

    // Simulate no records found scenario
    const noRecordsText = screen.getByText(/Oops! No records Found/i);
    expect(noRecordsText).toBeInTheDocument();
  });
});

describe('DoctorViewMedicalReports Component', () => {

  const renderDoctorViewMedicalReports = (props = {}) => {
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <DoctorViewMedicalReports {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  test('frontend_doctorviewmedicalreports_doctor_component_renders_with_heading', () => {
    renderDoctorViewMedicalReports();

    // Check if the heading "Medical Reports" is present
    const headingElement = screen.getByText(/Medical Reports/i);
    expect(headingElement).toBeInTheDocument();
  });

  test('frontend_doctorviewmedicalreports_doctor_component_renders_with_table', () => {
    renderDoctorViewMedicalReports();

    // Check if the table is present
    const tableElement = screen.getByRole('table');
    expect(tableElement).toBeInTheDocument();
  });

  test('frontend_doctorviewmedicalreports_doctor_component_displays_no_records_found_message_when_no_data', () => {
    renderDoctorViewMedicalReports();

    // Simulate the scenario where no medical reports are found
    const noRecordsText = screen.getByText(/Oops! No records Found/i);
    expect(noRecordsText).toBeInTheDocument();
  });

  test('frontend_doctorviewmedicalreports_doctor_component_displays_search_input', () => {
    renderDoctorViewMedicalReports();

    // Check if the search input is rendered
    const searchInput = screen.getByPlaceholderText(/Search.../i);
    expect(searchInput).toBeInTheDocument();
  });
});

describe('UserPostFeedback Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  const renderUserPostFeedbackComponent = (props = {}) => {
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <UserPostFeedback {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  test('frontend_userpostfeedback_user_component_renders_with_heading', () => {
    renderUserPostFeedbackComponent();

    // Check if the heading is present
    const headingElement = screen.getByText(/Submit Your Feedback/i);
    expect(headingElement).toBeInTheDocument();
  });

  test('frontend_userpostfeedback_user_component_displays_validation_message_when_submit_clicked_with_empty_feedback', () => {
    renderUserPostFeedbackComponent();

    // Click the submit button without entering feedback text
    fireEvent.click(screen.getByRole('button', { name: /Submit Feedback/i }));

    // Check for validation message
    expect(screen.getByText('Feedback text is required')).toBeInTheDocument();
  });
});

