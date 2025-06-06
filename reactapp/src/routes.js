import { createRoutesFromElements, Navigate, Route, useLocation } from "react-router-dom";
import Login from "./Components/Login";
import ErrorPage from "./Components/ErrorPage";
import Signup from "./Components/Signup";
const NotFound = () => <div>404</div>;

export default createRoutesFromElements(
    <Route>
        <Route path="/login" element={<Login/>} />
        <Route path="/signup" element={<Signup/>} />
        <Route path="/error" element={<ErrorPage/>} />
        <Route element={<NotFound/>} />
    </Route>
)