import "./index.css";
import "./App.css";
import { Navbar } from "./component/Navbar/Navbar";
import { CssBaseline, ThemeProvider } from "@mui/material";

import { darkTheme } from "./Theme/DarkTheme";
import Home from "./component/Home/Home";
import RestaurantDetail from "./component/Restaurant/RestaurantDetail";
import Cart from "./component/Cart/Cart";
import Profile from "./component/Profiles/Profile.jsx";
import CustomerRoute from "./Routers/CustomerRoute.jsx";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { getUser } from "./component/State/Authentication/Action";
import { findCart } from "./component/State/Cart/Action.js";
import Routers from "./Routers/Routers.jsx";
import { getRestaurantByUserId } from "./component/State/Restaurant/Action.js";

function App() {
  const dispatch = useDispatch();
  const jwt = localStorage.getItem("jwt");
  const { auth } = useSelector((store) => store);

  useEffect(() => {
    dispatch(getUser(auth.jwt || jwt));
    dispatch(findCart(jwt));
  }, [auth.jwt]);

  useEffect(() => {
    dispatch(getRestaurantByUserId(auth.jwt || jwt));
  }, [auth.user]);

  return (
    <ThemeProvider theme={darkTheme}>
      <CssBaseline />
      <Routers />
    </ThemeProvider>
  );
}

export default App;
