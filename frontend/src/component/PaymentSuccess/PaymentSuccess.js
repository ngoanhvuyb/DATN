import React, { useEffect } from "react";
import TaskAltIcon from "@mui/icons-material/TaskAlt";
import { green } from "@mui/material/colors";
import { Button, Card } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { clearCartAction } from "../State/Cart/Action"; // Import clearCart action

export const PaymentSuccess = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const { auth } = useSelector((state) => state);
  const jwt = localStorage.getItem("jwt");

  // Clear giỏ hàng khi trang thanh toán thành công được load
  useEffect(() => {
    dispatch(clearCartAction(auth.jwt || jwt));
  }, [dispatch, auth.jwt, jwt]);

  return (
    <div className="min-h-screen px-5">
      <div className="flex flex-col items-center justify-center h-[90vh]">
        <Card className="box w-full lg:w-1/4 flex flex-col items-center rounded-md p-5">
          <TaskAltIcon sx={{ fontSize: "5rem", color: green[500] }} />
          <h1 className="py-5 text-2xl font-semibold">Order Success!</h1>
          <p className="py-3 text-center text-gray-400">
            Thank you for choosing our restaurant! We appreciate your order
          </p>
          <p className="py-2 text-center text-gray-200 text-lg">
            Have a great day!
          </p>
          <Button
            onClick={() => navigate("/")}
            variant="contained"
            className="py-5"
            sx={{ margin: "1rem 0rem" }}
          >
            Go to Homepage
          </Button>
        </Card>
      </div>
    </div>
  );
};
