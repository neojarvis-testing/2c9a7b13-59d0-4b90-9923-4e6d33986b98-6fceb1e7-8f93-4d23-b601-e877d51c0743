import React from "react";
import { Formik, Form, Field, ErrorMessage, } from 'formik';
import * as Yup from 'yup';
import './Signup.css';

const Signup = () => {
    const initialValues = {
        username: '',
        email: '',
        mobile: '',
        password: '',
        confirmPassword: '',
        role: ''
    };

    const validationSchema = Yup.object({
        username: Yup.string().required('User Name is required.'),
        email: Yup.string().email('Invalid email').required('Email is required.'),
        mobile: Yup.string()
        .matches(/^[0-9]{10}$/, 'Mobile Number must be exactly 10 digits')
        .required('Mobile Number is required.'),
        password: Yup.string()
        .min(6, 'Passwrod muct be atleast 6 characters')
        .required('Passwrod is required.'),
        confirmPassword: Yup.string()
        .oneOf([Yup.ref('password'), null], 'Passwrod must match')
        .required('Confirm Passwrod is required.'),
        role: Yup.string().required('Role is required.')
    });

    const handleSubmit = () => {

    }

    return (
        <div className="signup-container">
            <div className="signup-card">
                <h2>Signup</h2>
                <Formik initialValues={initialValues} validationSchema={validationSchema} onSubmit={handleSubmit}>
                    <Form >
                        <div className="form-group">
                            <Field name="username" type="text" placeholder="User Name"/>
                            <ErrorMessage name="username" component="div" className="error-text"/>
                        </div>

                        <div className="form-group">
                            <Field name="email" type="email" placeholder="Email"/>
                            <ErrorMessage name="email" component="div" className="error-text"/>
                        </div>

                        <div className="form-group">
                            <Field name="mobile" type="text" placeholder="Mobile Number"/>
                            <ErrorMessage name="mobile" component="div" className="error-text"/>
                        </div>

                        <div className="form-group">
                            <Field name="password" type="password" placeholder="Password"/>
                            <ErrorMessage name="password" component="div" className="error-text"/>
                        </div>

                        <div className="form-group">
                            <Field name="confirmPassword" type="password" placeholder="Confirm Password"/>
                            <ErrorMessage name="confirmPassword" component="div" className="error-text"/>
                        </div>

                        <div className="form-group">
                            <Field name="role" as="select">
                                <option value=''>Select Role</option>
                                <option value='receptionist'>Receptionist</option>
                                <option value='nurse'>Nurse</option>
                                <option value='doctor'>Doctor</option>
                                <option value='user'>User</option>
                            </Field>
                            <ErrorMessage name="role" component="div" className="error-text"/>
                        </div>
                        <button type="submit">Submit</button>
                        <p className="login-redirect">Already have an account? <a href="/login">Login</a></p>
                    </Form>
                </Formik>
            </div>
        </div>
    );
}

export default Signup;