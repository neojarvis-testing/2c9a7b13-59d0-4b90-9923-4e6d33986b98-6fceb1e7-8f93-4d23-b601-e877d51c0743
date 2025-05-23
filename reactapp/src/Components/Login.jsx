import React, {useState} from "react";
import './Login.css';


const Login = () => {
    const [formData, setFormData] = useState({email: '', password: ''});
    const [errors, setErrors] = useState({});

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
        validateField(name, value)
    };

    const validateField = (name, value) => {
        let message = '';
        if(name === 'email'){
            // const emailRegex = '';
            // if(!emailRegex.test(value)){
            //     message = 'Invalid email address';
            // } else 
            
        }

        if (name === 'password') {
            if(value.length < 6){
                message = 'Password must be at least 6 characters';
            }
        }

        setErrors(prevErrors => ({ ...prevErrors, [name]: message }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log('Login Successfull!');
    };

    return (
        <div className="login-container">
            <div className="left-panel">
                <h2>WellCare HUB</h2>
                <p> Your health is our priority. Begin your journey to wellness by today.</p>
            </div>
            <div className="right-panel">
                <form onSubmit={handleSubmit}>
                    <h2>Login</h2>
                    <div className="form-group">
                        <input 
                            type="text"
                            name="email"
                            placeholder="email"
                            value={formData.email}
                            onChange={handleChange}
                            required    
                        />
                        {errors.email && <p className="error-text">{errors.email}</p>}
                    </div>
                    <div className="form-group">
                        <input 
                            type="password"
                            name="password"
                            placeholder="password"
                            value={formData.password}
                            onChange={handleChange}
                            required    
                        />
                        {errors.password && <p className="error-text">{errors.password}</p>}
                    </div>
                    <button type="submit">Login</button>
                    <p className="signup-text">
                        Don't have an account? <a href="#">Signup</a>
                    </p>
                </form>
            </div>
        </div>
    )
}

export default Login;