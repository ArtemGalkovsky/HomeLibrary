import './App.css'
import {BrowserRouter, Route, Routes} from "react-router";
import "./styles/flex.css";
import MainPage from "./components/main-page/MainPage.jsx";
import LoginPage from "./components/login/LoginPage.jsx";

function App() {
  return <BrowserRouter>
      <Routes>
          <Route path="/" element={<MainPage/>}>

          </Route>
          <Route path="/login" element={<LoginPage/>}>

          </Route>
      </Routes>
  </BrowserRouter>
}

export default App
