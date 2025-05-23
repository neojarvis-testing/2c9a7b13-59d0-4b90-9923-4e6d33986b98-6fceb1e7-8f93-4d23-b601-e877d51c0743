import React from "react";
import './ErrorPage.css';
// import alertIcon from '/alert.png';

const ErrorPage = () => {
    return (
        <div className="error-page-container">
            {/* <img src={alertIcon} alt="Error Alert Icon" className="error-icon"/> */}
            <h1>Oops! Something Went Wrong</h1>
            <p>Please try again later.</p>
        </div>
    );
};

export default ErrorPage;
