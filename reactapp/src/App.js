import { useMemo } from 'react';
import routes from './routes';

import { createBrowserRouter, RouterProvider} from 'react-router-dom';

const App = () => {
    const router = useMemo(() => createBrowserRouter(routes), []);
    return (
        <div id='app-inner-container'>
            <RouterProvider router={router} />
        </div>
    )
}

export default App;