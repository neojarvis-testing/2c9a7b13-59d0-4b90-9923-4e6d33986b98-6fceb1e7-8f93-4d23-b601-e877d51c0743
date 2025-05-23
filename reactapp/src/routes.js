import { createRoutesFromElements, Navigate, Route, useLocation } from "react-router-dom";
import Login from "./Components/Login";
import ErrorPage from "./Components/ErrorPage";
const NotFound = () => <div>404</div>;

export default createRoutesFromElements(
    <Route>
        <Route path="/login" element={<Login/>} />
        <Route path="/error" element={<ErrorPage/>} />
        <Route element={<NotFound/>} />
    </Route>
)