import React from 'react'
import ReactDOM from 'react-dom/client'
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";

import App from './App'

import './global.css';

import AllowedAccess from './components/AllowedAccess';
import RouterError from './components/RouterError';
import SignIn from './components/Forms/SignIn';


const router = createBrowserRouter([
  {
    path: "/",
    element: <SignIn />,
  },
  {
    path: "/userProfile",
    element: <AllowedAccess />,
    errorElement: <RouterError />,
  },
  
  
]);

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <App />
    <RouterProvider router={router} />
  </React.StrictMode>
)